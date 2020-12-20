package com.vem.atsecserver.entity.sales;

/**
 * @author volkanulutas
 * @since 12.12.2020
 */
public enum EnumCustomerType {
    HOSPITAL("Hastane"),
    FIRM("Firma"),
    INDIVIDUAL("Bireysel"),
    ABROAD_AGENT("Yurt Dışı Acenta");

    private final String name;

    EnumCustomerType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static EnumCustomerType findByName(String name) {
        for (EnumCustomerType e : EnumCustomerType.values()) {
            if (e.getName().equals(name)) {
                return e;
            }
        }
        return null;
    }
}
