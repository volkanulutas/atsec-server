package com.vem.atsecserver.service.rawproduct;

import com.vem.atsecserver.entity.rawproduct.EnumRawProductStatus;
import com.vem.atsecserver.entity.rawproduct.RawProduct;

import java.util.List;

/**
 * @author volkanulutas
 * @since 01.01.2021
 */
public interface RawProductService {
    RawProduct create(RawProduct rawProductRequest);

    RawProduct update(RawProduct rawProductRequest);

    List<RawProduct> getAllRawProducts();

    RawProduct delete(Long id);

    RawProduct findRawProductById(Long id);

    List<RawProduct> getRawProductsByStatus(EnumRawProductStatus status);
}
