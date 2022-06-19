package com.vem.atsecserver.service.barcodegeneration;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import com.vem.atsecserver.data.barcodegeneration.DonorBarcode;
import com.vem.atsecserver.data.barcodegeneration.ProductBarcode;
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
public class DonorBarcodeGeneratorService {
    // name and destination of output file e.g. "report.pdf"
    private static String destFileName = "report.pdf";

    public byte[] createBarcode(DonorBarcode donorBarcode) throws FileNotFoundException, JRException {
        System.out.println("generating jasper report...");

        // 1. compile template ".jrxml" file
        JasperReport jasperReport = getJasperReport();

        // 2. parameters "empty"
        Map<String, Object> parameters = getParameters();
        // 3. datasource "java object"
        JRDataSource dataSource = getDataSource(donorBarcode);

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

        DonorBarcode donorBarcode = new DonorBarcode();
        donorBarcode.setDonorCode("1");
        donorBarcode.setStatus("Ön İşlem");

        // 3. datasource "java object"
        JRDataSource dataSource = getDataSource(donorBarcode);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        JasperExportManager.exportReportToPdfFile(jasperPrint, destFileName);
    }

    private static JasperReport getJasperReport() throws FileNotFoundException, JRException {
        File template = ResourceUtils.getFile("classpath:vem_donor.jrxml");
        return JasperCompileManager.compileReport(template.getAbsolutePath());
    }

    private static Map<String, Object> getParameters() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "hmkcode");
        return parameters;
    }

    private static JRDataSource getDataSource(DonorBarcode donorBarcode) {
        List<DonorBarcode> donorBarcodeList = new LinkedList<>();
        donorBarcodeList.add(donorBarcode);
        return new JRBeanCollectionDataSource(donorBarcodeList);
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
