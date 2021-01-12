package com.vem.atsecserver.payload.sales;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author volkanulutas
 * @since 12.12.2020
 */
public class CustomerRequest {
    @NotNull
    private Long id;

    @NotBlank
    private String identityNumber;

    private String customerType; // Bireysel, Firma etc.

    private String definition;

    private String name;

    private String surname;

    private String address;

    private String telephone;

    private Boolean deleted;

    public CustomerRequest() {
        // Default constructor.
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
