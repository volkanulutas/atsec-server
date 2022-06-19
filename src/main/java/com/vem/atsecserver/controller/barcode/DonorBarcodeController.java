package com.vem.atsecserver.controller.barcode;

import com.vem.atsecserver.data.barcodegeneration.DonorBarcode;
import com.vem.atsecserver.data.barcodegeneration.ProductBarcode;
import com.vem.atsecserver.entity.product.Product;
import com.vem.atsecserver.entity.rawproduct.RawProduct;
import com.vem.atsecserver.payload.product.ProductRequest;
import com.vem.atsecserver.service.barcodegeneration.DonorBarcodeGeneratorService;
import com.vem.atsecserver.service.rawproduct.RawProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.ByteArrayInputStream;
import java.net.URI;
import java.text.ParseException;

@Slf4j
@RestController
@Transactional
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(path = "/api/donorbarcode")
public class DonorBarcodeController {

    @Autowired
    private DonorBarcodeGeneratorService donorBarcodeGeneratorService;

    @Autowired
    private RawProductService rawProductService;

    @GetMapping(value = "/{rawProductId}", produces = MediaType.APPLICATION_PDF_VALUE)
    @ResponseBody
    public ResponseEntity<InputStreamResource> createBarcode(@PathVariable String rawProductId) throws ParseException {
        byte[] report = null;
        HttpHeaders headers = null;
        DonorBarcode donorBarcode = null;
        ByteArrayInputStream inputStream = null;
        try {
            RawProduct rawProduct = rawProductService.getRawProductById(Long.valueOf(rawProductId));
            donorBarcode = new DonorBarcode();
            donorBarcode.setDonorCode(rawProduct.getDonor().getCode());
            donorBarcode.setStatus(rawProduct.getStatus().getName());
            report = donorBarcodeGeneratorService.createBarcode(donorBarcode);
            inputStream = new ByteArrayInputStream(report);
            headers = new HttpHeaders();
            headers.setContentLength(report.length);
            headers.add("Content-Disposition", "inline;filename=" + donorBarcode.getDonorCode() + "_report.pdf");
        } catch (Exception ex) {
            log.error("Error is occurred while generating donor barcode!", ex);
        }
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(inputStream));
    }
}
