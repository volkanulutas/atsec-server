package com.vem.atsecserver.data.barcodegeneration;

import java.io.Serializable;

public class DonorBarcode implements Serializable {

    private String donorCode; // Donor.code
    private String  status; // EnumRawProductStatus

    public DonorBarcode() {
    }

    public String getDonorCode() {
        return donorCode;
    }

    public void setDonorCode(String donorCode) {
        this.donorCode = donorCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
