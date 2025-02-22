package com.bodega.api.service.impl;

import com.bodega.api.io.SaleDetailEntity;
import com.bodega.api.io.SaleEntity;
import com.bodega.api.repository.SaleRepository;
import com.bodega.api.service.SaleService;
import com.bodega.api.shared.dto.SaleDto;
import com.bodega.api.shared.report.SaleReport;
import com.bodega.api.ui.model.request.ReportSaleRequest;
import com.bodega.api.ui.model.response.ReportSaleResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.time.temporal.ChronoField;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.stream.IntStream;

@Slf4j
@RequiredArgsConstructor
@Service
public class SaleServiceImpl implements SaleService {

    private final SaleRepository repository;
    private final ModelMapper mapper;

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
          Locale spanishLocale=new Locale("es", "ES");
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
        saleEntity.setCode(UUID.randomUUID().toString());
        saleEntity.setSaleDetail(saleDetails);
        var savedSale = repository.save(saleEntity);
        return Mono.just(mapper.map(savedSale, SaleDto.class));
    }

  @Override
  public Flux<SaleDto> getSalesByUserId(UUID userId) {
    var salesByUser = repository.findByUserId(userId);
    return Flux.fromIterable(salesByUser)
      .map(saleEntity -> mapper.map(saleEntity, SaleDto.class));
  }
}
