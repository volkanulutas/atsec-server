package com.vem.atsecserver.entity.report.rawproduct;

/**
 * @author volkanulutas
 * @since 12.12.2020
 */
public enum EnumRawProductFileDBType {

    RAW_CONFIRMATION_FILES("Onam Formu"),

    RAW_TRANSFER_FILES("Transfer Formu"),

    RAW_TRANSPORTATION_FILES("Taşıma Formu"),

    RAW_EXTRA_FILES("Ek Form"),

    RAW_BARCODE("Ham Ürün Barkodu"),

    QUARANTINE_TEST_RESULT("Karantina Test Sonucu"),

    QUARANTINE_ACCEPTANCE_FORM("Karantina Kabul Formu"),

    QUARANTINE_EXTRA_FILES("Karantina Ek Formlar"),

    RESULT_FILE_STICKER("Etiket");

    private String name;

    EnumRawProductFileDBType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static EnumRawProductFileDBType find(String name){
        for (EnumRawProductFileDBType t : EnumRawProductFileDBType.values()) {
            if (t.toString().equals(name)) {
                return t;
            }
        }
        return null;
    }

    public static EnumRawProductFileDBType findByName(String name) {
        for (EnumRawProductFileDBType t : EnumRawProductFileDBType.values()) {
            if (t.getName().equals(name)) {
                return t;
            }
        }
        return null;
    }
}