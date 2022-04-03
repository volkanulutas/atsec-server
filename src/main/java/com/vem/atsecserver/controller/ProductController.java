package com.vem.atsecserver.controller;

import com.vem.atsecserver.converter.ProductConverter;
import com.vem.atsecserver.data.barcodegeneration.ProductBarcode;
import com.vem.atsecserver.entity.packingproduct.EnumPackingProductSize;
import com.vem.atsecserver.entity.product.EnumProductPreProcessingType;
import com.vem.atsecserver.entity.product.EnumWashingType;
import com.vem.atsecserver.entity.product.Product;
import com.vem.atsecserver.payload.auth.response.ApiResponse;
import com.vem.atsecserver.payload.exception.ResourceNotFoundException;
import com.vem.atsecserver.payload.product.ProductRequest;
import com.vem.atsecserver.service.barcodegeneration.SecBarcodeGeneratorService2;
import com.vem.atsecserver.service.product.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author volkanulutas
 * @since 12.12.2020
 */
@Slf4j
@RestController
@Transactional
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(path = "/api/product")
// @Secured("PRODUCT_PAGE_PERMISSION")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductConverter productConverter;

    @Autowired
    private SecBarcodeGeneratorService2 secBarcodeGeneratorService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductRequest> getProductById(@PathVariable("id") Long id) throws ParseException {
        return ResponseEntity.ok(Optional.ofNullable(productConverter.toRequest(productService.findProductById(id)))
                .orElseThrow(() -> new ResourceNotFoundException("Product not exists with id", id + "")));
    }

    @GetMapping(value = "/", produces = "application/json")
    public List<ProductRequest> getAllProducts() throws ParseException {
        List<ProductRequest> result = new ArrayList<>();
        List<Product> all = productService.getAllProducts();
        for (Product product : all) {
            result.add(productConverter.toRequest(product));
        }
        return result;
    }

    @GetMapping(value = "/preprocessing", produces = "application/json")
    public List<ProductRequest> getAllPreProcessingProducts() throws ParseException {
        List<ProductRequest> result = new ArrayList<>();
        List<Product> all = productService.getAllPreProcessingProducts();
        for (Product product : all) {
            result.add(productConverter.toRequest(product));
        }
        return result;
    }


    @GetMapping(value = "/packing", produces = "application/json")
    public List<ProductRequest> getAllPackingProducts() throws ParseException {
        System.err.println("packing...");
        List<ProductRequest> result = new ArrayList<>();
        List<Product> all = productService.getAllPackingProducts();
        for (Product product : all) {
            result.add(productConverter.toRequest(product));
        }
        return result;
    }

    @GetMapping("/createbarcode/{productId}")
    @ResponseBody
    public byte[] createBarcode(@PathVariable String productId) throws ParseException {
        ProductRequest productRequest = productConverter.toRequest(productService.findProductById(Long.parseLong(productId)));

        byte[] barcode = null;
        Product product = productService.update(productConverter.toEntity(productRequest));

        ProductBarcode productBarcode = new ProductBarcode();
        productBarcode.setStatus(product.getStatus().toString());
        productBarcode.setDonorId(product.getDonor().getCode());
        productBarcode.setDefinition(product.getDefinition());
        productBarcode.setSecCode(product.getSecCode());
        productBarcode.setId(product.getId());
        try {
            barcode = secBarcodeGeneratorService.createBarcode(productBarcode);
        } catch (Exception ex) {
            log.error("Error is occurred while generating sec barcode code.", ex);
        }
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{productId}")
                .buildAndExpand(product.getId()).toUri();
        return barcode;
    }

    @PostMapping(value = "/", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> create(/*@Valid*/ @RequestBody ProductRequest productRequest) {
        try {
            Product product = productService.create(productConverter.toEntity(productRequest));
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{productId}")
                    .buildAndExpand(product.getId()).toUri();
            System.err.println("basari");
            return ResponseEntity.created(location)
                    .body(new ApiResponse(true, "Product created successfully."));
        } catch (Exception ex) {
            System.err.println("Hata");
            log.error("Hata oluştu: ", ex);
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> update(/*@Valid*/ @RequestBody ProductRequest productRequest) {
        System.err.println(productRequest.toString());
        System.err.println("update");
        Product product = productService.update(productConverter.toEntity(productRequest));
        System.err.println(product.toString());
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{productId}")
                .buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Product updated successfully."));
    }

    @DeleteMapping(value = "/{id}")
    public @ResponseBody
    ResponseEntity<?> delete(@PathVariable("id") Long id) {
        Product product = productService.delete(id);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{productId}")
                .buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Product deleted successfully."));
    }

    @GetMapping(value = "/preprocessingtypelist", produces = "application/json")
    public List<String> getPreProcessingTypeList() {
        return EnumProductPreProcessingType.valuesByName();
    }

    @GetMapping(value = "/washingtypelist", produces = "application/json")
    public List<String> getWashingTypeList() {
        return EnumWashingType.valuesByName();
    }

    @GetMapping(value = "/packingproductsize", produces = "application/json")
    public List<String> getPackingProductSize() {
        return EnumPackingProductSize.valuesByCode();
    }
}