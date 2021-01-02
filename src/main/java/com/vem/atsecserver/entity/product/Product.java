package com.vem.atsecserver.entity.product;

import com.vem.atsecserver.entity.Donor;
import com.vem.atsecserver.entity.rawproduct.EnumRawProductStatus;
import com.vem.atsecserver.entity.sales.Customer;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author volkanulutas
 * @since 25.12.2020
 */
@Entity(name = "Product")
@Table(name = "Product")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private EnumProductStatus status;

    @Enumerated(EnumType.STRING)
    @Column(length = 60)
    private EnumProductType type; // TODO: Kemiğin türü Product Code lookup type.

    @Column
    private String definition;

    @Column
    private long expirationDate;

    @Column
    private int splitLength;

    @Column
    private String information; // NOTE: recall prosedüründe kullanılabilir.

    @Column(unique = true)
    private String secCode;

    @ManyToOne(fetch = FetchType.EAGER)
    private Donor donor;

    @ManyToOne(fetch = FetchType.EAGER)
    private Customer customer;

    @Column
    private Boolean deleted;

    // TODO: sec e özgü bilgiler yer alacak.

    // NOTE: product group a ihtiyaç var mı? Yok bu ihtiyaç ProductStatus ve donorID ile sağlanır.

    public Product() {
        // default constructor.
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Donor getDonor() {
        return donor;
    }

    public void setDonor(Donor donor) {
        this.donor = donor;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getSecCode() {
        return secCode;
    }

    public void setSecCode(String secCode) {
        this.secCode = secCode;
    }

    public EnumProductStatus getStatus() {
        return status;
    }

    public void setStatus(EnumProductStatus status) {
        this.status = status;
    }

    public EnumProductType getType() {
        return type;
    }

    public void setType(EnumProductType type) {
        this.type = type;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public long getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(long expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getSplitLength() {
        return splitLength;
    }

    public void setSplitLength(int splitLength) {
        this.splitLength = splitLength;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
