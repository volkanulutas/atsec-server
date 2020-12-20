package com.vem.atsecserver.controller;

import com.vem.atsecserver.converter.CustomerConverter;
import com.vem.atsecserver.entity.sales.Customer;
import com.vem.atsecserver.payload.CustomerRequest;
import com.vem.atsecserver.payload.auth.response.ApiResponse;
import com.vem.atsecserver.payload.exception.ResourceNotFoundException;
import com.vem.atsecserver.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author volkanulutas
 * @since 12.12.2020
 */
@RestController
@RequestMapping(path = "/api/customer")
public class CustomerController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerConverter customerConverter;

    // TODO: pagination

    @Autowired
    private CustomerService customerService;

    @GetMapping("/{id}")
    public ResponseEntity<CustomerRequest> getCustomerById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(Optional.ofNullable(customerConverter.toRequest(customerService.findCustomerById(id)))
                .orElseThrow(() -> new ResourceNotFoundException("Customer not exists with id", id + "")));
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    public List<CustomerRequest> getAllCustomers() {
        List<CustomerRequest> result = new ArrayList<>();
        List<Customer> all = customerService.getAllCustomers();
        for (Customer customer : all) {
            result.add(customerConverter.toRequest(customer));
        }
        return result;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> create(/*@Valid*/ @RequestBody CustomerRequest customerRequest) {
        Customer customer = customerService.create(customerConverter.toEntity(customerRequest));
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{customerId}")
                .buildAndExpand(customer.getId()).toUri();
        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Customer created successfully."));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> update(@Valid @RequestBody CustomerRequest customerRequest) {
        Customer customer = customerService.update(customerConverter.toEntity(customerRequest));
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{customerId}")
                .buildAndExpand(customer.getId()).toUri();
        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Customer updated successfully."));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public @ResponseBody
    ResponseEntity<?> delete(@PathVariable("id") Long id) {
        CustomerRequest request = customerConverter.toRequest(customerService.delete(id));
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{customerId}")
                .buildAndExpand(request.getId()).toUri();
        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Customer deleted successfully."));
    }
}