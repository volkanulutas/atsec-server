package com.vem.atsecserver.service.user;

import com.vem.atsecserver.entity.user.Role;
import com.vem.atsecserver.repository.user.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role create(Role roleRequest) {
        Role role = new Role(roleRequest.getName(), roleRequest.getDefinition());
        role.setPermissions(roleRequest.getPermissions());
        role.setDeleted(false);
        return roleRepository.save(role);
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
        return roleRepository.findAll().stream().filter(e -> !e.isDeleted()).collect(Collectors.toList());
    }

    @Override
    public Role delete(Long id) {
        Optional<Role> byId = roleRepository.findById(id);
        if (byId.isPresent()) {
            byId.get().setDeleted(true);
            roleRepository.save(byId.get());

            return byId.get();
        } else {
            LOGGER.error("Role does not found with id: {}", id);
        }
        return null;
    }

    @Override
    public Role findRoleById(Long id) {
        Optional<Role> roleOpt = roleRepository.findById(id);
        if (roleOpt.isPresent() && !roleOpt.get().isDeleted()) {
            return roleOpt.get();
        }
        return null;
    }
}
