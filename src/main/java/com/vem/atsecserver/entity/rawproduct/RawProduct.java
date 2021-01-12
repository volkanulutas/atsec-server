package com.vem.atsecserver.entity.rawproduct;

import com.vem.atsecserver.entity.file.FileDB;
import com.vem.atsecserver.entity.user.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

/**
 * @author volkanulutas
 * @since 12.12.2020
 */
@Entity(name = "Raw_Product")
@Table(name = "Raw_Product")
public class RawProduct implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Donor donor;

    @ManyToOne(fetch = FetchType.EAGER)
    private DonorInstitute donorInstitute;

    @Column
    private long issueTissueDate; // Doku çıkarım tarihi

    @Column
    private long arrivalDate; // Merkeze geliş tarihi

    @OneToOne(targetEntity = TissueType.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "tissue_type_id")
    private TissueType tissueType;

    @OneToOne(targetEntity = Location.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "location_id")
    private Location location;

    @Column
    private EnumRawProductStatus status;

    @Column
    private String definition;

    @Column
    private String information; // NOTE: recall prosedüründe kullanılabilir.

    @Column
    private Boolean deleted;

    /*
    @OneToMany(mappedBy = "raw_product")
    private Collection<FileDB> files;
*/
    // NOTE: product group a ihtiyaç var mı? Yok bu ihtiyaç ProductStatus ve donorID ile sağlanır.

    public RawProduct() {
        // default constructor.
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public long getIssueTissueDate() {
        return issueTissueDate;
    }

    public void setIssueTissueDate(long issueTissueDate) {
        this.issueTissueDate = issueTissueDate;
    }

    public long getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(long arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public TissueType getTissueType() {
        return tissueType;
    }

    public void setTissueType(TissueType tissueType) {
        this.tissueType = tissueType;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public EnumRawProductStatus getStatus() {
        return status;
    }

    public void setStatus(EnumRawProductStatus status) {
        this.status = status;
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


    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
