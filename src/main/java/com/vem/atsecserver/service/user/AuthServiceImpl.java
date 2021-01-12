package com.vem.atsecserver.service.user;

import com.vem.atsecserver.data.mail.Email;
import com.vem.atsecserver.entity.auth.ConfirmationToken;
import com.vem.atsecserver.entity.user.User;
import com.vem.atsecserver.service.auth.ConfirmationTokenService;
import com.vem.atsecserver.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author volkanulutas
 * @since 01.01.2021
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private ConfirmationTokenService confirmationTokenService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private EmailSenderService emailSenderService;

    @Override
    public Boolean changeUserPassword(String token, String password) {
        ConfirmationToken confirmationToken = confirmationTokenService.findByConfirmationToken(token);
        if (confirmationToken != null) {
            User user = confirmationToken.getUser();
            user.setPassword(password);
            user = userService.save(user);
            confirmationToken.setValidity(false);
            sendInformationMailToAdminRole(confirmationToken);
            confirmationTokenService.save(confirmationToken);
            return true;
        }
        return false;
    }

    private void sendInformationMailToAdminRole(ConfirmationToken confirmationToken) {
        List<User> adminRoleUsers = roleService.getAdminRoleUsers();
        for (User user : adminRoleUsers) {
            Email email = new Email(user.getUsername(), "Kullanıcı Şifre Değiştirme Bildirimi", "Aşağıdaki kullanıcı şifresini değiştirmiştir: \n"
                    + confirmationToken.getUser());
            emailSenderService.sendEmail(email);
        }
    }
}
