package com.vem.atsecserver.entity.rawproduct;

import com.vem.atsecserver.entity.Donor;
import com.vem.atsecserver.entity.DonorInstitute;
import com.vem.atsecserver.entity.sales.Customer;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author volkanulutas
 * @since 12.12.2020
 */
@Entity(name = "Raw_Product")
@Table(name = "Raw_Product")
public class RawProduct implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private EnumRawProductStatus status;

    @Enumerated(EnumType.STRING)
    @Column(length = 60)
    private EnumRawProductType type; // TODO: Kemiğin türü Product Code lookup type.

    @Column
    private String definition;

    @Column
    private String location;

    @Column
    private long acceptanceDate;

    @Column
    private String information; // NOTE: recall prosedüründe kullanılabilir.

    @ManyToOne(fetch = FetchType.EAGER)
    private DonorInstitute donorInstitute;

    @ManyToOne(fetch = FetchType.EAGER)
    private Customer customer;

    @ManyToOne(fetch = FetchType.EAGER)
    private Donor donor;

    @Column
    private boolean deleted;

    // TODO: sec e özgü bilgiler yer alacak.

    // NOTE: product group a ihtiyaç var mı? Yok bu ihtiyaç ProductStatus ve donorID ile sağlanır.

    public RawProduct() {
        // default constructor.
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Donor getDonor() {
        return donor;
    }

    public void setDonor(Donor donor) {
        this.donor = donor;
    }

    public DonorInstitute getDonorInstitute() {
        return donorInstitute;
    }

    public void setDonorInstitute(DonorInstitute donorInstitute) {
        this.donorInstitute = donorInstitute;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public EnumRawProductStatus getStatus() {
        return status;
    }

    public void setStatus(EnumRawProductStatus status) {
        this.status = status;
    }

    public EnumRawProductType getType() {
        return type;
    }

    public void setType(EnumRawProductType type) {
        this.type = type;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public long getAcceptanceDate() {
        return acceptanceDate;
    }

    public void setAcceptanceDate(long acceptanceDate) {
        this.acceptanceDate = acceptanceDate;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
