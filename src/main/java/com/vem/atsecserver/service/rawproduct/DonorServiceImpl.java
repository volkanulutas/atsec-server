package com.vem.atsecserver.service.rawproduct;

import com.vem.atsecserver.converter.DonorConverter;
import com.vem.atsecserver.data.DonorIdUtil;
import com.vem.atsecserver.entity.rawproduct.Donor;
import com.vem.atsecserver.repository.rawproduct.DonorRepository;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author volkanulutas
 * @since 01.01.2021
 */
@Service
public class DonorServiceImpl implements DonorService {
    @Autowired
    private DonorRepository donorRepository;

    @Autowired
    private DonorConverter donorConverter;

    @Override
    public Donor findByCitizenNumber(String citizenshipNumber) {
        return donorRepository.findByCitizenshipNumber(citizenshipNumber);
    }

    @Override
    public Donor create(Donor donorRequest) {
        Donor existence = findByCitizenNumber(donorRequest.getCitizenshipNumber());
        if (existence == null) { // create
            Donor donor = new Donor();
            donor.setRegisteredDate(System.currentTimeMillis());

            donor.setAddress(donorRequest.getAddress());
            donor.setBloodTestPdfFile(donorRequest.getBloodTestPdfFile());
            donor.setName(donorRequest.getName());
            donor.setSurname(donorRequest.getSurname());
            donor.setDonorInstitute(donorRequest.getDonorInstitute());
            donor.setBloodType(donorRequest.getBloodType());
            donor.setSex(donorRequest.getSex());
            donor.setBirthdate(donorRequest.getBirthdate());
            donor.setTissueType(donorRequest.getTissueType());
            donor.setTissueNumber(donorRequest.getTissueNumber());
            donor.setAddressCity(donorRequest.getAddressCity());
            donor.setAddressDistrict(donorRequest.getAddressDistrict());
            donor.setCitizenshipNumber(donorRequest.getCitizenshipNumber());
            donor.setTelephone(donorRequest.getTelephone());

            donor.setCode(generateDonorId(donor.getDonorInstitute().getCode()));
            if (donorRequest.getRawProducts() != null) {
                donor.setRawProducts(donorRequest.getRawProducts());
            }
            donor.setTelephone(donorRequest.getTelephone());
            donor.setDeleted(false);
            return donorRepository.save(donor);
        }
        return update(donorRequest);
    }

    @Override
    public Donor update(Donor donorRequest) {
        Donor donor = findByCitizenNumber(donorRequest.getCitizenshipNumber());
        if (donor != null) {
            donor.setDeleted(false);
            donor.setCode(donorRequest.getCode());
            donor.setDonorInstitute(donor.getDonorInstitute());
            donor.setAddress(donorRequest.getAddress());
            donor.setBloodTestPdfFile(donorRequest.getBloodTestPdfFile());
            donor.setName(donorRequest.getName());
            donor.setSurname(donorRequest.getSurname());
            donor.setBloodType(donorRequest.getBloodType());
            donor.setSex(donorRequest.getSex());
            donor.setBirthdate(donor.getBirthdate());
            donor.setTissueType(donor.getTissueType());
            donor.setTissueNumber(donorRequest.getTissueNumber());
            donor.setAddressCity(donorRequest.getAddressCity());
            donor.setAddressDistrict(donorRequest.getAddressDistrict());
            donor.setCitizenshipNumber(donorRequest.getCitizenshipNumber());
            donor.setTelephone(donorRequest.getTelephone());
            if (donor.getRawProducts() != null) {
                donor.setRawProducts(donorRequest.getRawProducts());
            }
            return donorRepository.save(donor);
        }
        return donor;
    }

    @Override
    public List<Donor> getAllDonors() {
        return donorRepository.findAll().stream().filter(e -> !e.getDeleted()).collect(Collectors.toList());
    }

    @Override
    public boolean delete(Long id) {
        Optional<Donor> byId = donorRepository.findById(id);

        if (byId.isPresent()) {
            System.out.println("silindi");
            byId.get().setDeleted(true);
            donorRepository.save(byId.get());

            return true;
        }
        return false;
    }

    @Override
    public Donor findDonorById(Long id) {
        return donorRepository.findById(id).get();
    }

    @Override
    public Donor findDonorByCode(String code) {
        return donorRepository.findByCode(code);
    }

    public String generateDonorId(String donorInstituteCode) {
        Date dateNow = new Date(System.currentTimeMillis());
        Donor donorCurrent = donorRepository.findTopByOrderByIdDesc();

        int year = Calendar.getInstance().get(Calendar.YEAR) % 100;
        DonorIdUtil donorId = new DonorIdUtil(donorInstituteCode, year + "", 1);

        if (donorCurrent != null && !donorCurrent.getDeleted()) {
            Date dateCurrent = new Date(donorCurrent.getRegisteredDate());
            if (DateUtils.isSameDay(dateNow, dateCurrent)) {
                String donorIdCurrent = donorCurrent.getCode();
                donorId.setDonorId(donorIdCurrent);
                int donorIdGeneratedValue = donorId.getGeneratedValue() + 1;
                donorId.setGeneratedValue(donorIdGeneratedValue);
                return donorId.getDonorId();
            } else {
                return donorId.getDonorId();
            }
        }
        return donorId.getDonorId();
    }
}