package com.vem.atsecserver.payload.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vem.atsecserver.entity.product.Product;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author volkanulutas
 * @since 12.12.2020
 */
public class DonorRequest {
    @NotNull
    private Long id;

    @NotNull
    private String identityNumber;

    private String name;

    private String surname;

    private String telephone;

    private String address;

    @JsonIgnore
    private List<Product> products;

    private byte[] bloodTestPdfFile;

    private Boolean deleted;

    public DonorRequest() {
        // default constructor
    }

    public Long getId() {
        return id;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public byte[] getBloodTestPdfFile() {
        return bloodTestPdfFile;
    }

    public void setBloodTestPdfFile(byte[] bloodTestPdfFile) {
        this.bloodTestPdfFile = bloodTestPdfFile;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}