package com.vem.atsecserver.service.user;

import com.vem.atsecserver.entity.user.Permission;
import com.vem.atsecserver.repository.user.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author volkanulutas
 * @since 12.12.2020
 */
@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public Permission create(Permission permissionPar) {
        Permission permission = new Permission(permissionPar.getName(),
                permissionPar.getDefinition(), permissionPar.getMenu());
        permission.setDeleted(false);
        return permissionRepository.save(permission);
    }

    @Override
    public Permission update(Permission permissionPar) {
        Optional<Permission> byId = permissionRepository.findById(permissionPar.getId());
        if (byId.isPresent()) {
            byId.get().setName(permissionPar.getName());
            byId.get().setDefinition(permissionPar.getDefinition());
            byId.get().setDeleted(false);
            return permissionRepository.save(byId.get());
        }
        return null;
    }

    @Override
    public List<Permission> getAllPermissions() {
        return permissionRepository.findAll().stream().filter(e -> !e.getDeleted()).collect(Collectors.toList());
    }

    @Override
    public Permission delete(Long id) {
        Optional<Permission> byId = permissionRepository.findById(id);
        if (byId.isPresent()) {
            byId.get().setDeleted(true);
            return permissionRepository.save(byId.get());
        }
        return null;
    }

    @Override
    public Permission findPermissionById(Long id) {
        return permissionRepository.findById(id).get(); // TODO: get()
    }

    @Override
    public Permission findPermissionByName(String name) {
        return permissionRepository.findByName(name);
    }


}