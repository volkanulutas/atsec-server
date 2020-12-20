package com.vem.atsecserver.repository.sales;

import com.vem.atsecserver.entity.sales.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author volkanulutas
 * @since 12.12.2020
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
