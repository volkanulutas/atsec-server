package com.vem.atsecserver.repository;

import com.vem.atsecserver.entity.product.Pool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author volkanulutas
 * @since 12.12.2020
 */
@Repository
public interface PoolRepository extends JpaRepository<Pool, Long> {
}
