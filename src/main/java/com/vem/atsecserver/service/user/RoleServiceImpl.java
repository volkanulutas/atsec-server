package com.vem.atsecserver.service.user;

import com.vem.atsecserver.entity.user.Permission;
import com.vem.atsecserver.entity.user.Role;
import com.vem.atsecserver.entity.user.User;
import com.vem.atsecserver.repository.user.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author volkanulutas
 * @since 01.01.2021
 */
@Slf4j
@Service
public class RoleServiceImpl implements RoleService {
    private final static String ADMIN_ROLE = "ADMIN_ROLE";

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role create(Role roleRequest) {
        Role byName = roleRepository.findByName(roleRequest.getName());
        if (byName == null) {
            Role role = new Role(roleRequest.getName(), roleRequest.getDefinition());
            List<Permission> permissionList = new ArrayList<>();
            for (Permission p : roleRequest.getPermissions()) {
                permissionList.add(permissionService.findPermissionByName(p.getName()));
            }
            role.setPermissions(permissionList);
            role.setDeleted(false);
            return roleRepository.save(role);
        }
        return byName;
    }

    @Override
    public Role update(Role roleRequest) {
        Optional<Role> byId = roleRepository.findById(roleRequest.getId());
        if (byId.isPresent()) {
            byId.get().setName(roleRequest.getName());
            byId.get().setDefinition(roleRequest.getDefinition());
            byId.get().setPermissions(roleRequest.getPermissions());
            return roleRepository.save(byId.get());
        }
        return null;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll().stream().filter(e -> !e.getDeleted()).collect(Collectors.toList());
    }

    @Override
    public Role delete(Long id) {
        Optional<Role> byId = roleRepository.findById(id);
        if (byId.isPresent()) {
            byId.get().setDeleted(true);
            roleRepository.save(byId.get());

            return byId.get();
        } else {
            log.error("Role does not found with id: {}", id);
        }
        return null;
    }

    @Override
    public Role findRoleById(Long id) {
        Optional<Role> roleOpt = roleRepository.findById(id);
        if (roleOpt.isPresent() && !roleOpt.get().getDeleted()) {
            return roleOpt.get();
        }
        return null;
    }

    @Override
    public Role findRoleByName(String name) {
        Role role = roleRepository.findByName(name);
        if (role != null && !role.getDeleted()) {
            return role;
        }
        return null;
    }

    @Override
    public List<User> getAdminRoleUsers() {
        Role role = roleRepository.findByName(ADMIN_ROLE);
        return (List<User>) role.getUsers();
    }
}
