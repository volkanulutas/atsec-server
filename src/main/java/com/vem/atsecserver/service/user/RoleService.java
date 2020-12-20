package com.vem.atsecserver.service.user;

import com.vem.atsecserver.entity.user.Role;

import java.util.List;

public interface RoleService {
    Role create(Role role);

    Role update(Role role);

    List<Role> getAllRoles();

    Role delete(Long id);

    Role findRoleById(Long id);
}
