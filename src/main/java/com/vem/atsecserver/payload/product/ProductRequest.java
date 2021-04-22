package com.vem.atsecserver.payload.product;

import com.vem.atsecserver.payload.rawproduct.DonorRequest;
import com.vem.atsecserver.payload.sales.CustomerRequest;
import com.vem.atsecserver.repository.rawproduct.DonorRepository;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author volkanulutas
 * @since 12.12.2020
 */
public class ProductRequest {
    @NotNull
    private Long id;

    private DonorRequest donor;

    private CustomerRequest customer;

    private String status;

    private String secCode;

    private String type;

    private List<String> preProcessingType;

    private String definition;

    private String information;

    private boolean deleted;

    // TODO: expiry date

    public ProductRequest() {
        // default constructor.
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DonorRequest getDonor() {
        return donor;
    }

    public void setDonor(DonorRequest donor) {
        this.donor = donor;
    }

    public CustomerRequest getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerRequest customer) {
        this.customer = customer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSecCode() {
        return secCode;
    }

    public void setSecCode(String secCode) {
        this.secCode = secCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getPreProcessingType() {
        return preProcessingType;
    }

    public void setPreProcessingType(List<String> preProcessingType) {
        this.preProcessingType = preProcessingType;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}