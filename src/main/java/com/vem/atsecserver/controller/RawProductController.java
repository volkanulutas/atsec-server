package com.vem.atsecserver.controller;

import com.vem.atsecserver.converter.RawProductConverter;
import com.vem.atsecserver.entity.rawproduct.RawProduct;
import com.vem.atsecserver.payload.auth.response.ApiResponse;
import com.vem.atsecserver.payload.exception.ResourceNotFoundException;
import com.vem.atsecserver.payload.rawproduct.RawProductRequest;
import com.vem.atsecserver.service.RawProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author volkanulutas
 * @since 25.12.2020
 */
@RestController
@RequestMapping(path = "/api/rawproduct")
public class RawProductController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RawProductController.class);

    @Autowired
    private RawProductService rawProductService;

    @Autowired
    private RawProductConverter rawProductConverter;

    @GetMapping("/location")
    public ResponseEntity<List<String>> getProductLocations() {
        return ResponseEntity.ok(Optional.ofNullable(rawProductService.getRawProductLocations())
                .orElseThrow(() -> new ResourceNotFoundException("Product locations not exists", "")));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RawProductRequest> getRawProductById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(Optional.ofNullable(rawProductConverter.toRequest(rawProductService.findRawProductById(id)))
                .orElseThrow(() -> new ResourceNotFoundException("Product not exists with id", id + "")));
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    public List<RawProductRequest> getAllProducts() {
        List<RawProductRequest> result = new ArrayList<>();
        List<RawProduct> all = rawProductService.getAllRawProducts();
        for (RawProduct product : all) {
            result.add(rawProductConverter.toRequest(product));
        }
        return result;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> create(/*@Valid*/ @RequestBody RawProductRequest productRequest) {
        RawProduct product = rawProductService.create(rawProductConverter.toEntity(productRequest));
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{productId}")
                .buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Product created successfully."));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> update(/*@Valid*/ @RequestBody RawProductRequest productRequest) {
        RawProduct product = rawProductService.update(rawProductConverter.toEntity(productRequest));
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{productId}")
                .buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Product updated successfully."));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public @ResponseBody
    ResponseEntity<?> delete(@PathVariable("id") Long id) {
        RawProduct product = rawProductService.delete(id);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{productId}")
                .buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Product deleted successfully."));
    }
}