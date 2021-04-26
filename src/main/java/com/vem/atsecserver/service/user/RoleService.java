package com.vem.atsecserver.service.user;

import com.vem.atsecserver.entity.user.Role;
import com.vem.atsecserver.entity.user.User;

import java.util.List;

/**
 * @author volkanulutas
 * @since 01.01.2021
 */
public interface RoleService {
    Role create(Role role);

    Role update(Role role);

    List<Role> getAllRoles();

    Role delete(Long id);

    Role findRoleById(Long id);

    Role findRoleByName(String name);

    List<User> getAdminRoleUsers();
}
