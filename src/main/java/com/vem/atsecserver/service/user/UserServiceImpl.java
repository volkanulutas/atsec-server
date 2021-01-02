package com.vem.atsecserver.service.user;

import com.vem.atsecserver.auth.security.UserPrincipal;
import com.vem.atsecserver.entity.user.User;
import com.vem.atsecserver.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author volkanulutas
 * @since 01.01.2021
 */
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Override
    public User save(User userParam) {
        User user = findUserByUsername(userParam.getUsername());
        if (user == null) { // new user
            user = new User(); // update
            user.setPassword(passwordEncoder.encode(userParam.getPassword()));
        } else {
            if (!userParam.getPassword().equals("")) { //TODO: review et
                user.setPassword(passwordEncoder.encode(userParam.getPassword()));
            }
        }
        user.setUsername(userParam.getUsername());
        user.setName(userParam.getName());
        user.setSurname(userParam.getSurname());
        user.setUsername(userParam.getUsername());
        user.setRole(userParam.getRole());
        user.setEnabled(userParam.getEnabled());
        user.setDeleted(false);
        return userRepository.save(user);
    }

    @Override
    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsernameIgnoreCase(username);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll().stream().filter(e -> !e.getDeleted()).collect(Collectors.toList());
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id).get(); // TODO: iyileştir
    }

    @Override
    public User delete(Long id) {
        User result = null;
        Optional<User> byId = userRepository.findById(id);
        if (byId.isPresent()) {
            result = byId.get();
            result.setDeleted(true);
            result = userRepository.save(result);
        }
        return result;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User is not found!");
        }
        return UserPrincipal.create(user);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}