package com.vem.atsecserver.payload.packingproduct;

import com.vem.atsecserver.entity.packingproduct.EnumPackingProductSize;
import com.vem.atsecserver.entity.rawproduct.Donor;
import com.vem.atsecserver.payload.rawproduct.DonorRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PackingProductRequest {

    private Long id;

    private String packingProductItem;

    private String packingProduct;

    private String number;

    private DonorRequest donor;

    private String lot;

    private String date;

    private boolean deleted;
}
