package com.vem.atsecserver.payload.rawproduct;

import com.vem.atsecserver.payload.rawproduct.RawProductRequest;

import java.io.Serializable;
import java.util.List;

/**
 * @author volkanulutas
 * @since 25.12.2020
 */
public class DonorInstituteRequest implements Serializable {
    private Long id;

    private String code;

    private String name;

    private List<RawProductRequest> rawProducts;

    private Boolean deleted;

    public DonorInstituteRequest() {
        // Default constructor.
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<RawProductRequest> getRawProducts() {
        return rawProducts;
    }

    public void setRawProducts(List<RawProductRequest> rawProducts) {
        this.rawProducts = rawProducts;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "DonorInstituteRequest{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", rawProducts=" + rawProducts +
                ", deleted=" + deleted +
                '}';
    }
}