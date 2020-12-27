package com.vem.atsecserver.payload.rawproduct;

import javax.validation.constraints.NotNull;

/**
 * @author volkanulutas
 * @since 12.12.2020
 */
public class RawProductRequest {
    @NotNull
    private Long id;

    private String donorCode;

    private String status;

    private String type;

    private String location;

    private String definition;

    private long acceptanceDate;

    private String information;

    private Boolean isDeleted;

    private String customerId;

    public RawProductRequest() {
        // default constructor.
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getAcceptanceDate() {
        return acceptanceDate;
    }

    public void setAcceptanceDate(long acceptanceDate) {
        this.acceptanceDate = acceptanceDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getDonorCode() {
        return donorCode;
    }

    public void setDonorCode(String donorCode) {
        this.donorCode = donorCode;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}
