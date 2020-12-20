package com.vem.atsecserver.service;

import com.vem.atsecserver.entity.product.Donor;

import java.util.List;

public interface DonorService {
    boolean existsByIdentityNumber(String identityNumber);

    Donor create(Donor donorRequest);

    Donor update(Donor donorRequest);

    List<Donor> getAllDonors();

    Donor delete(Long id);

    Donor findDonorById(Long id);
}
