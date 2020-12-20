package com.vem.atsecserver.payload.auth.request;

import javax.validation.constraints.NotBlank;

/**
 * @author volkanulutas
 * @since 12.12.2020
 */
public class LoginRequest {
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
