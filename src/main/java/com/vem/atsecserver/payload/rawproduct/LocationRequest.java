package com.vem.atsecserver.payload.rawproduct;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author volkanulutas
 * @since 02.01.2021
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LocationRequest {

    private Long id;

    private String name;

    private String definition;

    private Boolean deleted;
}