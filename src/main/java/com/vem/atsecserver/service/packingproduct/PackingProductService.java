package com.vem.atsecserver.service.packingproduct;

import com.vem.atsecserver.entity.packingproduct.PackingProduct;

import java.util.List;

/**
 * @author volkanulutas
 * @since 01.01.2021
 */
public interface PackingProductService {
    PackingProduct create(PackingProduct packingProduct);

    PackingProduct update(PackingProduct packingProduct);

    List<PackingProduct> getAllPackingProducts();

    PackingProduct delete(Long id);

    PackingProduct findProductById(Long id);
}
