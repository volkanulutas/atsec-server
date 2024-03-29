package com.vem.atsecserver.repository.product;

import com.vem.atsecserver.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author volkanulutas
 * @since 12.12.2020
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
