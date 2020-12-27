package com.vem.atsecserver.repository;

import com.vem.atsecserver.entity.Donor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author volkanulutas
 * @since 12.12.2020
 */
@Repository
public interface DonorRepository extends JpaRepository<Donor, Long> {

    Donor findTopByOrderByIdDesc();

    Donor findByCode(String code);
}