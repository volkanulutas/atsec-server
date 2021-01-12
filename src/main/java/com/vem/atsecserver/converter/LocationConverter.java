package com.vem.atsecserver.converter;

import com.vem.atsecserver.entity.rawproduct.Location;
import com.vem.atsecserver.payload.rawproduct.LocationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author volkanulutas
 * @since 01.01.2021
 */
@Component
public class LocationConverter {
    private static final Logger LOGGER = LoggerFactory.getLogger(LocationConverter.class);

    public Location toEntity(LocationRequest request) {
        if (request == null) {
            LOGGER.error("Error is occurred while converting location.");
            return null;
        }
        Location entity = new Location();
        if (request.getId() != null) {
            entity.setId(request.getId());
        }
        entity.setDeleted(request.getDeleted());
        entity.setName(request.getName());
        entity.setDefinition(request.getDefinition());
        return entity;
    }

    public LocationRequest toRequest(Location entity) {
        if (entity == null) {
            LOGGER.error("Error is occurred while converting location.");
            return null;
        }
        LocationRequest request = new LocationRequest();
        request.setId(entity.getId());
        request.setName(entity.getName());
        request.setDefinition(entity.getDefinition());
        request.setDeleted(entity.getDeleted());
        return request;
    }
}
