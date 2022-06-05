package com.vem.atsecserver.converter;

import com.vem.atsecserver.entity.product.EnumProductPreProcessingType;
import com.vem.atsecserver.entity.product.PreProcessingType;
import com.vem.atsecserver.entity.product.Product;
import com.vem.atsecserver.payload.product.PreProcessingTypeRequest;
import com.vem.atsecserver.payload.product.ProductRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class PreProcessingTypeConverter {

    public PreProcessingType toEntity(PreProcessingTypeRequest source) {
        PreProcessingType target = new PreProcessingType();
        target.setPreProcessingType(EnumProductPreProcessingType.findByName(source.getPreProcessingType()));
        target.setProcessingDate(source.getProcessingDate());
        return target;
    }

    public PreProcessingTypeRequest toRequest(PreProcessingType source) {
        try {
            PreProcessingTypeRequest target = new PreProcessingTypeRequest();
            target.setPreProcessingType(source.getPreProcessingType().toString());
            target.setProcessingDate(source.getProcessingDate());
            return target;
        } catch (Exception ex) {
            log.error("PreProcessingType conversion error.");
        }
        return null;
    }
}
