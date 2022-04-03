package com.vem.atsecserver.payload.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;

/**
 * @author volkanulutas
 * @since 12.12.2020
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PermissionRequest {
    @NotNull
    private Long id;

    @NotBlank
    @Size(min = 3)
    private String name;

    private String menu;

    @JsonIgnore
    private Collection<RoleRequest> roles;

    private String definition;

    private boolean isDeleted;
}
