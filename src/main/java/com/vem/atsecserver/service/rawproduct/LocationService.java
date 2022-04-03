package com.vem.atsecserver.service.rawproduct;

import com.vem.atsecserver.entity.rawproduct.EnumLocationType;
import com.vem.atsecserver.entity.rawproduct.Location;

import java.util.List;

/**
 * @author volkanulutas
 * @since 02.01.2021
 */
public interface LocationService {
    Location create(Location location);

    Location update(Location location);

    List<Location> getAllLocations();

    Location delete(Long id);

    Location findLocationById(Long id);

    List<Location> getLocationsByType(EnumLocationType valueOf);
}
