package com.vem.atsecserver.data.report.product;

import java.io.Serializable;
import java.util.Objects;

public class ProductReport implements Serializable {

    private Long id;
    private String status;
    private String definition;
    private String secCode;
    private String donorId;
    private String customerId;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getSecCode() {
        return secCode;
    }

    public void setSecCode(String secCode) {
        this.secCode = secCode;
    }

    public String getDonorId() {
        return donorId;
    }

    public void setDonorId(String donorId) {
        this.donorId = donorId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductReport that = (ProductReport) o;
        return Objects.equals(status, that.status) && Objects.equals(definition, that.definition) && Objects.equals(secCode, that.secCode) && Objects.equals(donorId, that.donorId) && Objects.equals(customerId, that.customerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, definition, secCode, donorId, customerId);
    }
}
