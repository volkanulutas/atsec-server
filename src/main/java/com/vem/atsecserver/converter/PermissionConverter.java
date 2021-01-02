package com.vem.atsecserver.converter;

import com.vem.atsecserver.entity.user.Permission;
import com.vem.atsecserver.payload.user.PermissionRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author volkanulutas
 * @since 01.01.2021
 */
@Component
public class PermissionConverter {
    private static final Logger LOGGER = LoggerFactory.getLogger(PermissionConverter.class);

    public Permission toEntity(PermissionRequest request) {
        if (request == null) {
            LOGGER.error("Error is occurred while converting permission.");
            return null;
        }
        Permission entity = new Permission();
        if (request.getId() != null) {
            entity.setId(request.getId());
        }
        entity.setDeleted(request.isDeleted());
        entity.setName(request.getName());
        entity.setDefinition(request.getDefinition());
        return entity;
    }

    public PermissionRequest toRequest(Permission entity) {
        if (entity == null) {
            LOGGER.error("Error is occurred while converting permission.");
            return null;
        }
        PermissionRequest request = new PermissionRequest();
        request.setId(entity.getId());
        request.setName(entity.getName());
        request.setDefinition(entity.getDefinition());
        request.setDeleted(entity.getDeleted());
        return request;
    }
}
