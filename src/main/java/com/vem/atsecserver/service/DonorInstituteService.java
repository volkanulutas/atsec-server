package com.vem.atsecserver.service;

import com.vem.atsecserver.entity.DonorInstitute;

import java.util.List;
import java.util.Optional;

/**
 * @author volkanulutas
 * @since 01.01.2021
 */
public interface DonorInstituteService {
    DonorInstitute create(DonorInstitute role);

    DonorInstitute update(DonorInstitute role);

    List<DonorInstitute> getAllDonorInstitutes();

    DonorInstitute delete(Long id);

    DonorInstitute findDonorInstituteById(Long id);

    Boolean existsByCode(String code);

    DonorInstitute findDonorById(Long id);
}
