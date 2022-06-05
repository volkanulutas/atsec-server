package com.vem.atsecserver.payload.product;

import com.vem.atsecserver.entity.product.EnumProductPreProcessingType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PreProcessingTypeRequest {

    private String preProcessingType; // EnumProductPreProcessingType

    private long processingDate;
}
