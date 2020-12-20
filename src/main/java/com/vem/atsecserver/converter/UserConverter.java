package com.vem.atsecserver.converter;

import com.vem.atsecserver.entity.user.Role;
import com.vem.atsecserver.entity.user.User;
import com.vem.atsecserver.payload.user.UserRequest;
import com.vem.atsecserver.service.user.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserConverter.class);

    @Autowired
    private RoleService roleService;

    public User toEntity(UserRequest request) {
        if (request == null) {
            LOGGER.error("Error is occurred while converting user.");
            return null;
        }
        User user = new User();
        if (request.getId() != null) {
            user.setId(request.getId());
        }
        user.setDeleted(request.isDeleted());
        user.setEnabled(request.isEnabled());
        user.setName(request.getName());
        user.setSurname(request.getSurname());
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        Role roleById = roleService.findRoleById(Long.parseLong(request.getRole()));
        if (roleById != null) {
            user.setRole(roleById);
        }
        return user;
    }

    public UserRequest toRequest(User entity) {
        if (entity == null) {
            LOGGER.error("Error is occurred while converting user.");
            return null;
        }
        UserRequest request = new UserRequest();
        request.setId(entity.getId());
        request.setName(entity.getName());
        request.setSurname(entity.getSurname());
        request.setUsername(entity.getUsername());
        request.setPassword(entity.getPassword());
        request.setDeleted(entity.isDeleted());
        request.setEnabled(entity.isEnabled());
        if (entity.getRole() != null) {
            request.setRole(entity.getRole().getId() + "");
        }
        return request;
    }
}
