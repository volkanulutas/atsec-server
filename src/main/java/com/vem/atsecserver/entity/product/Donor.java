package com.vem.atsecserver.entity.product;

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

    @Column(unique = true)
    private String identityNumber;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private String telephone;

    @Column
    private String address;

    //TODO: registerDate

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "donor_id")
    private List<Product> products;

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


    public Boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
