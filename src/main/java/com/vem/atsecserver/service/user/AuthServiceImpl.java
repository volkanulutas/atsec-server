package com.vem.atsecserver.service.user;

import com.vem.atsecserver.entity.auth.ConfirmationToken;
import com.vem.atsecserver.entity.user.User;
import com.vem.atsecserver.service.ConfirmationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private ConfirmationTokenService confirmationTokenService;

    @Override
    public Boolean changeUserPassword(String token, String password) {
        ConfirmationToken confirmationToken = confirmationTokenService.findByConfirmationToken(token);
        if (confirmationToken != null) {
            User user = confirmationToken.getUser();
            user.setPassword(password);
            user = userService.save(user);
            confirmationToken.setValidity(false);
            confirmationTokenService.save(confirmationToken);
            System.out.println("***** --> true");
            return true;
        }
        System.out.println("***** --> false");
        return false;
    }
}
