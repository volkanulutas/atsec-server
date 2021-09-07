package com.vem.atsecserver.service.barcodegeneration;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import com.vem.atsecserver.entity.product.EnumProductType;
import com.vem.atsecserver.entity.product.Product;
import com.vem.atsecserver.entity.rawproduct.Donor;
import com.vem.atsecserver.entity.sales.Customer;
import com.vem.atsecserver.entity.sales.EnumCustomerType;
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
public class SecBarcodeGeneratorService2 {

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
    private static String destFileName = "report.pdf";


    public byte[] getBarcode() throws FileNotFoundException, JRException {
        System.out.println("generating jasper report...");

        // 1. compile template ".jrxml" file
        JasperReport jasperReport = getJasperReport();

        // 2. parameters "empty"
        Map<String, Object> parameters = getParameters();

        // 3. datasource "java object"
        JRDataSource dataSource = getDataSource();

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

        // 3. datasource "java object"
        JRDataSource dataSource = getDataSource();

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        JasperExportManager.exportReportToPdfFile(jasperPrint, destFileName);
    }

    private static JasperReport getJasperReport() throws FileNotFoundException, JRException {
        File template = ResourceUtils.getFile("classpath:report.jrxml");
        return JasperCompileManager.compileReport(template.getAbsolutePath());
    }

    private static Map<String, Object> getParameters() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "hmkcode");
        return parameters;
    }

    private static JRDataSource getDataSource() {

        List<Product> products = new LinkedList<>();

        Product product = new Product();
        Customer customer = new Customer();
        customer.setName("Volkan Ulutaş");
        customer.setCustomerType(EnumCustomerType.ABROAD_AGENT);
        customer.setDefinition("Volkan Def");
        product.setCustomer(customer);
        product.setDefinition("product açıklaması");
        Donor donor = new Donor();
        donor.setName("Donor1");
        donor.setId(1L);
        donor.setSurname("DSurname1");
        product.setDonor(donor);
        product.setSecCode("3232");
        product.setType(EnumProductType.NONE);
        product.setInformation("Info");
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
