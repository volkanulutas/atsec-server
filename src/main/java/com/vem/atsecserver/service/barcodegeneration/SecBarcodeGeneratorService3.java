package com.vem.atsecserver.service.barcodegeneration;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import com.vem.atsecserver.data.report.product.ProductReport;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author volkanulutas
 * @since 12.12.2020
 */
@Service
public class SecBarcodeGeneratorService3 {

    public static void generateCode128BarcodeImage(String barcodeText) throws IOException {
        /*
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
        ZxingBarcodeGenerator.generateCode128BarcodeImage(barcode);
        */
        Code128Writer barcodeWriter = new Code128Writer();
        BitMatrix bitMatrix = barcodeWriter.encode(barcodeText, BarcodeFormat.CODE_128, 300, 150);

        // MatrixToImageWriter.toBufferedImage(bitMatrix);
        Path path = FileSystems.getDefault().getPath("./MyQRCode.png");
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
    }

    // name and destination of output file e.g. "report.pdf"
    private static String destFileName = "report_product.pdf";


    public byte[] createBarcode(ProductReport productBarcode) throws FileNotFoundException, JRException {
        System.out.println("generating jasper report...");

        // 1. compile template ".jrxml" file
        JasperReport jasperReport = getJasperReport();

        // 2. parameters "empty"
        Map<String, Object> parameters = getParameters();

        // 3. datasource "java object"
        JRDataSource dataSource = getDataSource(productBarcode);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        byte[] file = JasperExportManager.exportReportToPdf(jasperPrint);
        return file;
    }

    public static void main(String[] args) throws FileNotFoundException, JRException {

        System.out.println("generating jasper report...");

        // 1. compile template ".jrxml" file
        JasperReport jasperReport = getJasperReport();

        // 2. parameters "empty"
        Map<String, Object> parameters = getParameters();

        ProductReport product = new ProductReport();
        product.setCustomerId("1");
        product.setDefinition("product açıklaması");
        product.setDonorId("2");
        product.setSecCode("3232");
        product.setStatus("status");

        // 3. datasource "java object"
        JRDataSource dataSource = getDataSource(product);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        JasperExportManager.exportReportToPdfFile(jasperPrint, destFileName);
    }

    private static JasperReport getJasperReport() throws FileNotFoundException, JRException {
        File template = ResourceUtils.getFile("classpath:report_Product.jrxml");
        return JasperCompileManager.compileReport(template.getAbsolutePath());
    }

    private static Map<String, Object> getParameters() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "hmkcode");
        return parameters;
    }

    private static JRDataSource getDataSource(ProductReport productBarcode) {
        List<ProductReport> products = new LinkedList<>();
        // products.add(productBarcode);
        ProductReport product = new ProductReport();
        product.setCustomerId("1");
        product.setDefinition("product açıklaması");
        product.setDonorId("2");
        product.setSecCode("3232");
        product.setStatus("status");
        products.add(product);
        return new JRBeanCollectionDataSource(products);
    }

/*
    public static void main(String[] args) {
        try {
            SecBarcodeGeneratorService.generateCode128BarcodeImage("0170101330");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 */
}
