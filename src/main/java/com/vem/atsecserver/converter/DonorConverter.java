package com.vem.atsecserver.converter;

import com.vem.atsecserver.entity.rawproduct.Donor;
import com.vem.atsecserver.entity.rawproduct.EnumBloodType;
import com.vem.atsecserver.entity.rawproduct.EnumSex;
import com.vem.atsecserver.payload.rawproduct.DonorRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author volkanulutas
 * @since 01.01.2021
 */
@Slf4j
@Component
public class DonorConverter {

    @Autowired
    private DonorInstituteConverter donorInstituteConverter;

    public Donor toEntity(DonorRequest request) throws ParseException {
        if (request == null) {
            log.error("Error is occurred while converting donor.");
            return null;
        }
        Donor entity = new Donor();
        entity.setDonorInstitute(donorInstituteConverter.toEntity(request.getDonorInstitute()));
        entity.setTelephone(request.getTelephone());
        entity.setAddress(request.getAddress());
        entity.setAddressCity(request.getAddressCity());
        entity.setAddressDistrict(request.getAddressDistrict());
        entity.setName(request.getName());
        entity.setSurname(request.getSurname());

        entity.setCode(request.getCode());

        entity.setTissueType(request.getTissueType());
        entity.setTissueNumber(Integer.valueOf(request.getTissueNumber()));

        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date parse = simpleDateFormat.parse(request.getBirthdate());
        entity.setBirthdate(parse.getTime());

        entity.setBloodType(EnumBloodType.getBloodTypeByName(request.getBloodType()));
        entity.setSex(EnumSex.getSexByName(request.getSex()));
        entity.setCitizenshipNumber(request.getCitizenshipNumber());
        entity.setDeleted(request.getDeleted());
        entity.setBloodTestPdfFile(request.getBloodTestPdfFile());
        entity.setTissueType(request.getTissueType());
        try {
            entity.setTissueNumber(Integer.parseInt(request.getTissueNumber()));
        } catch (Exception ex) {
            log.error("hata olustu setTissueNumber");
        }
        if (request.getProducts() != null) {
            entity.setRawProducts(request.getProducts());
        }
        return entity;
    }

    public DonorRequest toRequest(Donor entity) throws ParseException {
        if (entity == null) {
            log.error("Error is occurred while converting donor.");
            return null;
        }
        DonorRequest request = new DonorRequest();
        request.setTelephone(entity.getTelephone());
        request.setAddress(entity.getAddress());
        request.setDonorInstitute(donorInstituteConverter.toRequest(entity.getDonorInstitute()));
        request.setAddressDistrict(entity.getAddressDistrict());
        request.setAddressCity(entity.getAddressCity());
        request.setName(entity.getName());
        request.setSurname(entity.getSurname());
        request.setCitizenshipNumber(entity.getCitizenshipNumber());
        request.setCode(entity.getCode());
        request.setDeleted(entity.getDeleted());
        request.setBloodTestPdfFile(entity.getBloodTestPdfFile());
        request.setBloodType(entity.getBloodType() == null ? null : entity.getBloodType().getName());
        request.setTissueType(entity.getTissueType());
        request.setTissueNumber(entity.getTissueNumber() + "");
        request.setSex(entity.getSex() == null ? null : entity.getSex().getName());

        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String format = simpleDateFormat.format(new Date(entity.getBirthdate()));
        request.setBirthdate(format);

        request.setId(entity.getId());
        if (entity.getRawProducts() != null) {
            request.setProducts(entity.getRawProducts());
        }
        return request;
    }
}
