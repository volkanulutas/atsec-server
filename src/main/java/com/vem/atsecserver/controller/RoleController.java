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
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
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
@Transactional
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(path = "/api/role")
// @Secured("ROLE_PAGE_PERMISSION")
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

    @PostMapping(value = "/", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> create(/*@Valid*/ @RequestBody RoleRequest roleRequest) {
        Role role = roleService.create(roleConverter.toEntity(roleRequest));
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{roleId}")
                .buildAndExpand(roleRequest.getId()).toUri();
        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Role created successfully."));
    }

    @PutMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> update(/*@Valid*/ @RequestBody RoleRequest roleRequest) {
        Role role = roleService.update(roleConverter.toEntity(roleRequest));
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{roleId}")
                .buildAndExpand(roleRequest.getId()).toUri();
        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Role updated successfully."));
    }

    @DeleteMapping(value = "/{id}")
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