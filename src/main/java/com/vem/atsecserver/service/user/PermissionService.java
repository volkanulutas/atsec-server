package com.vem.atsecserver.service.user;

import com.vem.atsecserver.entity.user.Permission;

import java.util.List;

/**
 * @author volkanulutas
 * @since 12.12.2020
 */
public interface PermissionService {
    Permission create(Permission permission);

    Permission update(Permission permission);

    List<Permission> getAllPermissions();

    Permission delete(Long id);

    Permission findPermissionById(Long id);

    Permission findPermissionByName(String name);
}
