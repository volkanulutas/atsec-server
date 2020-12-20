package com.vem.atsecserver.converter;

import com.vem.atsecserver.entity.user.Permission;
import com.vem.atsecserver.entity.user.Role;
import com.vem.atsecserver.payload.user.PermissionRequest;
import com.vem.atsecserver.payload.user.RoleRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RoleConverter {
    private static final Logger LOGGER = LoggerFactory.getLogger(RoleConverter.class);

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
        request.setDeleted(entity.isDeleted());
        request.setId(entity.getId());
        request.setDeleted(entity.isDeleted());

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
