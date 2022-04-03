package com.vem.atsecserver.entity.report.product;

/**
 * @author volkanulutas
 * @since 12.12.2020
 */
public enum EnumProductFileDBType {
    GENERAL_STICKER("Etiket");

    private String name;

    EnumProductFileDBType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static EnumProductFileDBType find(String name){
        for (EnumProductFileDBType t : EnumProductFileDBType.values()) {
            if (t.toString().equals(name)) {
                return t;
            }
        }
        return null;
    }

    public static EnumProductFileDBType findByName(String name) {
        for (EnumProductFileDBType t : EnumProductFileDBType.values()) {
            if (t.getName().equals(name)) {
                return t;
            }
        }
        return null;
    }
}