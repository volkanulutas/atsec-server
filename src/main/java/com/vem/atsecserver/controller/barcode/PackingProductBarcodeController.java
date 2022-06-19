package com.vem.atsecserver.controller.barcode;

import com.vem.atsecserver.data.barcodegeneration.PackingPackingProductBarcode;
import com.vem.atsecserver.data.barcodegeneration.ProductBarcode;
import com.vem.atsecserver.entity.packingproduct.PackingProduct;
import com.vem.atsecserver.entity.product.Product;
import com.vem.atsecserver.entity.rawproduct.RawProduct;
import com.vem.atsecserver.payload.product.ProductRequest;
import com.vem.atsecserver.service.barcodegeneration.PackingProductBarcodeGeneratorService;
import com.vem.atsecserver.service.packingproduct.PackingProductService;
import com.vem.atsecserver.service.rawproduct.RawProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.text.ParseException;

@Slf4j
@RestController
@Transactional
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(path = "/api/packingproductbarcode")
public class PackingProductBarcodeController {

    @Autowired
    private PackingProductService packingProductService;

    @Autowired
    private PackingProductBarcodeGeneratorService packingProductBarcodeGeneratorService;

    @GetMapping(value = "/{packingProductId}", produces = MediaType.APPLICATION_PDF_VALUE)
    @ResponseBody
    public ResponseEntity<InputStreamResource> createBarcode(@PathVariable String packingProductId) throws ParseException {
        byte[] report = null;
        ByteArrayInputStream inputStream =null;
        PackingPackingProductBarcode packingProductBarcode =null;
                HttpHeaders headers = new HttpHeaders();
        try {
            PackingProduct packingProduct = packingProductService.findProductById(Long.valueOf(packingProductId));
            packingProductBarcode = new PackingPackingProductBarcode();
            packingProductBarcode.setSize(packingProduct.getPackingProduct().getCode() + " - " + packingProduct.getPackingProduct().getSize());
            packingProductBarcode.setLot(packingProduct.getLot());
            packingProductBarcode.setSerialNumber(packingProduct.getSerialNumber());
            packingProductBarcode.setDonorCode(packingProduct.getDonor().getCode());
            report = packingProductBarcodeGeneratorService.createBarcode(packingProductBarcode);

            inputStream =  new ByteArrayInputStream(report);

            headers.setContentLength(report.length);
            headers.add("Content-Disposition", "inline;filename=" + packingProduct.getSerialNumber() + "_report.pdf");
        } catch (Exception ex) {
            log.error("Error is occurred while creating packing product barcode. ", ex);
        }
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(inputStream));
    }
}
