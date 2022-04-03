package com.vem.atsecserver.payload.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author volkanulutas
 * @since 01.01.2021
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRequest {
    // @NotNull
    private Long id;
    @NotBlank
    @Size(max = 50)
    @Email
    private String username;

    @Size(min = 6, max = 40)
    private String password;

    private String name;

    private String surname;
    private String role;
    private boolean enabled;

    private boolean deleted;
}