package com.vem.atsecserver.service;

import com.vem.atsecserver.entity.sales.Customer;

import java.util.List;

public interface CustomerService {
    Customer create(Customer customer);

    Customer update(Customer customer);

    List<Customer> getAllCustomers();

    Customer delete(Long id);

    Customer findCustomerById(Long id);
}
