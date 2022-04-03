package com.vem.atsecserver.service.report.product;

import com.vem.atsecserver.data.report.product.ProductReport;
import com.vem.atsecserver.entity.product.Product;
import com.vem.atsecserver.entity.report.product.ProductFile;
import com.vem.atsecserver.repository.file.product.ProductFileDBRepository;
import com.vem.atsecserver.service.product.ProductService;
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
public class ProductReportService {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductFileDBRepository productFileDBRepository;

    public ProductFile save(ProductFile productFile) {
        return productFileDBRepository.save(productFile);
    }

    public byte[] exportReport(Product product) throws FileNotFoundException, JRException {
        try {
            ProductReport report = new ProductReport();
            report.setStatus(product.getStatus().toString());
            report.setCustomerId(product.getCustomer().getName());
            report.setDonorId(product.getDonor().getCode() + "");
            report.setSecCode(product.getSecCode());

            List<ProductReport> reportList = new ArrayList<>();
            reportList.add(report);

            File file = ResourceUtils.getFile("classpath:report_Product.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(reportList);

            Map<String, Object> parameters = new HashMap<>();

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

            byte[] bytes = JasperExportManager.exportReportToPdf(jasperPrint);


            // JasperExportManager.exportReportToPdfFile(jasperPrint, "/Users/volkanulutas/Documents/report/report.pdf");
            return bytes;
        } catch (Exception ex) {
            System.err.println("Error creating product barcode...");
        }
        return null;
    }


    public byte[] exportReport(long rawProductId) throws FileNotFoundException, JRException {
        Product rawProduct = productService.findProductById(rawProductId);
        return exportReport(rawProduct);
    }
}
