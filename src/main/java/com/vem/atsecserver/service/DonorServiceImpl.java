package com.vem.atsecserver.service;

import com.vem.atsecserver.entity.Donor;
import com.vem.atsecserver.repository.DonorRepository;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DonorServiceImpl implements DonorService {
    @Autowired
    private DonorRepository donorRepository;

    @Override
    public Boolean existsByCode(String code) {
        return donorRepository.findByCode(code) != null;
    }

    @Override
    public Donor create(Donor donorRequest) {
        Donor donor = new Donor();
        donor.setRegisteredDate(System.currentTimeMillis());
        donor.setCode(generateDonorId());
        donor.setAddress(donorRequest.getAddress());
        donor.setBloodTestPdfFile(donorRequest.getBloodTestPdfFile());
        donor.setName(donorRequest.getName());
        donor.setSurname(donorRequest.getSurname());
        if (donorRequest.getRawProducts() != null) {
            donor.setRawProducts(donorRequest.getRawProducts());
        }
        donor.setTelephone(donorRequest.getTelephone());
        donor.setDeleted(false);
        return donorRepository.save(donor);
    }

    @Override
    public Donor update(Donor donorRequest) {
        Optional<Donor> byId = donorRepository.findById(donorRequest.getId());
        if (byId.isPresent()) {
            Donor donor = byId.get();
            donor.setAddress(donorRequest.getAddress());
            donor.setBloodTestPdfFile(donorRequest.getBloodTestPdfFile());
            donor.setDeleted(false);
            donor.setCode(donorRequest.getCode());
            donor.setName(donorRequest.getName());
            donor.setSurname(donorRequest.getSurname());
            if (donor.getRawProducts() != null) {
                donor.setRawProducts(donorRequest.getRawProducts());
            }
            donor.setTelephone(donorRequest.getTelephone());
            return donorRepository.save(donor);
        }
        return null;
    }

    @Override
    public List<Donor> getAllDonors() {
        return donorRepository.findAll().stream().filter(e -> !e.isDeleted()).collect(Collectors.toList());
    }

    @Override
    public Donor delete(Long id) {
        Optional<Donor> byId = donorRepository.findById(id);
        if (byId.isPresent()) {
            byId.get().setDeleted(true);
            donorRepository.save(byId.get());
            return byId.get();
        }
        return null;
    }

    @Override
    public Donor findDonorByCode(String code) {
        return donorRepository.findByCode(code);
    }

    @Override
    public Donor findDonorById(long id) {
        return donorRepository.findById(id).get(); // TODO:
    }

    public String generateDonorId() {
        Calendar calendar = Calendar.getInstance();
        StringBuilder builder = new StringBuilder();
        Date dateNow = new Date(System.currentTimeMillis());
        Donor donorCurrent = donorRepository.findTopByOrderByIdDesc();

        if (donorCurrent != null) {
            String identityNumber = donorCurrent.getCode();
            Date dateCurrent = new Date(donorCurrent.getRegisteredDate());
            if (DateUtils.isSameDay(dateNow, dateCurrent)) {
                char id1 = identityNumber.charAt(4);
                char id2 = identityNumber.charAt(5);
                int number = Integer.parseInt(id1 + id2 + "");
                calendar.setTime(dateCurrent);
                return builder.append(calendar.get(Calendar.YEAR) % 1000).append(calendar.get(Calendar.MONTH) + 1).append(calendar.get(Calendar.DAY_OF_MONTH)).append((number + 1)).toString();
            } else {
                calendar.setTime(dateNow);
                return builder.append(calendar.get(Calendar.YEAR) % 1000).append(calendar.get(Calendar.MONTH) + 1).append(calendar.get(Calendar.DAY_OF_MONTH)).append("01").toString();
            }
        }
        calendar.setTime(dateNow);
        return builder.append(calendar.get(Calendar.YEAR) % 1000).append(calendar.get(Calendar.MONTH) + 1).append(calendar.get(Calendar.DAY_OF_MONTH)).append("01").toString();
    }
}