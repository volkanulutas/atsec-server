package com.vem.atsecserver.repository.rawproduct;

import com.vem.atsecserver.entity.rawproduct.EnumRawProductStatus;
import com.vem.atsecserver.entity.rawproduct.RawProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author volkanulutas
 * @since 12.12.2020
 */
@Repository
public interface RawProductRepository extends JpaRepository<RawProduct, Long> {
    List<RawProduct> findByStatus(EnumRawProductStatus status);
}
