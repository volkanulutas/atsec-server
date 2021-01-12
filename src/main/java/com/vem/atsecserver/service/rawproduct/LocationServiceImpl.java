package com.vem.atsecserver.service.rawproduct;

import com.vem.atsecserver.entity.rawproduct.Location;
import com.vem.atsecserver.repository.rawproduct.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author volkanulutas
 * @since 02.01.2021
 */
@Service
public class LocationServiceImpl implements LocationService {
    @Autowired
    private LocationRepository locationRepository;

    @Override
    public Location create(Location parameter) {
        Location location = new Location(parameter.getName(), parameter.getDefinition());
        location.setDeleted(false);
        return locationRepository.save(location);
    }

    @Override
    public Location update(Location parameter) {
        Optional<Location> byId = locationRepository.findById(parameter.getId());
        if (byId.isPresent()) {
            byId.get().setName(parameter.getName());
            byId.get().setDefinition(parameter.getDefinition());
            byId.get().setDeleted(false);
            return locationRepository.save(byId.get());
        }
        return null;
    }

    @Override
    public List<Location> getAllLocations() {
        return locationRepository.findAll().stream().filter(e -> !e.getDeleted()).collect(Collectors.toList());
    }

    @Override
    public Location delete(Long id) {
        Optional<Location> byId = locationRepository.findById(id);
        if (byId.isPresent()) {
            byId.get().setDeleted(true);
            return locationRepository.save(byId.get());
        }
        return null;
    }

    @Override
    public Location findLocationById(Long id) {
        return locationRepository.findById(id).get(); // TODO: get()
    }
}