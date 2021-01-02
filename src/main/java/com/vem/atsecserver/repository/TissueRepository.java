package com.vem.atsecserver.repository;

import com.vem.atsecserver.entity.rawproduct.TissueType;
import com.vem.atsecserver.entity.user.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author volkanulutas
 * @since 02.01.2021
 */
@Repository
public interface TissueRepository extends JpaRepository<TissueType, Long> {
}
