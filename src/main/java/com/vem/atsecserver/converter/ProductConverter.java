package com.vem.atsecserver.converter;

import com.vem.atsecserver.entity.packingproduct.EnumPackingProductSize;
import com.vem.atsecserver.entity.product.*;
import com.vem.atsecserver.entity.report.product.ProductFile;
import com.vem.atsecserver.entity.sales.Customer;
import com.vem.atsecserver.payload.product.PreProcessingTypeRequest;
import com.vem.atsecserver.payload.product.ProductFileRequest;
import com.vem.atsecserver.payload.product.ProductRequest;
import com.vem.atsecserver.payload.product.ProductStatusDateRequest;
import com.vem.atsecserver.service.rawproduct.DonorService;
import com.vem.atsecserver.service.sales.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author volkanulutas
 * @since 01.01.2021
 */
@Slf4j
@Component
public class ProductConverter {
    @Autowired
    private DonorService donorService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private DonorConverter donorConverter;

    @Autowired
    private CustomerConverter customerConverter;

    @Autowired
    private ProductFileConverter productFileConverter;

    @Autowired
    private ProductStatusDateConverter productStatusDateConverter;

    @Autowired
    private LocationConverter locationConverter;

    @Autowired
    private PreProcessingTypeConverter preProcessingTypeConverter;

    public Product toEntity(ProductRequest request) {
        if (request == null) {
            log.error("Error is occurred while converting product.");
            return null;
        }
        try {
            Product product = new Product();
            if (request.getId() != null) {
                product.setId(request.getId());
            }
            List<EnumProductFormType> productFormTypes = new ArrayList<>();
            if (request.getProductFormType() != null) {
                for (String val : request.getProductFormType()) {
                    EnumProductFormType byName = EnumProductFormType.findByName(val);
                    productFormTypes.add(byName);
                }
            }
            product.setProductFormType(productFormTypes);
            List<EnumProductGranulationType> granulationTypes = new ArrayList<>();
            if (request.getGranulationType() != null) {
                for (String val : request.getGranulationType()) {
                    EnumProductGranulationType byName = EnumProductGranulationType.findByName(val);
                    granulationTypes.add(byName);
                }
            }
            product.setGranulationType(granulationTypes);
            product.setType(EnumProductType.findByName(request.getType()));
            product.setStatus(EnumProductStatus.findByName(request.getStatus()));
            List<PreProcessingTypeRequest> preProcessingTypes = request.getPreProcessingTypes();
            if (preProcessingTypes != null) {
                for (PreProcessingTypeRequest preProcessingType : preProcessingTypes) {
                    product.addPreProcessingType(preProcessingTypeConverter.toEntity(preProcessingType));
                }
            }
            if(request.getProductStatusDateRequests() != null){
                for (ProductStatusDateRequest data : request.getProductStatusDateRequests()) {
                    product.addProductStatusDates(productStatusDateConverter.toEntity(data));
                }
            }
            product.setSecCode(request.getSecCode());
            product.setInformation(request.getInformation());
            product.setDefinition(request.getDefinition());
            try {
                Customer customerById = customerService.findCustomerById(request.getCustomer().getId());
                if (customerById != null) {
                    product.setCustomer(customerById);
                }
            } catch (Exception ex) {
                log.error("Product conversion error of Customer Id set.");
            }
            product.setDeleted(request.isDeleted());
            List<ProductFile> files = new ArrayList<>();
            for (ProductFileRequest fileRequest : request.getFiles()) {
                files.add(productFileConverter.toEntity(fileRequest));
            }
            product.setFiles(files);
            product.setLocation(locationConverter.toEntity(request.getLocation()));
            return product;
        } catch (Exception ex) {
            System.err.println("Hata cevirme");
        }
        return null;
    }

    public ProductRequest toRequest(Product entity) throws ParseException {
        if (entity == null) {
            log.error("Error is occurred while converting product.");
            return null;
        }
        ProductRequest request = new ProductRequest();
        request.setId(entity.getId());

        List<String> productFormTypes = new ArrayList<>();
        for (EnumProductFormType val : entity.getProductFormType()) {
            productFormTypes.add(val.getName());
        }
        List<String> granulationType = new ArrayList<>();
        for (EnumProductGranulationType val : entity.getGranulationType()) {
            granulationType.add(val.getName());
        }

        request.setProductFormType(productFormTypes);
        request.setGranulationType(granulationType);
        if (entity.getType() != null) {
            request.setType(entity.getType().getName());
        }
        if (entity.getStatus() != null) {
            request.setStatus(entity.getStatus().getName());
        }
        if (entity.getCustomer() != null) {
            request.setCustomer(customerConverter.toRequest(entity.getCustomer()));
        }
        if (entity.getDonor() != null) {
            request.setDonor(donorConverter.toRequest(entity.getDonor()));
        }
        request.setSecCode(entity.getSecCode());


        List<PreProcessingType> preProcessingTypes = entity.getPreProcessingTypes();
        List<PreProcessingTypeRequest> targetPreProcessingType = new ArrayList<>();
        if (preProcessingTypes != null) {
            for (PreProcessingType pre : preProcessingTypes) {
                if(pre.getPreProcessingType() != null) {
                    targetPreProcessingType.add(preProcessingTypeConverter.toRequest(pre));
                }
            }
        }
        request.setPreProcessingTypes(targetPreProcessingType);
        request.setDefinition(entity.getDefinition());
        request.setInformation(entity.getInformation());
        request.setDeleted(entity.isDeleted());

        List<ProductFileRequest> fileRequestList = new ArrayList<>();
        for (ProductFile file : entity.getFiles()) {
            fileRequestList.add(productFileConverter.toRequest(file));
        }
        request.setFiles(fileRequestList);

        List<ProductStatusDateRequest> productStatusDatesRequest = new ArrayList<>();
        for (ProductStatusDate data : entity.getProductStatusDates()) {
            productStatusDatesRequest.add(productStatusDateConverter.toRequest(data));
        }
        request.setLocation(locationConverter.toRequest(entity.getLocation()));
        request.setProductStatusDateRequests(productStatusDatesRequest);

        return request;
    }
}