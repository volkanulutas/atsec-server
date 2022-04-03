package com.vem.atsecserver.converter;

import com.vem.atsecserver.entity.report.product.ProductFile;
import com.vem.atsecserver.payload.product.ProductFileRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProductFileConverter {
    public ProductFile toEntity(ProductFileRequest request) {
        if (request == null) {
            log.error("Error is occurred while converting raw product.");
            return null;
        }
        ProductFile entity = new ProductFile();
        entity.setFileDBType(request.getFileDBType());
        entity.setData(request.getData());
        entity.setId(request.getId());
        entity.setName(request.getName());
        entity.setType(request.getType());
        return entity;
    }

    public ProductFileRequest toRequest(ProductFile entity) {
        if (entity == null) {
            log.error("Error is occurred while converting product.");
            return null;
        }
        ProductFileRequest request = new ProductFileRequest();
        request.setData(entity.getData());
        request.setId(entity.getId());
        request.setName(entity.getName());
        request.setFileDBType(entity.getFileDBType());
        return request;
    }
}
