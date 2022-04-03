package com.vem.atsecserver.service.rawproduct;

import com.vem.atsecserver.entity.rawproduct.Donor;

import java.util.List;

/**
 * @author volkanulutas
 * @since 01.01.2021
 */
public interface DonorService {

    Donor findByCitizenNumber(String citizenshipNumber);

    Donor create(Donor donorRequest);

    Donor update(Donor donorRequest);

    List<Donor> getAllDonors();

    boolean delete(Long id);

    Donor findDonorById(Long id);

    Donor findDonorByCode(String code);
}
