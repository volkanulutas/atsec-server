package com.vem.atsecserver.payload.rawproduct;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vem.atsecserver.entity.rawproduct.RawProduct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author volkanulutas
 * @since 12.12.2020
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DonorRequest {
    @NotNull
    private Long id;

    @NotNull
    private String code;

    private String citizenshipNumber;

    private DonorInstituteRequest donorInstitute;

    private String name;

    private String surname;

    private String telephone;

    private String address;

    private String addressDistrict;

    private String addressCity;

    private String tissueNumber;

    private String tissueType;

    private String bloodType;

    private String birthdate;

    private String sex;

    @JsonIgnore
    private List<RawProduct> products;

    private byte[] bloodTestPdfFile;

    private Boolean deleted;
}