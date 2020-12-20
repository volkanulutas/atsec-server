package com.vem.atsecserver.data.barcodegeneration;

/**
 * @author volkanulutas
 * @since 12.12.2020
 */
public class SecCode {
    // DE 000102 0000012312312 B 0702001 001 20180731
    // IT 00R207 0000000010479 E 0000065 003 20180731

    // Two character ISO country code: TR, IT.
    private String countryCode;
    // TE number as assigned in the EU TE Compendium
    private String teNumber;
    // Donation Identification Number assigned by the TE: 0000000010479.
    private String donationIdenNumber;
    // Coding System Identifier for EUTC.
    private String codingSystemIden;
    // EUTC Code padded to seven characters with five leading zeros
    private String productTypeCode;
    // Three character split number
    private int splitNumber;
    // Expiration date in YYYYMMDD format
    private String expirationDate;

    public SecCode() {
    }

    public SecCode(String countryCode, String teNumber,
                   String donationIdenNumber, String codingSystemIden,
                   String productTypeCode, int splitNumber, String expirationDate) {
        this.countryCode = countryCode;
        this.teNumber = teNumber;
        this.donationIdenNumber = donationIdenNumber;
        this.codingSystemIden = codingSystemIden;
        this.productTypeCode = productTypeCode;
        this.splitNumber = splitNumber;
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(countryCode)
                .append(teNumber)
                .append(donationIdenNumber)
                .append(codingSystemIden)
                .append(productTypeCode)
                .append(splitNumber)
                .append(expirationDate);
        return builder.toString();
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getTeNumber() {
        return teNumber;
    }

    public void setTeNumber(String teNumber) {
        this.teNumber = teNumber;
    }

    public String getDonationIdenNumber() {
        return donationIdenNumber;
    }

    public void setDonationIdenNumber(String donationIdenNumber) {
        this.donationIdenNumber = donationIdenNumber;
    }

    public String getCodingSystemIden() {
        return codingSystemIden;
    }

    public void setCodingSystemIden(String codingSystemIden) {
        this.codingSystemIden = codingSystemIden;
    }

    public String getProductTypeCode() {
        return productTypeCode;
    }

    public void setProductTypeCode(String productTypeCode) {
        this.productTypeCode = productTypeCode;
    }

    public int getSplitNumber() {
        return splitNumber;
    }

    public void setSplitNumber(int splitNumber) {
        this.splitNumber = splitNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }
}
