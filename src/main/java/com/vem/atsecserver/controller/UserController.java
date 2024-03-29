package com.vem.atsecserver.controller;

import com.vem.atsecserver.converter.UserConverter;
import com.vem.atsecserver.entity.user.User;
import com.vem.atsecserver.payload.auth.response.ApiResponse;
import com.vem.atsecserver.payload.exception.ResourceNotFoundException;
import com.vem.atsecserver.payload.user.UserRequest;
import com.vem.atsecserver.service.user.RoleService;
import com.vem.atsecserver.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author volkanulutas
 * @since 12.12.2020
 */
@Slf4j
@RestController
@Transactional
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(path = "/api/user")
// @Secured("USER_PAGE_PERMISSION")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserConverter userConverter;

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    public List<UserRequest> getAllUsers() {
        List<UserRequest> result = new ArrayList<>();
        List<User> allUsers = userService.getAllUsers();
        for (User user : allUsers) {
            result.add(userConverter.toRequest(user));
        }
        return result;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserRequest> getUserById(@PathVariable("id") Long id) {
        ResponseEntity<UserRequest> res = ResponseEntity.ok(Optional.ofNullable(userConverter.toRequest(userService.findUserById(id)))
                .orElseThrow(() -> new ResourceNotFoundException("User not exists with id", id + "")));
        return res;
    }

    @PutMapping(value = "/{id}", produces = "application/json", consumes =  "application/json")
    public ResponseEntity<UserRequest> updateUser(@PathVariable("id") Long id, @RequestBody UserRequest userParam) {
        User save = userService.save(userConverter.toEntity(userParam));
        return ResponseEntity.ok(userConverter.toRequest(save));
    }

    @PostMapping(value = "/", produces = "application/json", consumes =  "application/json")
    public @ResponseBody ResponseEntity<?> create(/*@Valid*/ @RequestBody UserRequest userRequest) {
        if (userService.existsByUsername(userRequest.getUsername())) {
            return new ResponseEntity<>(new ApiResponse(false, "Username is already taken!"), HttpStatus.BAD_REQUEST);
        }
        if (userService.existsByUsername(userRequest.getUsername())) {
            return new ResponseEntity<>(new ApiResponse(false, "Error: Email is already in use!"), HttpStatus.BAD_REQUEST);
        }
        userService.save(userConverter.toEntity(userRequest));
        return new ResponseEntity<>(new ApiResponse(true, "User registered successfully!"), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public @ResponseBody
    ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userConverter.toRequest(userService.delete(id)));
    }
}
