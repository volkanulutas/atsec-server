package com.vem.atsecserver.converter;

import com.vem.atsecserver.entity.product.EnumProductStatus;
import com.vem.atsecserver.entity.product.ProductStatusDate;
import com.vem.atsecserver.payload.product.ProductStatusDateRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.text.ParseException;

/**
 * @author volkanulutas
 * @since 01.01.2021
 */
@Slf4j
@Component
public class ProductStatusDateConverter {
    public ProductStatusDate toEntity(ProductStatusDateRequest request) {
        ProductStatusDate target = new ProductStatusDate();
        target.setId(request.getId());
        target.setProductStatus(EnumProductStatus.findByName(request.getProductStatus()));
        target.setProcessDate(request.getProcessDate());
        return target;
    }

    public ProductStatusDateRequest toRequest(ProductStatusDate entity) throws ParseException {
        ProductStatusDateRequest target = new ProductStatusDateRequest();
        target.setId(entity.getId());
        target.setProductStatus(entity.getProductStatus().getName());
        target.setProcessDate(entity.getProcessDate());
        return target;
    }
}