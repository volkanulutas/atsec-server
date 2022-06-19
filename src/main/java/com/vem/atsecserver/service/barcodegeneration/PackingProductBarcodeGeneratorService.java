package com.vem.atsecserver.service.barcodegeneration;

import com.vem.atsecserver.data.barcodegeneration.PackingPackingProductBarcode;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author volkanulutas
 * @since 12.12.2020
 */
@Service
public class PackingProductBarcodeGeneratorService {
    // name and destination of output file e.g. "report.pdf"
    private static String destFileName = "report.pdf";

    public byte[] createBarcode(PackingPackingProductBarcode barcode) throws FileNotFoundException, JRException {
        System.out.println("generating jasper report...");

        // 1. compile template ".jrxml" file
        JasperReport jasperReport = getJasperReport();

        // 2. parameters "empty"
        Map<String, Object> parameters = getParameters();
        // 3. datasource "java object"
        JRDataSource dataSource = getDataSource(barcode);

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

        PackingPackingProductBarcode barcode = new PackingPackingProductBarcode();
        barcode.setDonorCode("1");
        barcode.setLot("Lot");
        barcode.setSerialNumber("seri no");
        barcode.setSize("size");
        // 3. datasource "java object"
        JRDataSource dataSource = getDataSource(barcode);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        JasperExportManager.exportReportToPdfFile(jasperPrint, destFileName);
    }

    private static JasperReport getJasperReport() throws FileNotFoundException, JRException {
        File template = ResourceUtils.getFile("classpath:vem_packingproduct.jrxml");
        return JasperCompileManager.compileReport(template.getAbsolutePath());
    }

    private static Map<String, Object> getParameters() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "vulutas");
        return parameters;
    }

    private static JRDataSource getDataSource(PackingPackingProductBarcode barcode) {
        List<PackingPackingProductBarcode> donorBarcodeList = new LinkedList<>();
        donorBarcodeList.add(barcode);
        return new JRBeanCollectionDataSource(donorBarcodeList);
    }
}
