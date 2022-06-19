package com.vem.atsecserver.data.barcodegeneration;

import java.io.Serializable;

public class PackingPackingProductBarcode implements Serializable {

    private String serialNumber;
    private String donorCode; // Donor.code
    private String lot;
    private String size;

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getDonorCode() {
        return donorCode;
    }

    public void setDonorCode(String donorCode) {
        this.donorCode = donorCode;
    }

    public String getLot() {
        return lot;
    }

    public void setLot(String lot) {
        this.lot = lot;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
