package com.vem.atsecserver.converter;

import com.vem.atsecserver.entity.rawproduct.EnumRawProductStatus;
import com.vem.atsecserver.entity.rawproduct.RawProduct;
import com.vem.atsecserver.payload.rawproduct.RawProductRequest;
import com.vem.atsecserver.service.rawproduct.DonorService;
import com.vem.atsecserver.service.sales.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;

/**
 * @author volkanulutas
 * @since 25.12.2020
 */
@Slf4j
@Component
public class RawProductConverter {
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

    public RawProduct toEntity(RawProductRequest request) throws ParseException {
        if (request == null) {
            log.error("Error is occurred while converting raw product.");
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
        if (request.getLocation() != null) {
            entity.setLocation(locationConverter.toEntity(request.getLocation()));
        }
        if (request.getDoctorName() != null) {
            entity.setDoctorName(request.getDoctorName());
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
        if (request.getSignerInfo() != null) {
            entity.setSignerInfo(request.getSignerInfo());
        }
        entity.setDeleted(request.isDeleted());
        entity.setDataLogger(request.isDataLogger());
        entity.setTissueCarryCase(request.isTissueCarryCase());
        entity.setSterialBag(request.isSterialBag());
        entity.setTemperature(request.getTemperature());
        return entity;
    }

    public RawProductRequest toRequest(RawProduct entity) throws ParseException {
        if (entity == null) {
            log.error("Error is occurred while converting product.");
            return null;
        }
        RawProductRequest request = new RawProductRequest();
        request.setId(entity.getId());
        request.setIssueTissueDate(entity.getIssueTissueDate());
        request.setArrivalDate(entity.getArrivalDate());
        request.setLocation(locationConverter.toRequest(entity.getLocation()));
        if (entity.getDoctorName() != null) {
            request.setDoctorName(entity.getDoctorName());
        }
        if (entity.getStatus() != null) {
            request.setStatusName(entity.getStatus().getName());
        }
        if (entity.getDonor() != null) {
            request.setDonor(donorConverter.toRequest(entity.getDonor()));
        }
        if (entity.getTissueType() != null) {
            request.setTissueType(tissueConverter.toRequest(entity.getTissueType()));
        }
        if (entity.getSignerInfo() != null) {
            request.setSignerInfo(entity.getSignerInfo());
        }
        if (entity.getTissueCarryCase() != null) {
            request.setTissueCarryCase(entity.getTissueCarryCase());
        }
        if (entity.getSterialBag() != null) {
            request.setSterialBag(entity.getSterialBag());
        }
        if (entity.getDataLogger() != null) {
            request.setDataLogger(entity.getDataLogger());
        }
        if (entity.getTemperature() != -1) {
            request.setTemperature(entity.getTemperature());
        }

        request.setDefinition(entity.getDefinition());
        request.setDeleted(request.isDeleted());
        return request;
    }
}