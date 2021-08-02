package com.vem.atsecserver.entity.rawproduct;

import com.vem.atsecserver.entity.file.File;
import com.vem.atsecserver.entity.user.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * @author volkanulutas
 * @since 12.12.2020
 */
@Table(name = "rawproduct")
@Entity
public class RawProduct implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Donor donor;

    @OneToOne(targetEntity = TissueType.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "tissue_type_id")
    private TissueType tissueType;

    @ManyToOne(fetch = FetchType.EAGER)
    private DonorInstitute donorInstitute;

    @Column
    private long issueTissueDate; // Doku çıkarım tarihi

    @Column
    private long arrivalDate; // Merkeze geliş tarihi

    @OneToOne(targetEntity = Location.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "location_id")
    private Location location;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "secuser_id")
    private User checkedOutBy;

    @Column
    private EnumRawProductStatus status;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="file_id")
    private List <File> files;

    @Column
    private String definition;

    @Column
    private String information; // NOTE: recall prosedüründe kullanılabilir.

    @Column
    private Boolean deleted;

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

    public User getCheckedOutBy() {
        return checkedOutBy;
    }

    public void setCheckedOutBy(User checkedOutBy) {
        this.checkedOutBy = checkedOutBy;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RawProduct that = (RawProduct) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}