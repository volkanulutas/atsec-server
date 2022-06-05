package com.vem.atsecserver.payload.rawproduct;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author volkanulutas
 * @since 12.12.2020
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class RawProductRequest implements Serializable {
    @NotNull
    private Long id;

    private DonorRequest donor;

    private long issueTissueDate; // Doku çıkarım tarihi

    private TissueTypeRequest tissueType;

    private LocationRequest location;

    private String doctorName;

    private long arrivalDate; // Merkeze geliş tarihi

    private String statusName;

    private String definition;

    private String information; // NOTE: recall prosedüründe kullanılabilir.

    private String signerInfo;

    private boolean deleted;

    private boolean tissueCarryCase;

    private boolean sterialBag;

    private boolean dataLogger;

    private int temperature;
}