package com.vem.atsecserver.converter;

import com.vem.atsecserver.entity.user.Permission;
import com.vem.atsecserver.payload.user.PermissionRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author volkanulutas
 * @since 01.01.2021
 */
@Slf4j
@Component
public class PermissionConverter {
    public Permission toEntity(PermissionRequest request) {
        if (request == null) {
            log.error("Error is occurred while converting permission.");
            return null;
        }
        Permission entity = new Permission();
        if (request.getId() != null) {
            entity.setId(request.getId());
        }
        entity.setMenu(request.getMenu());
        entity.setDeleted(request.isDeleted());
        entity.setName(request.getName());
        entity.setDefinition(request.getDefinition());
        return entity;
    }

    public PermissionRequest toRequest(Permission entity) {
        if (entity == null) {
            log.error("Error is occurred while converting permission.");
            return null;
        }
        PermissionRequest request = new PermissionRequest();
        request.setId(entity.getId());
        request.setName(entity.getName());
        request.setDefinition(entity.getDefinition());
        request.setMenu(entity.getMenu());
        request.setDeleted(entity.isDeleted());
        return request;
    }
}
