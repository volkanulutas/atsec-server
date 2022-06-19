package com.vem.atsecserver.service.barcodegeneration;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
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
public class SecBarcodeGeneratorService {

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
        File template = ResourceUtils.getFile("classpath:country.jrxml");
        return JasperCompileManager.compileReport(template.getAbsolutePath());
    }

    private static Map<String, Object> getParameters() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "hmkcode");
        return parameters;
    }

    private static JRDataSource getDataSource() {

        List<Country> countries = new LinkedList<>();

        countries.add(new Country("IS", "Iceland", "https://i.pinimg.com/originals/72/b4/49/72b44927f220151547493e528a332173.png"));
        countries.add(new Country("TR", "Turkey", "https://i.pinimg.com/originals/82/63/23/826323bba32e6e5a5996062c3a3c662f.png"));
        countries.add(new Country("ZA", "South Africa", "https://i.pinimg.com/originals/f5/c7/8d/f5c78da001b46e26481c04fb93473454.png"));
        countries.add(new Country("PL", "Poland", "https://i.pinimg.com/originals/7f/ae/21/7fae21c4854010b11127218ead743863.png"));
        countries.add(new Country("CA", "Canada", "https://i.pinimg.com/originals/4d/d4/01/4dd401733ba25e6442fc8696e04e5846.png"));

        countries.add(new Country("PA", "Panama", "https://i.pinimg.com/originals/84/dc/e4/84dce49e52ebfb5b3814393069807e0a.png"));
        countries.add(new Country("HR", "Croatia", "https://i.pinimg.com/originals/f5/8c/94/f58c94a2a2b3221328fc1e2b7acfa656.png"));
        countries.add(new Country("JP", "Japan", "https://i.pinimg.com/originals/a5/d6/88/a5d688289cd6850016f14fe93b17da01.png"));
        countries.add(new Country("DE", "Germany", "https://i.pinimg.com/originals/af/c9/b2/afc9b2592a9f1cf591e8a52256ae1e9f.png"));
        countries.add(new Country("BR", "Brazil", "https://i.pinimg.com/originals/e4/03/c4/e403c4447a3bd8940459ae4f50856bed.png"));


        return new JRBeanCollectionDataSource(countries);
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
