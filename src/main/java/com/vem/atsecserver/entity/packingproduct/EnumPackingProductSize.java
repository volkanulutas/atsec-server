package com.vem.atsecserver.entity.packingproduct;

import java.util.ArrayList;
import java.util.List;

public enum EnumPackingProductSize {
    AT300100("AT300100","0,25 - 1 mm", "0,5 cc"),
    AT300101("AT300101,","0,25 - 1 mm", "1 cc"),
    AT300102("AT300102","0,25 - 1 mm", "2 cc"),
    AT300103("AT300104","0,25 - 1 mm", "3 cc"),
    AT300104("AT300104","0,25 - 1 mm", "4 cc"),
    AT300105("AT300105","0,25 - 1 mm", "5 cc"),
    AT300200("AT300200","1 - 2 mm", "0,5 cc"),
    AT300201("AT300201","1 - 2 mm", "1 cc"),
    AT300202("AT300202","1 - 2 mm", "2 cc"),
    AT300203("AT300203","1 - 2 mm", "3 cc"),
    AT300204("AT300204","1 - 2 mm", "4 cc"),
    AT300205("AT300205","1 - 2 mm", "5 cc"),
    AT300402("AT300402","2 - 4 mm", "2 cc"),
    AT300403("AT300403","2 - 4 mm", "3 cc"),
    AT300404("AT300404","2 - 4 mm", "4 cc"),
    AT300405("AT300405","2 - 4 mm", "5 cc"),
    AT300408("AT300408","2 - 4 mm", "8 cc"),
    AT300410("AT300410","2 - 4 mm", "10 cc"),
    AT300420("AT300420","2 - 4 mm", "20 cc"),
    AT300430("AT300430","2 - 4 mm", "30 cc"),
    AT300460("AT300460","2 - 4 mm", "60 cc"),
    AT301005("AT301005","4 - 10 mm", "5 cc"),
    AT301008("AT301008","4 - 10 mm", "8 cc"),
    AT301010("AT301010","4 - 10 mm", "10 cc"),
    AT301020("AT301020","4 - 10 mm", "20 cc"),
    AT301030("AT301030","4 - 10 mm", "30 cc"),
    AT301060("AT301060","4 - 10 mm", "60 cc"),
    AT311010("AT311010","10 x 10 x 10 mm", "10 x 10 x 10 mm"),
    AT311020("AT311020","10 x 10 x 20 mm", "10 x 10 x 20 mm"),
    AT311021("AT311021","10 x 12 x 20 mm", "10 x 12 x 20 mm"),
    AT311022("AT311022","20 x 20 x 20 mm", "20 x 20 x 20 mm"),
    AT311030("AT311030","10 x 10 x 30 mm", "10 x 10 x 30 mm"),
    AT311031("AT311031","10 x 12 x 30 mm", "10 x 12 x 30 mm"),
    AT311032("AT311032","10 x 20 x 30 mm", "10 x 20 x 30 mm"),
    AT311033("AT311033","12 x 20 x 30 mm", "12 x 20 x 30 mm"),
    AT311034("AT311034","20 x 20 x 30 mm", "20 x 20 x 30 mm");

    private String code;
    private String size;
    private String volume;

    EnumPackingProductSize(String code, String size, String volume) {
        this.code = code;
        this.size = size;
        this.volume = volume;
    }

    public static List<String> valuesByCode() {
        List<String> valuesList = new ArrayList<>();
        for (EnumPackingProductSize s : EnumPackingProductSize.values()) {
            valuesList.add(s.getCode());
        }
        return valuesList;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }
}
