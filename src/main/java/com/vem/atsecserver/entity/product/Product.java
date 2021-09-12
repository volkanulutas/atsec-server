package com.vem.atsecserver.entity.product;

import com.vem.atsecserver.entity.rawproduct.Donor;
import com.vem.atsecserver.entity.sales.Customer;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author volkanulutas
 * @since 25.12.2020
 */
@Entity(name = "Product")
@Table(name = "Product")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column
    private EnumProductStatus status;

    @ElementCollection(targetClass = EnumProductPreProcessingType.class)
    @CollectionTable
    @Enumerated(EnumType.STRING)
    private List<EnumProductPreProcessingType> preProcessingType;

    @Enumerated(EnumType.STRING)
    @Column(length = 60)
    private EnumProductType type; // TODO: Kemiğin türü Product Code lookup type.

    @Column
    private String definition;

    @Column
    private String information; // NOTE: recall prosedüründe kullanılabilir.

    // @Column(unique = true) // TODO:
    private String secCode;

    @ManyToOne(fetch = FetchType.EAGER)
    private Donor donor;

    @ManyToOne(fetch = FetchType.EAGER)
    private Customer customer;

    @Column
    private boolean deleted;

    // NOTE: product group a ihtiyaç var mı? Yok bu ihtiyaç ProductStatus ve donorID ile sağlanır.

    public Product() {
        // default constructor.
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EnumProductStatus getStatus() {
        return status;
    }

    public void setStatus(EnumProductStatus status) {
        this.status = status;
    }

    public List<EnumProductPreProcessingType> getPreProcessingType() {
        return preProcessingType;
    }

    public void setPreProcessingType(List<EnumProductPreProcessingType> preProcessingType) {
        this.preProcessingType = preProcessingType;
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

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getSecCode() {
        return secCode;
    }

    public void setSecCode(String secCode) {
        this.secCode = secCode;
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

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}