package com.vem.atsecserver.entity.product;

/**
 * @author volkanulutas
 * @since 12.12.2020
 */
public enum EnumProductType {

    NONE("None");
    // TODO: lookup table AB sisteminde dirsek kemiğininin türünü belirler.
    private String name;

    EnumProductType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static EnumProductType findByName(String name) {
        for (EnumProductType t : EnumProductType.values()) {
            if (t.getName().equals(name)) {
                return t;
            }
        }
        return null;
    }
}
