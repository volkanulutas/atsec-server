package com.vem.atsecserver.data;

import java.io.Serializable;

public class DonorIdUtil implements Serializable {

    private String donorInstitution; // H0001

    private String yearPart; // 22

    private int generatedValue; // 000001

    public DonorIdUtil() {
    }

    public DonorIdUtil(String donorInstitution, String yearPart, int generatedValue) {
        this.donorInstitution = donorInstitution;
        this.yearPart = yearPart;
        this.generatedValue = generatedValue;
    }

    public void setDonorId(String donorIdStr) {
        donorInstitution = donorIdStr.substring(0, 5);
        yearPart = donorIdStr.substring(6, 7);
        generatedValue = Integer.parseInt(donorIdStr.substring(8, 13));
    }

    public String getDonorId() {
        String generatedValueStr = generatedValue + "";
        if (generatedValueStr.length() > 6) {
            throw new RuntimeException();
        } else {
            String newGeneratedValueStr = "";
            for (int i = generatedValueStr.length(); i < 6; i++) {
                newGeneratedValueStr = newGeneratedValueStr + "0";
            }
            generatedValueStr = newGeneratedValueStr + generatedValueStr;
        }

        StringBuilder builder = new StringBuilder();
        return builder.append(donorInstitution)
                .append(yearPart)
                .append(generatedValueStr).toString();
    }

    public String getDonorInstitution() {
        return donorInstitution;
    }

    public void setDonorInstitution(String donorInstitution) {
        this.donorInstitution = donorInstitution;
    }

    public String getYearPart() {
        return yearPart;
    }

    public void setYearPart(String yearPart) {
        this.yearPart = yearPart;
    }

    public int getGeneratedValue() {
        return generatedValue;
    }

    public void setGeneratedValue(int generatedValue) {
        this.generatedValue = generatedValue;
    }
}
