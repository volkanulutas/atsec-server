package com.vem.atsecserver.payload.product;

import com.vem.atsecserver.entity.product.EnumProductStatus;
import com.vem.atsecserver.entity.product.PreProcessingType;
import com.vem.atsecserver.payload.rawproduct.DonorRequest;
import com.vem.atsecserver.payload.rawproduct.LocationRequest;
import com.vem.atsecserver.payload.sales.CustomerRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author volkanulutas
 * @since 12.12.2020
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class ProductRequest {
    @NotNull
    private Long id;

    private DonorRequest donor;

    private CustomerRequest customer;

    private String status;

   private  List<ProductStatusDateRequest> productStatusDateRequests;

    private String secCode;

    private String type;

    private List<PreProcessingTypeRequest> preProcessingTypes;

    private String definition;

    private String information;

    private boolean deleted;

    private List<ProductFileRequest> files;

    private List<String> productFormType;

    private List<String> granulationType;

    private LocationRequest location;

    // TODO: expiry date
}