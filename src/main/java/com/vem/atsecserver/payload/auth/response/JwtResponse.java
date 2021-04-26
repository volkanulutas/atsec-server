package com.vem.atsecserver.payload.auth.response;

import com.vem.atsecserver.entity.user.Permission;
import com.vem.atsecserver.payload.user.PermissionRequest;

import java.util.List;

/**
 * @author volkanulutas
 * @since 12.12.2020
 */
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private List<PermissionRequest> permissions;

    public JwtResponse(String accessToken, Long id, String username, List<PermissionRequest> permissions) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.permissions = permissions;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<PermissionRequest> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<PermissionRequest> permissions) {
        this.permissions = permissions;
    }
}
