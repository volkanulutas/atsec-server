package com.vem.atsecserver.entity.rawproduct;

import com.vem.atsecserver.entity.file.FileDB;
import com.vem.atsecserver.entity.user.Permission;
import com.vem.atsecserver.entity.user.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author volkanulutas
 * @since 12.12.2020
 */
@Entity(name = "RawProduct")
@Table(name = "rawproduct")
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

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "rawproduct_file",
            joinColumns = @JoinColumn(name = "rawproduct_id"),
            inverseJoinColumns = @JoinColumn(name = "file_id"))
    private List<FileDB> files = new ArrayList<>();

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

    public List<FileDB> getFiles() {
        return files;
    }

    public void setFiles(List<FileDB> files) {
        this.files = files;
    }
}
