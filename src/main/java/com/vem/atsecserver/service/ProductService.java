package com.vem.atsecserver.service;

import com.vem.atsecserver.entity.product.Product;

import java.util.List;

public interface ProductService {
    Product create(Product productRequest);

    Product update(Product productRequest);

    List<Product> getAllProducts();

    Product delete(Long id);

    Product findProductById(Long id);
}
