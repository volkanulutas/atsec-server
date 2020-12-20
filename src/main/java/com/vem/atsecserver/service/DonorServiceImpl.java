package com.vem.atsecserver.service;

import com.vem.atsecserver.entity.product.Donor;
import com.vem.atsecserver.repository.DonorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DonorServiceImpl implements DonorService {
    @Autowired
    private DonorRepository donorRepository;

    @Override
    public boolean existsByIdentityNumber(String identityNumber) {
        return donorRepository.findByIdentityNumber(identityNumber) != null;
    }

    @Override
    public Donor create(Donor donorRequest) {
        Donor donor = new Donor();
        donor.setAddress(donorRequest.getAddress());
        donor.setBloodTestPdfFile(donorRequest.getBloodTestPdfFile());
        donor.setDeleted(false);
        donor.setIdentityNumber(donorRequest.getIdentityNumber());
        donor.setName(donorRequest.getName());
        donor.setSurname(donorRequest.getSurname());
        if (donorRequest.getProducts() != null) {
            donor.setProducts(donorRequest.getProducts());
        }
        donor.setTelephone(donorRequest.getTelephone());
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
            donor.setIdentityNumber(donorRequest.getIdentityNumber());
            donor.setName(donorRequest.getName());
            donor.setSurname(donorRequest.getSurname());
            if (donor.getProducts() != null) {
                donor.setProducts(donorRequest.getProducts());
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
    public Donor findDonorById(Long id) {
        return donorRepository.findById(id).get(); // TODO: get()
    }
}