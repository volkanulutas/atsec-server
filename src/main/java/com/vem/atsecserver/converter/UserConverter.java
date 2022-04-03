package com.vem.atsecserver.converter;

import com.vem.atsecserver.entity.user.Role;
import com.vem.atsecserver.entity.user.User;
import com.vem.atsecserver.payload.user.UserRequest;
import com.vem.atsecserver.service.user.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author volkanulutas
 * @since 01.01.2021
 */
@Slf4j
@Component
public class UserConverter {
    @Autowired
    private RoleService roleService;

    public User toEntity(UserRequest request) {
        if (request == null) {
            log.error("Error is occurred while converting user.");
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
        if (request.getRole() != null) {
            Role roleById = roleService.findRoleById(Long.parseLong(request.getRole()));
            if (roleById != null) {
                user.setRole(roleById);
            }
        }
        return user;
    }

    public UserRequest toRequest(User entity) {
        if (entity == null) {
            log.error("Error is occurred while converting user.");
            return null;
        }
        UserRequest request = new UserRequest();
        request.setId(entity.getId());
        request.setName(entity.getName());
        request.setSurname(entity.getSurname());
        request.setUsername(entity.getUsername());
        request.setPassword(entity.getPassword());
        request.setDeleted(entity.getDeleted());
        request.setEnabled(entity.getEnabled());
        if (entity.getRole() != null) {
            request.setRole(entity.getRole().getId() + "");
        }
        return request;
    }
}
