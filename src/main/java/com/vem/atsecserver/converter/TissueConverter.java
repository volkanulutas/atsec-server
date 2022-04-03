package com.vem.atsecserver.converter;

import com.vem.atsecserver.entity.rawproduct.TissueType;
import com.vem.atsecserver.payload.rawproduct.TissueTypeRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author volkanulutas
 * @since 02.01.2021
 */
@Slf4j
@Component
public class TissueConverter {
    public TissueType toEntity(TissueTypeRequest request) {
        if (request == null) {
            log.error("Error is occurred while converting tissue type.");
            return null;
        }
        TissueType entity = new TissueType();
        if (request.getId() != null) {
            entity.setId(request.getId());
        }
        entity.setDeleted(request.getDeleted());
        entity.setName(request.getName());
        entity.setDefinition(request.getDefinition());
        return entity;
    }

    public TissueTypeRequest toRequest(TissueType entity) {
        if (entity == null) {
            log.error("Error is occurred while converting tissue type.");
            return null;
        }
        TissueTypeRequest request = new TissueTypeRequest();
        request.setId(entity.getId());
        request.setName(entity.getName());
        request.setDefinition(entity.getDefinition());
        request.setDeleted(entity.getDeleted());
        return request;
    }
}
