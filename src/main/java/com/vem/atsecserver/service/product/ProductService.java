package com.vem.atsecserver.service.product;

import com.vem.atsecserver.entity.product.Product;

import java.util.List;

/**
 * @author volkanulutas
 * @since 01.01.2021
 */
public interface ProductService {
    Product create(Product productRequest);

    Product update(Product productRequest);

    List<Product> getAllProducts();

    Product delete(Long id);

    Product findProductById(Long id);
}
