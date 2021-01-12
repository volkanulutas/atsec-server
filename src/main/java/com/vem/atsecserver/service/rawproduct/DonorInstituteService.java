package com.vem.atsecserver.service.rawproduct;

import com.vem.atsecserver.entity.rawproduct.DonorInstitute;

import java.util.List;

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
