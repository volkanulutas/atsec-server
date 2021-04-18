package com.vem.atsecserver.entity.sales;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.vem.atsecserver.entity.rawproduct.RawProduct;

import javax.persistence.*;
import java.util.List;

/**
 * @author volkanulutas
 * @since 12.12.2020
 */
@Entity
@Table(name = "Customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String identityNumber;

    @Enumerated(EnumType.STRING)
    @Column(length = 60)
    private EnumCustomerType customerType;

    /*
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "customer_id")
    private List<RawProduct> rawProducts;
     */
    @Column
    private String definition;

    @Column
    private String name;

    @Column
    private String address;

    @Column
    private String telephone;

    @Column
    private boolean deleted;

    public Customer() {
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

    public EnumCustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(EnumCustomerType customerType) {
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

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
