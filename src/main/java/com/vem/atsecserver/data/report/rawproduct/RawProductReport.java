package com.vem.atsecserver.data.report.rawproduct;

import java.io.Serializable;
import java.util.Objects;

public class RawProductReport implements Serializable {

    private String donorId;
    private String donorInstitute;
    private String tissueType;
    private String issueTissueDate;
    private String arrivalDate;
    private String location;
    private String checkedOutBy;
    private String status;

    public String getDonorId() {
        return donorId;
    }

    public void setDonorId(String donorId) {
        this.donorId = donorId;
    }

    public String getDonorInstitute() {
        return donorInstitute;
    }

    public void setDonorInstitute(String donorInstitute) {
        this.donorInstitute = donorInstitute;
    }

    public String getTissueType() {
        return tissueType;
    }

    public void setTissueType(String tissueType) {
        this.tissueType = tissueType;
    }

    public String getIssueTissueDate() {
        return issueTissueDate;
    }

    public void setIssueTissueDate(String issueTissueDate) {
        this.issueTissueDate = issueTissueDate;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCheckedOutBy() {
        return checkedOutBy;
    }

    public void setCheckedOutBy(String checkedOutBy) {
        this.checkedOutBy = checkedOutBy;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RawProductReport that = (RawProductReport) o;
        return Objects.equals(donorId, that.donorId) && Objects.equals(tissueType, that.tissueType) && Objects.equals(issueTissueDate, that.issueTissueDate) && Objects.equals(arrivalDate, that.arrivalDate) && Objects.equals(location, that.location) && Objects.equals(checkedOutBy, that.checkedOutBy) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(donorId, tissueType, issueTissueDate, arrivalDate, location, checkedOutBy, status);
    }
}
