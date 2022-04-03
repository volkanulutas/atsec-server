package com.vem.atsecserver.payload.rawproduct;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author volkanulutas
 * @since 02.01.2021
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TissueTypeRequest {
    @NotNull
    private Long id;

    @NotBlank
    @Size(min = 3)
    private String name;

    private String definition;

    private Boolean deleted;
}
