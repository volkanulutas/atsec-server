package com.vem.atsecserver.service.sales;

import com.vem.atsecserver.entity.sales.Customer;

import java.util.List;

/**
 * @author volkanulutas
 * @since 01.01.2021
 */
public interface CustomerService {
    Customer create(Customer customer);

    Customer update(Customer customer);

    List<Customer> getAllCustomers();

    Customer delete(Long id);

    Customer findCustomerById(Long id);
}
