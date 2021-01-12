package com.vem.atsecserver.payload.rawproduct;

import com.vem.atsecserver.entity.rawproduct.EnumRawProductStatus;
import com.vem.atsecserver.entity.rawproduct.Location;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author volkanulutas
 * @since 12.12.2020
 */
public class RawProductRequest implements Serializable {
    @NotNull
    private Long id;

    private DonorRequest donor;

    private DonorInstituteRequest donorInstitute;

    private long issueTissueDate; // Doku çıkarım tarihi

    private long arrivalDate; // Merkeze geliş tarihi

    private TissueTypeRequest tissueType;

    private Location location;

    private String statusName;

    private String definition;

    private String information; // NOTE: recall prosedüründe kullanılabilir.

    private Boolean deleted;

    public RawProductRequest() {
        // default constructor.
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DonorRequest getDonor() {
        return donor;
    }

    public void setDonor(DonorRequest donor) {
        this.donor = donor;
    }

    public DonorInstituteRequest getDonorInstitute() {
        return donorInstitute;
    }

    public void setDonorInstitute(DonorInstituteRequest donorInstitute) {
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

    public TissueTypeRequest getTissueType() {
        return tissueType;
    }

    public void setTissueType(TissueTypeRequest tissueType) {
        this.tissueType = tissueType;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
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
