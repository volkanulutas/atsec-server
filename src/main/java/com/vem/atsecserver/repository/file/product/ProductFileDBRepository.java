package com.vem.atsecserver.repository.file.product;

import com.vem.atsecserver.entity.product.Product;
import com.vem.atsecserver.entity.report.product.EnumProductFileDBType;
import com.vem.atsecserver.entity.report.product.ProductFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.stream.Stream;

/**
 * @author volkanulutas
 * @since 03.01.2020
 */
@Repository
public interface ProductFileDBRepository extends JpaRepository<ProductFile, Long> {
    Collection<ProductFile> findByFileDBType(EnumProductFileDBType fileDBType);
    Stream<ProductFile> findByProduct(Product rawProduct);
}
