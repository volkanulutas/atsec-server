package com.vem.atsecserver.controller;

import com.vem.atsecserver.converter.LocationConverter;
import com.vem.atsecserver.entity.rawproduct.Location;
import com.vem.atsecserver.payload.rawproduct.LocationRequest;
import com.vem.atsecserver.payload.auth.response.ApiResponse;
import com.vem.atsecserver.payload.exception.ResourceNotFoundException;
import com.vem.atsecserver.service.rawproduct.LocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author volkanulutas
 * @since 02.01.2021
 */
@RestController
@Transactional
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(path = "/api/location")
public class LocationController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LocationController.class);

    @Autowired
    private LocationService locationService;

    @Autowired
    private LocationConverter locationConverter;

    @GetMapping("/{id}")
    public ResponseEntity<LocationRequest> getLocationById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(Optional.ofNullable(locationConverter.toRequest(locationService.findLocationById(id)))
                .orElseThrow(() -> new ResourceNotFoundException("Location not exists with id", id + "")));
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    public List<LocationRequest> getAllLocations() {
        List<LocationRequest> result = new ArrayList<>();
        List<Location> all = locationService.getAllLocations();
        for (Location location : all) {
            result.add(locationConverter.toRequest(location));
        }
        return result;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> create(/*@Valid*/ @RequestBody LocationRequest request) {
        Location locationEntity = locationService.create(locationConverter.toEntity(request));
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{locationId}")
                .buildAndExpand(locationEntity.getId()).toUri();
        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Location created successfully."));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> update(/*@Valid*/ @RequestBody LocationRequest request) {
        Location locationEntity = locationService.update(locationConverter.toEntity(request));
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{locationId}")
                .buildAndExpand(locationEntity.getId()).toUri();
        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Location updated successfully."));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public @ResponseBody
    ResponseEntity<?> delete(@PathVariable("id") Long id) {
        Location locationEntity = locationService.delete(id);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{locationId}")
                .buildAndExpand(locationEntity.getId()).toUri();
        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Location deleted successfully."));
    }
}