package com.vem.atsecserver.service;

import com.vem.atsecserver.data.mail.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author volkanulutas
 * @since 25.12.2020
 */
@Service("emailSenderService")
public class EmailSenderService {

    @Value("spring.mail.from.email")
    private String from;

    private JavaMailSender javaMailSender;

    @Autowired
    public EmailSenderService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Async
    public void sendEmail(Email email) {
        email.setFrom(this.from);
        javaMailSender.send(email.getMail());
    }
}
