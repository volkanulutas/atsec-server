package com.vem.atsecserver.controller;

import com.vem.atsecserver.converter.ProductConverter;
import com.vem.atsecserver.entity.product.EnumProductPreProcessingType;
import com.vem.atsecserver.entity.product.Product;
import com.vem.atsecserver.payload.auth.response.ApiResponse;
import com.vem.atsecserver.payload.exception.ResourceNotFoundException;
import com.vem.atsecserver.payload.product.ProductRequest;
import com.vem.atsecserver.service.product.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author volkanulutas
 * @since 12.12.2020
 */
@RestController
@Transactional
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(path = "/api/product")
public class ProductController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductConverter productConverter;

    @GetMapping("/{id}")
    public ResponseEntity<ProductRequest> getProductById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(Optional.ofNullable(productConverter.toRequest(productService.findProductById(id)))
                .orElseThrow(() -> new ResourceNotFoundException("Product not exists with id", id + "")));
    }

    @GetMapping(value = "/", produces = "application/json")
    public List<ProductRequest> getAllProducts() {
        List<ProductRequest> result = new ArrayList<>();
        List<Product> all = productService.getAllProducts();
        for (Product product : all) {
            result.add(productConverter.toRequest(product));
        }
        return result;
    }

    @PostMapping(value = "/", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> create(/*@Valid*/ @RequestBody ProductRequest productRequest) {
        Product product = productService.create(productConverter.toEntity(productRequest));
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{productId}")
                .buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Product created successfully."));
    }

    @PutMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> update(/*@Valid*/ @RequestBody ProductRequest productRequest) {
        Product product = productService.update(productConverter.toEntity(productRequest));
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{productId}")
                .buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Product updated successfully."));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
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
}