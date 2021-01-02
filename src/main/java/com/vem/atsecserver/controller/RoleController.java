package com.vem.atsecserver.controller;

import com.vem.atsecserver.converter.RoleConverter;
import com.vem.atsecserver.entity.user.Role;
import com.vem.atsecserver.payload.auth.response.ApiResponse;
import com.vem.atsecserver.payload.exception.ResourceNotFoundException;
import com.vem.atsecserver.payload.user.RoleRequest;
import com.vem.atsecserver.service.user.RoleService;
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
@RequestMapping(path = "/api/role")
public class RoleController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleConverter roleConverter;

    @GetMapping("/{id}")
    public ResponseEntity<RoleRequest> getRoleById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(Optional.ofNullable(roleConverter.toRequest(roleService.findRoleById(id)))
                .orElseThrow(() -> new ResourceNotFoundException("Role not exists with id", id + "")));
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    public List<RoleRequest> getAllRoles() {
        List<RoleRequest> result = new ArrayList<>();
        List<Role> all = roleService.getAllRoles();
        for (Role role : all) {
            result.add(roleConverter.toRequest(role));
        }
        return result;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> create(/*@Valid*/ @RequestBody RoleRequest roleRequest) {
        Role role = roleService.create(roleConverter.toEntity(roleRequest));
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{roleId}")
                .buildAndExpand(roleRequest.getId()).toUri();
        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Role created successfully."));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> update(/*@Valid*/ @RequestBody RoleRequest roleRequest) {
        Role role = roleService.update(roleConverter.toEntity(roleRequest));
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{roleId}")
                .buildAndExpand(roleRequest.getId()).toUri();
        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Role updated successfully."));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public @ResponseBody
    ResponseEntity<?> delete(@PathVariable("id") Long id) {
        Role role = roleService.delete(id);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{roleId}")
                .buildAndExpand(id).toUri();
        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Role deleted successfully."));
    }
}