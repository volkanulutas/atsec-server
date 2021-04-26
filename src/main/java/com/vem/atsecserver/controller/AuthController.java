package com.vem.atsecserver.controller;

import com.vem.atsecserver.auth.security.JwtTokenProvider;
import com.vem.atsecserver.auth.security.UserPrincipal;
import com.vem.atsecserver.converter.PermissionConverter;
import com.vem.atsecserver.converter.UserConverter;
import com.vem.atsecserver.data.mail.Email;
import com.vem.atsecserver.entity.auth.ConfirmationToken;
import com.vem.atsecserver.entity.user.Permission;
import com.vem.atsecserver.entity.user.User;
import com.vem.atsecserver.payload.auth.request.ChangePasswordRequest;
import com.vem.atsecserver.payload.auth.request.LoginRequest;
import com.vem.atsecserver.payload.auth.response.ApiResponse;
import com.vem.atsecserver.payload.auth.response.JwtResponse;
import com.vem.atsecserver.payload.exception.EmailNotFoundException;
import com.vem.atsecserver.payload.user.PermissionRequest;
import com.vem.atsecserver.payload.user.UserRequest;
import com.vem.atsecserver.service.auth.ConfirmationTokenService;
import com.vem.atsecserver.service.EmailSenderService;
import com.vem.atsecserver.service.user.AuthService;
import com.vem.atsecserver.service.user.PermissionService;
import com.vem.atsecserver.service.user.RoleService;
import com.vem.atsecserver.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author volkanulutas
 * @since 12.12.2020
 */
@RestController
@Transactional
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ConfirmationTokenService confirmationTokenService;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private  PermissionConverter permissionConverter;

    @Autowired
    private JwtTokenProvider jwtUtils;

    @Autowired
    private EmailSenderService emailSenderService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserPrincipal userDetails = (UserPrincipal) authentication.getPrincipal();
        List<PermissionRequest> permissionReqList = new ArrayList<>();
        if (userDetails.getAuthorities() != null) {
            List<String> permissions = userDetails.getAuthorities().stream()
                    .map(item -> item.getAuthority())
                    .collect(Collectors.toList());
            for (String per: permissions) {
                Permission permissionByName = permissionService.findPermissionByName(per);
                permissionReqList.add(permissionConverter.toRequest(permissionByName));
            }
        }
        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                permissionReqList));
    }

    @PostMapping(value = "/change-password")
    public ResponseEntity<?> changeUserPassword(@RequestBody ChangePasswordRequest changePasswordRequest) {
        return ResponseEntity.ok(authService.changeUserPassword(changePasswordRequest.getToken(), changePasswordRequest.getPassword()));
    }

    /**
     * Receive email of the user, create token and send it via email to the user
     */
    @PostMapping(value = "/forget-password")
    public ResponseEntity<?> forgotUserPassword(@RequestBody UserRequest userRequest) {
        User user = userService.findUserByUsername(userRequest.getUsername());
        if (user != null) {
            ConfirmationToken confirmationToken = new ConfirmationToken(user);
            confirmationToken = confirmationTokenService.save(confirmationToken);
            Email email = new Email(user.getUsername(), "Complete Password Reset!", "To complete the password reset process, please click here: "
                    + "http://localhost:3000/change-password/" + confirmationToken.getConfirmationToken());
            emailSenderService.sendEmail(email);
            return ResponseEntity.ok(new ApiResponse(true, "Request to reset password received. Check your inbox for the reset link."));
        } else {
            return ResponseEntity.ok(new EmailNotFoundException("This email does not exist!", "e-mail"));
        }
    }

    @PostMapping(value = "/confirm-reset")
    public ResponseEntity<?> validateResetToken(@RequestParam("token") String confirmationToken) {
        ConfirmationToken token = confirmationTokenService.findByConfirmationToken(confirmationToken);
        if (token != null && token.getValidity()) {
            User user = userService.findUserByUsername(token.getUser().getUsername());
            user.setEnabled(true);
            userService.save(user);
            return ResponseEntity.ok(new ApiResponse(true, "Successful"));
        } else {
            return ResponseEntity.ok(new EmailNotFoundException("The link is invalid or broken!", "e-mail"));
        }
    }
}