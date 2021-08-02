package com.vem.atsecserver.converter;

import com.vem.atsecserver.entity.rawproduct.EnumRawProductStatus;
import com.vem.atsecserver.entity.rawproduct.Location;
import com.vem.atsecserver.entity.rawproduct.RawProduct;
import com.vem.atsecserver.payload.rawproduct.RawProductRequest;
import com.vem.atsecserver.service.sales.CustomerService;
import com.vem.atsecserver.service.rawproduct.DonorService;
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

    @Autowired
    private TissueConverter tissueConverter;

    @Autowired
    private DonorConverter donorConverter;

    @Autowired
    private LocationConverter locationConverter;

    @Autowired
    private DonorInstituteConverter donorInstituteConverter;

    public RawProduct toEntity(RawProductRequest request) {
        if (request == null) {
            LOGGER.error("Error is occurred while converting raw product.");
            return null;
        }
        RawProduct entity = new RawProduct();
        entity.setId(request.getId());
        entity.setArrivalDate(request.getArrivalDate());
        entity.setIssueTissueDate(request.getIssueTissueDate());
        if (request.getTissueType() != null) {
            entity.setTissueType(tissueConverter.toEntity(request.getTissueType()));
        }
        if (request.getDonor() != null) {
            entity.setDonor(donorConverter.toEntity(request.getDonor()));
        }
        if (request.getDonorInstitute() != null) {
            entity.setDonorInstitute(donorInstituteConverter.toEntity(request.getDonorInstitute()));
        }
        if (request.getLocation() != null) {
            entity.setLocation(locationConverter.toEntity(request.getLocation()));
        }
        if (request.getDefinition() != null) {
            entity.setDefinition(request.getDefinition());
        }
        if (request.getInformation() != null) {
            entity.setInformation(request.getInformation());
        }
        if (request.getStatusName() != null) {
            entity.setStatus(EnumRawProductStatus.findByName(request.getStatusName()));
        }
        if (request.getDeleted() != null) {
            entity.setDeleted(request.getDeleted());
        }
        return entity;
    }

    public RawProductRequest toRequest(RawProduct entity) {
        if (entity == null) {
            LOGGER.error("Error is occurred while converting product.");
            return null;
        }
        RawProductRequest request = new RawProductRequest();
        request.setId(entity.getId());
        request.setIssueTissueDate(entity.getIssueTissueDate());
        request.setArrivalDate(entity.getArrivalDate());
        request.setLocation(locationConverter.toRequest(entity.getLocation()));
        if (entity.getStatus() != null) {
            request.setStatusName(entity.getStatus().getName());
        }
        if (entity.getDonor() != null) {
            request.setDonor(donorConverter.toRequest(entity.getDonor()));
        }
        if (entity.getDonorInstitute() != null) {
            request.setDonorInstitute(donorInstituteConverter.toRequest(entity.getDonorInstitute()));
        }
        if (entity.getTissueType() != null) {
            request.setTissueType(tissueConverter.toRequest(entity.getTissueType()));
        }
        request.setDefinition(entity.getDefinition());
        request.setDeleted(request.getDeleted());
        return request;
    }
}