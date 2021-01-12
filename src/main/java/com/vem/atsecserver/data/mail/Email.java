package com.vem.atsecserver.data.mail;

import org.springframework.mail.SimpleMailMessage;

/**
 * @author volkanulutas
 * @since 01.01.2021
 */
public class Email {

    private String to;

    private String subject;

    private String from;

    private String text;

    public Email(String to, String subject, String text) {
        this.to = to;
        this.subject = subject;
        this.text = text;
    }

    public SimpleMailMessage getMail() {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(this.to);
        mailMessage.setSubject(this.subject);
        mailMessage.setFrom(this.from);
        mailMessage.setText(this.text);
        return mailMessage;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
