package com.vem.atsecserver.service.sales;

import com.vem.atsecserver.entity.sales.Customer;
import com.vem.atsecserver.repository.sales.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author volkanulutas
 * @since 01.01.2021
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer create(Customer customerRequest) {
        Customer customer = new Customer();
        customer.setName(customerRequest.getName());
        customer.setCustomerType(customerRequest.getCustomerType());
        customer.setAddress(customerRequest.getAddress());
        customer.setDefinition(customerRequest.getDefinition());
        customer.setDeleted(false);
        customer.setIdentityNumber(customerRequest.getIdentityNumber());
        customer.setTelephone(customerRequest.getTelephone());
        return customerRepository.save(customer);
    }

    @Override
    public Customer update(Customer customerRequest) {
        Optional<Customer> byId = customerRepository.findById(customerRequest.getId());
        if (byId.isPresent()) {
            Customer customer = byId.get();
            customer.setName(customerRequest.getName());
            customer.setCustomerType(customerRequest.getCustomerType());
            customer.setAddress(customerRequest.getAddress());
            customer.setDefinition(customerRequest.getDefinition());
            customer.setDeleted(false);
            customer.setIdentityNumber(customerRequest.getIdentityNumber());
            customer.setTelephone(customerRequest.getTelephone());
            return customerRepository.save(customer);
        }
        return null;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll().stream().filter(e -> !e.getDeleted()).collect(Collectors.toList());
    }

    @Override
    public Customer delete(Long id) {
        Optional<Customer> byId = customerRepository.findById(id);
        if (byId.isPresent()) {
            byId.get().setDeleted(true);
            customerRepository.save(byId.get());
            return byId.get();
        }
        return null;
    }

    @Override
    public Customer findCustomerById(Long id) {
        return customerRepository.findById(id).get(); //TODO: get()
    }
}