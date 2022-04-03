package com.vem.atsecserver.payload.auth.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author volkanulutas
 * @since 26.12.2020
 */
@NoArgsConstructor
@Data
public class ChangePasswordRequest implements Serializable {

    @NotBlank
    private String token;

    @NotBlank
    private String password;
}
