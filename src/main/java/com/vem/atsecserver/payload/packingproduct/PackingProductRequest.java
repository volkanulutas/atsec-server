package com.vem.atsecserver.payload.packingproduct;

import com.vem.atsecserver.entity.packingproduct.EnumPackingProductSize;
import com.vem.atsecserver.entity.rawproduct.Donor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PackingProductRequest {

    private Long id;

    private EnumPackingProductSize size; // TODO: Daha sonra sil productStatusDates de var bu bilgi

    private String lot;

    private long gamaDate;

    private long packingProductCode;

    private Donor donor;

    private int partitionId;

    private boolean deleted;
}
