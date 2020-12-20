package com.vem.atsecserver.payload.user;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;

/**
 * @author volkanulutas
 * @since 12.12.2020
 */
public class PermissionRequest {
    @NotNull
    private Long id;

    @NotBlank
    @Size(min = 3)
    private String name;

    @JsonIgnore
    private Collection<RoleRequest> roles;

    private String definition;

    private Boolean isDeleted;

    public PermissionRequest() {
        // Default constructor.
    }

    public PermissionRequest(String name, String definition) {
        this.name = name;
        this.definition = definition;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public Boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}
