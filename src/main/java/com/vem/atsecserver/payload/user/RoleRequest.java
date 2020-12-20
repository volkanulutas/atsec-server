package com.vem.atsecserver.payload.user;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Collection;

public class RoleRequest {

    private Long id;

    private String name;

    private String definition;

    @JsonIgnore
    private Collection<UserRequest> users;

    private Collection<PermissionRequest> permissions;

    private Boolean isDeleted;

    public RoleRequest() {
        // Default constructor.
    }

    public RoleRequest(String name, String definition) {
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

    public Collection<UserRequest> getUsers() {
        return users;
    }

    public void setUsers(Collection<UserRequest> users) {
        this.users = users;
    }

    public Collection<PermissionRequest> getPermissions() {
        return permissions;
    }

    public void setPermissions(Collection<PermissionRequest> permissions) {
        this.permissions = permissions;
    }

    public Boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}
