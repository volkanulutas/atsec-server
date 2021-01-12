package com.vem.atsecserver.repository.rawproduct;

import com.vem.atsecserver.entity.rawproduct.DonorInstitute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author volkanulutas
 * @since 25.12.2020
 */
@Repository
public interface DonorInstituteRepository extends JpaRepository<DonorInstitute, Long> {
    DonorInstitute findByCode(String code);
}