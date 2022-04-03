package com.vem.atsecserver.payload.rawproduct;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author volkanulutas
 * @since 25.12.2020
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DonorInstituteRequest implements Serializable {
    private Long id;

    private String code;

    private String name;

    private List<RawProductRequest> rawProducts;

    private Boolean deleted;
}