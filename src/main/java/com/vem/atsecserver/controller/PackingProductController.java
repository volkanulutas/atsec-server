package com.vem.atsecserver.controller;

import com.vem.atsecserver.converter.PackingProductConverter;
import com.vem.atsecserver.entity.packingproduct.EnumPackingProductSize;
import com.vem.atsecserver.entity.packingproduct.PackingProduct;
import com.vem.atsecserver.payload.auth.response.ApiResponse;
import com.vem.atsecserver.payload.exception.ResourceNotFoundException;
import com.vem.atsecserver.payload.packingproduct.PackingProductRequest;
import com.vem.atsecserver.service.packingproduct.PackingProductService;
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
@RequestMapping(path = "/api/packingproduct")
// @Secured("PRODUCT_PAGE_PERMISSION")
public class PackingProductController {
    @Autowired
    private PackingProductService packingProductService;

    @Autowired
    private PackingProductConverter packingProductConverter;

    @GetMapping("/{id}")
    public ResponseEntity<PackingProductRequest> getProductById(@PathVariable("id") Long id) throws ParseException {
        return ResponseEntity.ok(Optional.ofNullable(packingProductConverter.toRequest(packingProductService.findProductById(id)))
                .orElseThrow(() -> new ResourceNotFoundException("Product not exists with id", id + "")));
    }

    @GetMapping(value = "/", produces = "application/json")
    public List<PackingProductRequest> getAllProducts() throws ParseException {
        List<PackingProductRequest> result = new ArrayList<>();
        List<PackingProduct> all = packingProductService.getAllPackingProducts();
        for (PackingProduct product : all) {
            result.add(packingProductConverter.toRequest(product));
        }
        return result;
    }

    @PostMapping(value = "/", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> create(/*@Valid*/ @RequestBody PackingProductRequest productRequest) {
        try {
            PackingProduct product = packingProductService.create(packingProductConverter.toEntity(productRequest));
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
    public ResponseEntity<?> update(/*@Valid*/ @RequestBody PackingProductRequest productRequest) {
        System.err.println(productRequest.toString());
        System.err.println("update");
        PackingProduct product = packingProductService.update(packingProductConverter.toEntity(productRequest));
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
        PackingProduct product = packingProductService.delete(id);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{productId}")
                .buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Product deleted successfully."));
    }

    @GetMapping(value = "/packingproductsize", produces = "application/json")
    public List<String> getPackingProductSize() {
        return EnumPackingProductSize.valuesByCode();
    }
}