package com.vem.atsecserver.payload.rawproduct;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vem.atsecserver.entity.rawproduct.RawProduct;

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
    private String code;

    private String citizenshipNumber;

    private String name;

    private String surname;

    private String telephone;

    private String address;

    @JsonIgnore
    private List<RawProduct> products;

    private byte[] bloodTestPdfFile;

    private Boolean deleted;

    public DonorRequest() {
        // default constructor
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

    public String getCitizenshipNumber() {
        return citizenshipNumber;
    }

    public void setCitizenshipNumber(String citizenshipNumber) {
        this.citizenshipNumber = citizenshipNumber;
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

    public List<RawProduct> getProducts() {
        return products;
    }

    public void setProducts(List<RawProduct> products) {
        this.products = products;
    }

    public byte[] getBloodTestPdfFile() {
        return bloodTestPdfFile;
    }

    public void setBloodTestPdfFile(byte[] bloodTestPdfFile) {
        this.bloodTestPdfFile = bloodTestPdfFile;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}