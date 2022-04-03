package com.vem.atsecserver.payload.auth.response;

import com.vem.atsecserver.payload.user.PermissionRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author volkanulutas
 * @since 12.12.2020
 */
@NoArgsConstructor
@Data
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
}
