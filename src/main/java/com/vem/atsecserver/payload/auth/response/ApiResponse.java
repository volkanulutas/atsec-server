package com.vem.atsecserver.payload.auth.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author volkanulutas
 * @since 12.12.2020
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ApiResponse {
    private Boolean success;
    private String message;
}
