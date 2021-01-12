package com.vem.atsecserver.entity.file;

/**
 * @author volkanulutas
 * @since 12.12.2020
 */
public enum EnumFileDBType {

    RAW_APPROVE_FILES("Onam Formları"),

    RAW_TRANSFER_FILES("Transfer Formları"),

    RAW_TRANSPORTATION_FILES("Taşıma Formları"),

    RAW_EXTRA_FILES("Ek Formlar"),

    RAW_BARCODE("Ham Ürün Barkodu");

    private String name;

    EnumFileDBType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static EnumFileDBType findByName(String name) {
        for (EnumFileDBType t : EnumFileDBType.values()) {
            if (t.getName().equals(name)) {
                return t;
            }
        }
        return null;
    }
}
