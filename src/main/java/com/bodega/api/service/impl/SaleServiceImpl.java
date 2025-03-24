package com.bodega.api.service.impl;

import com.bodega.api.config.PaypalPropertyConfig;
import com.bodega.api.io.SaleDetailEntity;
import com.bodega.api.io.SaleEntity;
import com.bodega.api.repository.SaleRepository;
import com.bodega.api.service.ProductService;
import com.bodega.api.service.SaleService;
import com.bodega.api.shared.dto.ProductDto;
import com.bodega.api.shared.dto.SaleDto;
import com.bodega.api.shared.report.SaleReport;
import com.bodega.api.ui.model.request.CreateOrderRequest;
import com.bodega.api.ui.model.request.ReportSaleRequest;
import com.bodega.api.ui.model.request.SaleRequest;
import com.bodega.api.ui.model.response.PaypalCreateOrderResponse;
import com.bodega.api.ui.model.response.ReportSaleResponse;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Base64;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.stream.IntStream;

@Slf4j
@RequiredArgsConstructor
@Service
public class SaleServiceImpl implements SaleService {

  private final ProductService productService;
  private final SaleRepository repository;
  private final ModelMapper mapper;
  private final WebClient webClient;
  private final PaypalPropertyConfig paypalPropertyConfig;

  @Override
  public Mono<PaypalCreateOrderResponse> createOrder(CreateOrderRequest request) {
    String basicAuthHeader = "basic " + Base64.getEncoder().encodeToString((paypalPropertyConfig.getClientId() + ":" + paypalPropertyConfig.getSecret()).getBytes());
    return webClient
      .post()
      .uri(paypalPropertyConfig.getUrlV2() + "/checkout/orders")
      .header("Prefer", "return=representation")
      .header("PayPal-Request-Id", UUID.randomUUID().toString())
      .header(HttpHeaders.AUTHORIZATION, basicAuthHeader)
      .bodyValue(request)
      .retrieve()
      .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> clientResponse
        .bodyToMono(String.class)
        .flatMap(errorResponse -> Mono.error(new RuntimeException(errorResponse))))
      .bodyToMono(PaypalCreateOrderResponse.class);
  }

  @Transactional
  @Override
  public Mono<PaypalCreateOrderResponse> captureOrder(String paypalId, SaleRequest request) {
    var url = paypalPropertyConfig.getUrlV2().concat("/checkout/orders/{id}/capture");
    String basicAuthHeader = "basic " + Base64.getEncoder().encodeToString((paypalPropertyConfig.getClientId() + ":" + paypalPropertyConfig.getSecret()).getBytes());
    return webClient
      .post()
      .uri(url, paypalId)
      .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
      .header("Prefer", "return=representation")
      .header("PayPal-Request-Id", UUID.randomUUID().toString())
      .header(HttpHeaders.AUTHORIZATION, basicAuthHeader)
      .retrieve()
      .bodyToMono(PaypalCreateOrderResponse.class)
      .map(paypalCreateOrderResponse -> {
        var sale = mapper.map(request, SaleDto.class);
        sale.setPaypalId(paypalCreateOrderResponse.getId());
        save(sale);
        return paypalCreateOrderResponse;
      })
      .log();
  }

  @Transactional
  @Override
  public Flux<ProductDto> updateProductStockByPaypalId(String paypalId) {
    var sales = repository.findAllByPaypalId(paypalId);
    return updateProductStock(sales.getSaleDetail());
  }

  @Transactional
  @Override
  public Flux<ProductDto> updateProductStockByCode(String code) {
    var sales = repository.findAllByCode(code);
    return updateProductStock(sales.getSaleDetail());
  }

  public Flux<ProductDto> updateProductStock(List<SaleDetailEntity> saleDetails) {
    return Flux.fromIterable(saleDetails)
      .concatMap(saleDetailEntity -> {
        var product = productService.getProduct(saleDetailEntity.getProductId());
        return product
          .flatMap(productDto -> {
            var quantity = productDto.getQuantity() - saleDetailEntity.getQuantity();
            productDto.setQuantity(quantity);
            return productService.saveProduct(productDto);
          });
      });
  }

  @Override
  public Flux<SaleDto> getSalesByUser(UUID userId) {
    var sales = repository.findAllByUserId(userId);
    return Flux.fromIterable(sales)
      .map(saleEntity -> mapper.map(saleEntity, SaleDto.class));
  }

  @Override
  public Mono<SaleDto> save(SaleDto saleDto) {
    var saleEntity = mapper.map(saleDto, SaleEntity.class);
    var saleDetails = saleDto
      .getSaleDetail()
      .stream()
      .map(saleDetailDto -> {
        var saleDetailEntity = mapper.map(saleDetailDto, SaleDetailEntity.class);
        saleDetailEntity.setSaleId(saleDto.getId());
        return saleDetailEntity;
      })
      .toList();
    saleEntity.setSaleDetail(saleDetails);
    var savedSale = repository.save(saleEntity);
    return Mono.just(mapper.map(savedSale, SaleDto.class));
  }

  @Override
  public void buildReportSales(ReportSaleRequest saleDataReport, HttpServletResponse response) {
    SaleReport.BuildReport(saleDataReport, response);
  }

  @Override
  public List<ReportSaleResponse> generateReportSales() {
    var sales = repository.findAll();

    var years = sales
      .stream()
      .map(SaleEntity::getDateRegister)
      .map(LocalDateTime::getYear)
      .map(String::valueOf)
      .distinct()
      .toList();

    return years
      .stream()
      .map(year -> {
        var reportSale = new ReportSaleResponse();
        reportSale.setYear(year);
        reportSale.setMonths(this.buildReportMonthSale(sales, year));
        return reportSale;
      })
      .toList();
  }

  public List<ReportSaleResponse.ReportMonthSales> buildReportMonthSale(List<SaleEntity> sales, String year) {
    return IntStream.rangeClosed(1, 12)
      .boxed()
      .map(month -> {
        var monthSale = new ReportSaleResponse.ReportMonthSales();
        Locale spanishLocale = new Locale("es", "ES");
        var monthName = LocalDate.now().withMonth(month)
          .getMonth()
          .getDisplayName(TextStyle.FULL, spanishLocale);
        monthSale.setMonth(StringUtils.capitalize(monthName.toLowerCase()));

        var currentSales = sales
          .stream()
          .filter(sale -> {
            var currentYear = sale.getDateRegister().getYear();
            var currentMonth = sale.getDateRegister().getMonthValue();
            return String.valueOf(currentYear).equals(year) && month.equals(currentMonth);
          })
          .toList();

        var total = currentSales
          .stream()
          .map(SaleEntity::getSalePrice)
          .reduce(Integer::sum)
          .orElse(0);
        monthSale.setVenta(total);
        return monthSale;
      })
      .toList();
  }
}
