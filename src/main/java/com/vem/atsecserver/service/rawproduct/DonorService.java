package com.vem.atsecserver.service.rawproduct;

import com.vem.atsecserver.entity.rawproduct.Donor;

import java.util.List;

/**
 * @author volkanulutas
 * @since 01.01.2021
 */
public interface DonorService {
    Boolean existsByCode(String code);

    Donor create(Donor donorRequest);

    Donor update(Donor donorRequest);

    List<Donor> getAllDonors();

    Donor delete(Long id);

    Donor findDonorByCode(String code);

    Donor findDonorById(long parseLong);
}
