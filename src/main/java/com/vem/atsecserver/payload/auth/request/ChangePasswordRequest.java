package com.vem.atsecserver.payload.auth.request;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author volkanulutas
 * @since 26.12.2020
 */
public class ChangePasswordRequest implements Serializable {

    @NotBlank
    private String token;

    @NotBlank
    private String password;

    public ChangePasswordRequest() {
        // Default constructor.
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
