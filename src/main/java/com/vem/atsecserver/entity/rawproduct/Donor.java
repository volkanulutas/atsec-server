package com.vem.atsecserver.entity.rawproduct;

import com.vem.atsecserver.entity.rawproduct.RawProduct;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author volkanulutas
 * @since 12.12.2020
 */
@Entity(name = "Donor")
@Table(name = "Donor")
public class Donor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 8)
    private String code;

    @Column(unique = true, length = 11)
    private String citizenshipNumber;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private String telephone;

    @Column
    private String address;

    @Column
    private long registeredDate;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "donor_id")
    private List<RawProduct> rawProducts;

    @Lob
    private byte[] bloodTestPdfFile;

    @Column
    private Boolean deleted;

    public Donor() {
        // default constructor.
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

    public List<RawProduct> getRawProducts() {
        return rawProducts;
    }

    public void setRawProducts(List<RawProduct> rawProducts) {
        this.rawProducts = rawProducts;
    }

    public byte[] getBloodTestPdfFile() {
        return bloodTestPdfFile;
    }

    public void setBloodTestPdfFile(byte[] bloodTestPdfFile) {
        this.bloodTestPdfFile = bloodTestPdfFile;
    }

    public long getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(long registeredDate) {
        this.registeredDate = registeredDate;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
