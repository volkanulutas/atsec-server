package com.vem.atsecserver.data.barcodegeneration;

import com.vem.atsecserver.entity.rawproduct.Donor;
import com.vem.atsecserver.entity.sales.Customer;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author volkanulutas
 * @since 25.12.2020
 */
public class ProductBarcode implements Serializable {
    private Long id;
    private String status;
    private String definition;
    private String secCode;
    private String donorId;
    private String customerId;

    public ProductBarcode() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    @Override
    public String toString() {
        return "ProductBarcode{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", definition='" + definition + '\'' +
                ", secCode='" + secCode + '\'' +
                ", donorId='" + donorId + '\'' +
                ", customerId='" + customerId + '\'' +
                '}';
    }
}