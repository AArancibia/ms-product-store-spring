package com.bodega.api.shared.report;

import com.bodega.api.ui.model.request.MonthSaleReport;
import com.bodega.api.ui.model.request.ReportSaleRequest;
import com.itextpdf.awt.DefaultFontMapper;
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.geom.Rectangle2D;

@Slf4j
@Service
public class SaleReport {
    private SaleReport() {}

    public static void BuildReport(ReportSaleRequest saleDataReport, HttpServletResponse response) {
        try {

            int width = 640;
            int height = 720;

            Document document = new Document();

            PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
            writer.setInitialLeading(20);
            document.open();

            PdfContentByte Add_Chart_Content= writer.getDirectContent();

            PdfTemplate template_Chart_Holder = Add_Chart_Content.createTemplate(width,height);

            Graphics2D Graphics_Chart = template_Chart_Holder.createGraphics(width,height,new DefaultFontMapper());

            Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            fontTitle.setSize(18);

            Font fontDescription = FontFactory.getFont(FontFactory.HELVETICA);
            fontDescription.setSize(15);

            Paragraph description = new Paragraph("A continuación se detalla por meses la venta alcanzada:", fontDescription);
            description.setSpacingBefore(15);

            document.add(new Paragraph("Reporte de venta en el año " + saleDataReport.getYear(), fontTitle));
            document.add(description);

            Rectangle2D Chart_Region=new Rectangle2D.Double(0,0,540,280);

            generatePieChart(saleDataReport).draw(Graphics_Chart,Chart_Region);
            Graphics_Chart.dispose();

            Add_Chart_Content.addTemplate(template_Chart_Holder, 30, 0);

            document.close();
        } catch (Exception e) {
            log.info(e.getMessage());
        }
    }

    public static JFreeChart generatePieChart(ReportSaleRequest saleDataReport) {
        var dataSet = new DefaultPieDataset();
        for (MonthSaleReport month: saleDataReport.getMonths()) {
            if (month.getVenta() > 0) {
                String message = month.getMonth() + " - S/. " + month.getVenta();
                dataSet.setValue(message, month.getVenta());
            }
        }
        return ChartFactory.createPieChart(
                "", dataSet, false, false, false);
    }
}
