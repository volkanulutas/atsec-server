package com.vem.atsecserver.service.report.rawproduct;

import com.vem.atsecserver.data.report.rawproduct.RawProductReport;
import com.vem.atsecserver.entity.rawproduct.RawProduct;
import com.vem.atsecserver.service.rawproduct.RawProductService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RawProductReportService {

    @Autowired
    private RawProductService rawProductService;

    public byte[] exportReport(long rawProductId) throws FileNotFoundException, JRException {
        RawProduct rawProduct = rawProductService.getRawProductById(rawProductId);
        return exportReport(rawProduct);
    }

    public byte[] exportReport(RawProduct rawProduct) throws FileNotFoundException, JRException {
        RawProductReport report = new RawProductReport();
        report.setDonorId(rawProduct.getDonor().getCode());
        report.setTissueType(rawProduct.getTissueType().getName());
        report.setIssueTissueDate(rawProduct.getIssueTissueDate() + "");
        report.setArrivalDate(rawProduct.getArrivalDate() + ""); // TODO: date i string e donustur
        report.setLocation(rawProduct.getLocation().getName());
        report.setStatus(rawProduct.getStatus().getName());

        List<RawProductReport> reportList = new ArrayList<>();
        reportList.add(report);

        File file = ResourceUtils.getFile("classpath:rawproduct.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(reportList);

        Map<String, Object> parameters = new HashMap<>();
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        byte[] bytes = JasperExportManager.exportReportToPdf(jasperPrint);

        return bytes;
        // JasperExportManager.exportReportToPdfFile(jasperPrint, "/Users/volkanulutas/Documents/report/report.pdf");
    }
}
