package com.vem.atsecserver.payload.sales;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author volkanulutas
 * @since 12.12.2020
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerRequest {
    @NotNull
    private Long id;

    @NotBlank
    private String identityNumber;

    private String customerType; // Bireysel, Firma etc.

    private String definition;

    private String name;

    private String surname;

    private String address;

    private String telephone;

    private Boolean deleted;
}
