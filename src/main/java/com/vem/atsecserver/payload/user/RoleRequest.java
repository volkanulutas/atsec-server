package com.vem.atsecserver.payload.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

/**
 * @author volkanulutas
 * @since 01.01.2021
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoleRequest {

    private Long id;

    private String name;

    private String definition;

    @JsonIgnore
    private Collection<UserRequest> users;

    private Collection<PermissionRequest> permissions;

    private boolean isDeleted;
}
