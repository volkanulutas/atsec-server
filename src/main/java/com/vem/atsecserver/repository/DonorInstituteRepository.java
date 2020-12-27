package com.vem.atsecserver.repository;

import com.vem.atsecserver.entity.DonorInstitute;
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