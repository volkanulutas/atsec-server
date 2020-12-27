package com.vem.atsecserver.converter;

import com.vem.atsecserver.entity.Donor;
import com.vem.atsecserver.entity.rawproduct.EnumRawProductStatus;
import com.vem.atsecserver.entity.rawproduct.EnumRawProductType;
import com.vem.atsecserver.entity.rawproduct.RawProduct;
import com.vem.atsecserver.payload.rawproduct.RawProductRequest;
import com.vem.atsecserver.service.CustomerService;
import com.vem.atsecserver.service.DonorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author volkanulutas
 * @since 25.12.2020
 */
@Component
public class RawProductConverter {
    private static final Logger LOGGER = LoggerFactory.getLogger(RawProductConverter.class);

    @Autowired
    private DonorService donorService;

    @Autowired
    private CustomerService customerService;

    public RawProduct toEntity(RawProductRequest request) {
        if (request == null) {
            LOGGER.error("Error is occurred while converting raw product.");
            return null;
        }
        RawProduct raw = new RawProduct();
        Donor donorById = donorService.findDonorByCode(request.getDonorCode());
        raw.setDonor(donorById);
        if (request.getId() != null) {
            raw.setId(request.getId());
        }
        raw.setType(EnumRawProductType.findByName(request.getType()));
        raw.setLocation(request.getLocation());
        raw.setStatus(EnumRawProductStatus.findByName(request.getStatus()));
        raw.setInformation(request.getInformation());
        raw.setAcceptanceDate(request.getAcceptanceDate());
        if (request.getCustomerId() != null) {
            raw.setCustomer(customerService.findCustomerById(Long.parseLong(request.getCustomerId())));
        }
        raw.setDeleted(request.isDeleted());
        raw.setDefinition(request.getDefinition());
        return raw;
    }

    public RawProductRequest toRequest(RawProduct entity) {
        if (entity == null) {
            LOGGER.error("Error is occurred while converting product.");
            return null;
        }
        RawProductRequest request = new RawProductRequest();
        request.setId(entity.getId());
        request.setDonorCode(entity.getDonor().getCode());
        if (entity.getType() != null) {
            request.setType(entity.getType().getName());
        }
        if (entity.getStatus() != null) {
            request.setStatus(entity.getStatus().getName());
        }
        request.setDefinition(entity.getDefinition());
        request.setInformation(entity.getInformation());
        request.setLocation(entity.getLocation());
        request.setAcceptanceDate(entity.getAcceptanceDate());
        if (entity.getCustomer() != null) {
            request.setCustomerId(entity.getCustomer().getId() + "");
        }
        request.setDeleted(entity.isDeleted());
        return request;
    }
}