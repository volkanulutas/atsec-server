package com.vem.atsecserver.payload.product;

import com.vem.atsecserver.entity.report.product.EnumProductFileDBType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductFileRequest {
    private Long id;

    private String name;

    private EnumProductFileDBType fileDBType;

    private String type;

    private byte[] data;
}
