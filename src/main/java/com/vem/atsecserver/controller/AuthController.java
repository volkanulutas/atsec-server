package com.vem.atsecserver.controller;

import com.vem.atsecserver.auth.security.JwtTokenProvider;
import com.vem.atsecserver.auth.security.UserPrincipal;
import com.vem.atsecserver.converter.UserConverter;
import com.vem.atsecserver.entity.auth.ConfirmationToken;
import com.vem.atsecserver.entity.user.User;
import com.vem.atsecserver.payload.auth.request.ChangePasswordRequest;
import com.vem.atsecserver.payload.auth.request.LoginRequest;
import com.vem.atsecserver.payload.auth.response.ApiResponse;
import com.vem.atsecserver.payload.auth.response.JwtResponse;
import com.vem.atsecserver.payload.exception.EmailNotFoundException;
import com.vem.atsecserver.payload.user.UserRequest;
import com.vem.atsecserver.service.ConfirmationTokenService;
import com.vem.atsecserver.service.EmailSenderService;
import com.vem.atsecserver.service.user.AuthService;
import com.vem.atsecserver.service.user.RoleService;
import com.vem.atsecserver.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author volkanulutas
 * @since 12.12.2020
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
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
    private JwtTokenProvider jwtUtils;

    @Autowired
    private EmailSenderService emailSenderService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        System.out.println("signin ********");
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserPrincipal userDetails = (UserPrincipal) authentication.getPrincipal();
        List<String> roles = null;
        if (userDetails.getAuthorities() != null) {
            roles = userDetails.getAuthorities().stream()
                    .map(item -> item.getAuthority())
                    .collect(Collectors.toList());
        }
        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                roles));
    }

    @RequestMapping(value = "/change-password", method = RequestMethod.POST)
    public ResponseEntity<?> changeUserPassword(@RequestBody ChangePasswordRequest changePasswordRequest) {
        return ResponseEntity.ok(authService.changeUserPassword(changePasswordRequest.getToken(), changePasswordRequest.getPassword()));
    }

    /**
     * Receive email of the user, create token and send it via email to the user
     */
    @RequestMapping(value = "/forget-password", method = RequestMethod.POST)
    public ResponseEntity<?> forgotUserPassword(@RequestBody UserRequest userRequest) {
        System.out.println("*********s-");
        User user = userService.findUserByUsername(userRequest.getUsername());
        if (user != null) {
            // create token
            ConfirmationToken confirmationToken = new ConfirmationToken(user);

            // save it
            confirmationToken = confirmationTokenService.save(confirmationToken);

            // create the email
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(user.getUsername());
            mailMessage.setSubject("Complete Password Reset!");
            mailMessage.setFrom("volkanulutas@gmail.com");
            mailMessage.setText("To complete the password reset process, please click here: "
                    + "http://localhost:3000/change-password/" + confirmationToken.getConfirmationToken());
            emailSenderService.sendEmail(mailMessage);
            return ResponseEntity.ok(new ApiResponse(true, "Request to reset password received. Check your inbox for the reset link."));
        } else {
            return ResponseEntity.ok(new EmailNotFoundException("This email does not exist!", "e-mail"));
        }
    }

    @RequestMapping(value = "/confirm-reset", method = RequestMethod.POST)
    public ResponseEntity<?> validateResetToken(@RequestParam("token") String confirmationToken) {
        ConfirmationToken token = confirmationTokenService.findByConfirmationToken(confirmationToken);
        if (token != null && token.getValidity()) {
            User user = userService.findUserByUsername(token.getUser().getUsername());
            user.setEnabled(true);
            user = userService.save(user);
            return ResponseEntity.ok(new ApiResponse(true, "Successful"));
        } else {
            System.out.println("**** -> broken");
            return ResponseEntity.ok(new EmailNotFoundException("The link is invalid or broken!", "e-mail"));
        }
    }
}