package com.vem.atsecserver.converter;

import com.vem.atsecserver.entity.product.EnumProductPreProcessingType;
import com.vem.atsecserver.entity.product.EnumProductStatus;
import com.vem.atsecserver.entity.product.EnumProductType;
import com.vem.atsecserver.entity.product.Product;
import com.vem.atsecserver.entity.rawproduct.Donor;
import com.vem.atsecserver.entity.sales.Customer;
import com.vem.atsecserver.payload.product.ProductRequest;
import com.vem.atsecserver.service.rawproduct.DonorService;
import com.vem.atsecserver.service.sales.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author volkanulutas
 * @since 01.01.2021
 */
@Component
public class ProductConverter {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductConverter.class);

    @Autowired
    private DonorService donorService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private DonorConverter donorConverter;

    @Autowired
    private CustomerConverter customerConverter;

    public Product toEntity(ProductRequest request) {
        if (request == null) {
            LOGGER.error("Error is occurred while converting product.");
            return null;
        }
        Product product = new Product();
        if (request.getId() != null) {
            product.setId(request.getId());
        }
        product.setType(EnumProductType.findByName(request.getType()));
        product.setStatus(EnumProductStatus.findByName(request.getStatus()));
        List<EnumProductPreProcessingType> preProcessingTypeList = product.getPreProcessingType();
        if (preProcessingTypeList == null) {
            preProcessingTypeList = new ArrayList<>();
        }
        if(request.getPreProcessingType() !=null)
        {
            for (String preProcessingStr : request.getPreProcessingType()) {
                preProcessingTypeList.add(EnumProductPreProcessingType.findByName(preProcessingStr));
            }
        }
        product.setSecCode(request.getSecCode());
        product.setInformation(request.getInformation());
        product.setDefinition(request.getDefinition());
        try {
            Donor donorById = donorService.findDonorById(request.getDonor().getId());
            if (donorById != null) {
                product.setDonor(donorById);
            }
        } catch (Exception ex) {
            LOGGER.error("Product conversion error of Donor Id set.");
        }
        try {
            Customer customerById = customerService.findCustomerById(request.getCustomer().getId());
            if (customerById != null) {
                product.setCustomer(customerById);
            }
        } catch (Exception ex) {
            LOGGER.error("Product conversion error of Customer Id set.");
        }
        product.setDeleted(request.isDeleted());
        return product;
    }

    public ProductRequest toRequest(Product entity) {
        if (entity == null) {
            LOGGER.error("Error is occurred while converting product.");
            return null;
        }
        ProductRequest request = new ProductRequest();
        request.setId(entity.getId());
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

        List<String> preProcessingList = new ArrayList<>();
        for (EnumProductPreProcessingType preProcessingType : entity.getPreProcessingType()) {
            preProcessingList.add(preProcessingType.getName());
        }
        request.setPreProcessingType(preProcessingList);
        request.setDefinition(entity.getDefinition());
        request.setInformation(entity.getInformation());
        request.setDeleted(entity.isDeleted());
        return request;
    }
}