package com.vem.atsecserver.controller;

import com.vem.atsecserver.converter.PermissionConverter;
import com.vem.atsecserver.entity.user.Permission;
import com.vem.atsecserver.payload.auth.response.ApiResponse;
import com.vem.atsecserver.payload.exception.ResourceNotFoundException;
import com.vem.atsecserver.payload.user.PermissionRequest;
import com.vem.atsecserver.service.user.PermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author volkanulutas
 * @since 12.12.2020
 */
@RestController
@RequestMapping(path = "/api/permission")
public class PermissionController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PermissionController.class);

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private PermissionConverter permissionConverter;

    @GetMapping("/{id}")
    public ResponseEntity<PermissionRequest> getDonorById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(Optional.ofNullable(permissionConverter.toRequest(permissionService.findPermissionById(id)))
                .orElseThrow(() -> new ResourceNotFoundException("Permission not exists with id", id + "")));
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    public List<PermissionRequest> getAllPermissions() {
        List<PermissionRequest> result = new ArrayList<>();
        List<Permission> all = permissionService.getAllPermissions();
        for (Permission permission : all) {
            result.add(permissionConverter.toRequest(permission));
        }
        return result;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> create(/*@Valid*/ @RequestBody PermissionRequest permissionRequest) {
        Permission permission = permissionService.create(permissionConverter.toEntity(permissionRequest));
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{permissionId}")
                .buildAndExpand(permission.getId()).toUri();
        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Permission created successfully."));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> update(/*@Valid*/ @RequestBody PermissionRequest permissionRequest) {
        Permission permission = permissionService.update(permissionConverter.toEntity(permissionRequest));
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{permissionId}")
                .buildAndExpand(permission.getId()).toUri();
        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Permission updated successfully."));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public @ResponseBody
    ResponseEntity<?> delete(@PathVariable("id") Long id) {
        Permission permission = permissionService.delete(id);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{permissionId}")
                .buildAndExpand(permission.getId()).toUri();
        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Permission deleted successfully."));
    }
}