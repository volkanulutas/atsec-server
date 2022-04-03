package com.vem.atsecserver.repository;

import com.vem.atsecserver.entity.packingproduct.PackingProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author volkanulutas
 * @since 12.12.2020
 */
@Repository
public interface PackingProductRepository extends JpaRepository<PackingProduct, Long> {
}
