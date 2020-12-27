package com.vem.atsecserver.service.user;

import com.vem.atsecserver.entity.user.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    User save(User user);

    Boolean existsByUsername(String username);

    List<User> getAllUsers();

    User findUserById(Long id);

    User delete(Long id);

    User findUserByUsername(String username);
}
