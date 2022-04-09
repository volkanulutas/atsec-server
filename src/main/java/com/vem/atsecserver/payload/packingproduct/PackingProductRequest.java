package com.vem.atsecserver.payload.packingproduct;

import com.vem.atsecserver.entity.packingproduct.EnumPackingProductSize;
import com.vem.atsecserver.entity.rawproduct.Donor;
import com.vem.atsecserver.payload.rawproduct.DonorRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PackingProductRequest {

    private Long id;

    private String size;

    private String packingProductCode;

    private String lot;

    private String date;

    private DonorRequest donor;

    private int partitionId;

    private boolean deleted;
}
