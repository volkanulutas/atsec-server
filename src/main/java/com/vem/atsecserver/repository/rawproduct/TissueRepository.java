package com.vem.atsecserver.repository.rawproduct;

import com.vem.atsecserver.entity.rawproduct.TissueType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author volkanulutas
 * @since 02.01.2021
 */
@Repository
public interface TissueRepository extends JpaRepository<TissueType, Long> {
}
