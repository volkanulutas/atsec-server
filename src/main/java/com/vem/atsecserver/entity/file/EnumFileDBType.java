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

    RAW_BARCODE("Ham Ürün Barkodu"),

    QUARANTINE_TEST_RESULT("Karantina Test Sonucu"),

    QUARANTINE_ACCEPTANCE_FORM("Karantina Kabul Formu"),

    QUARANTINE_EXTRA_FILES("Karantina Ek Formlar"),

    RESULT_FILE_STICKER("Etiket");

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