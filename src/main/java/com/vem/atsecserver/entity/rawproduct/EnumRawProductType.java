package com.vem.atsecserver.entity.rawproduct;

/**
 * @author volkanulutas
 * @since 12.12.2020
 */
public enum EnumRawProductType {

    NONE("None");
    // TODO: lookup table AB sisteminde dirsek kemiğininin türünü belirler.
    private String name;

    EnumRawProductType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static EnumRawProductType findByName(String name) {
        for (EnumRawProductType t : EnumRawProductType.values()) {
            if (t.getName().equals(name)) {
                return t;
            }
        }
        return null;
    }
}
