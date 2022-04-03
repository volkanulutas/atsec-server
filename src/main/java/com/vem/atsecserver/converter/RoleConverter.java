package com.vem.atsecserver.converter;

import com.vem.atsecserver.entity.user.Permission;
import com.vem.atsecserver.entity.user.Role;
import com.vem.atsecserver.payload.user.PermissionRequest;
import com.vem.atsecserver.payload.user.RoleRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author volkanulutas
 * @since 01.01.2021
 */
@Slf4j
@Component
public class RoleConverter {
    @Autowired
    private PermissionConverter permissionConverter;

    public Role toEntity(RoleRequest request) {
        Role role = new Role();
        if (request.getId() != null) {
            role.setId(request.getId());
        }
        role.setName(request.getName());
        role.setDefinition(request.getDefinition());
        role.setDeleted(request.isDeleted());

        List<Permission> roles = new ArrayList<>();
        for (PermissionRequest per : request.getPermissions()) {
            roles.add(permissionConverter.toEntity(per));
        }
        role.setPermissions(roles);
        return role;
    }

    public RoleRequest toRequest(Role entity) {
        RoleRequest request = new RoleRequest();
        request.setName(entity.getName());
        request.setDefinition(entity.getDefinition());
        request.setDeleted(entity.getDeleted());
        request.setId(entity.getId());
        request.setDeleted(entity.getDeleted());

        /*
        List<UserRequest> users = new ArrayList<>();
        for (User user : entity.getUsers()) {
            users.add(UserConverter.toRequest(user));
        }
        request.setUsers(users);
         */

        List<PermissionRequest> permissions = new ArrayList<>();
        for (Permission permission : entity.getPermissions()) {
            permissions.add(permissionConverter.toRequest(permission));
        }
        request.setPermissions(permissions);

        return request;
    }
}
