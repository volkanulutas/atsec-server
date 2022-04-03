package com.vem.atsecserver.converter;

import com.vem.atsecserver.entity.sales.Customer;
import com.vem.atsecserver.entity.sales.EnumCustomerType;
import com.vem.atsecserver.payload.sales.CustomerRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author volkanulutas
 * @since 01.01.2021
 */
@Slf4j
@Component
public class CustomerConverter {

    public Customer toEntity(CustomerRequest request) {
        if (request == null) {
            log.error("Error is occurred while converting customer.");
            return null;
        }
        Customer entity = new Customer();
        entity.setName(request.getName());
        entity.setTelephone(request.getTelephone());
        entity.setIdentityNumber(request.getIdentityNumber());
        entity.setDeleted(request.getDeleted());
        entity.setCustomerType(EnumCustomerType.findByName(request.getCustomerType())); // Bireysel, Firma etc.
        entity.setDefinition(request.getDefinition());
        entity.setAddress(request.getAddress());
        if (request.getId() != null) {
            entity.setId(request.getId());
        }
        return entity;
    }

    public CustomerRequest toRequest(Customer entity) {
        if (entity == null) {
            log.error("Error is occurred while converting customer.");
            return null;
        }
        CustomerRequest request = new CustomerRequest();
        request.setName(entity.getName());
        request.setTelephone(entity.getTelephone());
        request.setIdentityNumber(entity.getIdentityNumber());
        request.setDeleted(entity.isDeleted());
        request.setCustomerType(entity.getCustomerType().getName());
        request.setDefinition(entity.getDefinition());
        request.setAddress(entity.getAddress());
        request.setId(entity.getId());
        return request;
    }
}
