package com.vem.atsecserver.converter;

import com.vem.atsecserver.entity.product.Donor;
import com.vem.atsecserver.payload.product.DonorRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DonorConverter {
    private static final Logger LOGGER = LoggerFactory.getLogger(DonorConverter.class);

    public Donor toEntity(DonorRequest request) {
        if (request == null) {
            LOGGER.error("Error is occurred while converting donor.");
            return null;
        }
        Donor entity = new Donor();
        entity.setTelephone(request.getTelephone());
        entity.setAddress(request.getAddress());
        entity.setName(request.getName());
        entity.setSurname(request.getSurname());
        entity.setIdentityNumber(request.getIdentityNumber());
        entity.setDeleted(request.isDeleted());
        entity.setBloodTestPdfFile(request.getBloodTestPdfFile());
        if (request.getId() != null) {
            entity.setId(request.getId());
        }
        if (request.getProducts() != null) {
            entity.setProducts(request.getProducts());
        }
        return entity;
    }

    public DonorRequest toRequest(Donor entity) {
        if (entity == null) {
            LOGGER.error("Error is occurred while converting donor.");
            return null;
        }
        DonorRequest request = new DonorRequest();
        request.setTelephone(entity.getTelephone());
        request.setAddress(entity.getAddress());
        request.setName(entity.getName());
        request.setSurname(entity.getSurname());
        request.setIdentityNumber(entity.getIdentityNumber());
        request.setDeleted(entity.isDeleted());
        request.setBloodTestPdfFile(entity.getBloodTestPdfFile());
        request.setId(entity.getId());
        if (entity.getProducts() != null) {
            request.setProducts(entity.getProducts());
        }
        return request;
    }
}
