package com.vem.atsecserver.converter;

import com.vem.atsecserver.entity.Donor;
import com.vem.atsecserver.payload.rawproduct.DonorRequest;
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
        entity.setCode(request.getCode());
        entity.setCitizenshipNumber(request.getCitizenshipNumber());
        entity.setDeleted(request.getDeleted());
        entity.setBloodTestPdfFile(request.getBloodTestPdfFile());
        if (request.getId() != null) {
            entity.setId(request.getId());
        }
        if (request.getProducts() != null) {
            entity.setRawProducts(request.getProducts());
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
        request.setCitizenshipNumber(request.getCitizenshipNumber());
        request.setCode(entity.getCode());
        request.setDeleted(entity.isDeleted());
        request.setBloodTestPdfFile(entity.getBloodTestPdfFile());
        request.setId(entity.getId());
        if (entity.getRawProducts() != null) {
            request.setProducts(entity.getRawProducts());
        }
        return request;
    }
}
