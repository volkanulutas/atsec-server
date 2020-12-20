package com.vem.atsecserver.converter;

import com.vem.atsecserver.entity.product.EnumProductStatus;
import com.vem.atsecserver.entity.product.EnumProductType;
import com.vem.atsecserver.entity.product.Product;
import com.vem.atsecserver.payload.product.ProductRequest;
import com.vem.atsecserver.service.CustomerService;
import com.vem.atsecserver.service.DonorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductConverter.class);

    @Autowired
    private DonorService donorService;

    @Autowired
    private CustomerService customerService;

    public Product toEntity(ProductRequest request) {
        if (request == null) {
            LOGGER.error("Error is occurred while converting product.");
            return null;
        }
        Product product = new Product();
        product.setName(request.getName());
        if (request.getId() != null) {
            product.setId(request.getId());
        }
        product.setType(EnumProductType.findByName(request.getType()));
        product.setSplitLength(request.getSplitLength());
        product.setStatus(EnumProductStatus.findByName(request.getStatus()));
        product.setSecCode(request.getSecCode());
        product.setInformation(request.getInformation());
        product.setExpirationDate(request.getExpirationDate());
        if (request.getDonorId() != null) {
            product.setDonor(donorService.findDonorById(Long.parseLong(request.getDonorId())));
        }
        if (request.getCustomerId() != null) {
            product.setCustomer(customerService.findCustomerById(Long.parseLong(request.getCustomerId())));
        }
        product.setDeleted(request.isDeleted());
        product.setDefinition(request.getDefinition());
        return product;
    }

    public ProductRequest toRequest(Product entity) {
        if (entity == null) {
            LOGGER.error("Error is occurred while converting product.");
            return null;
        }
        ProductRequest request = new ProductRequest();
        request.setId(entity.getId());
        request.setName(entity.getName());
        if (entity.getType() != null) {
            request.setType(entity.getType().getName());
        }
        if (entity.getStatus() != null) {
            request.setStatus(entity.getStatus().getName());
        }
        request.setSplitLength(entity.getSplitLength());
        request.setSecCode(entity.getSecCode());
        request.setDefinition(entity.getDefinition());
        request.setInformation(entity.getInformation());
        request.setExpirationDate(entity.getExpirationDate());
        if (entity.getDonor() != null) {
            request.setDonorId(entity.getDonor().getId() + "");
        }
        if (entity.getCustomer() != null) {
            request.setCustomerId(entity.getCustomer().getId() + "");
        }
        request.setDeleted(entity.isDeleted());
        return request;
    }
}