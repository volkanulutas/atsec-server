package com.vem.atsecserver.entity.rawproduct;

/**
 * @author volkanulutas
 * @since 12.12.2020
 */
public enum EnumRawProductStatus {
    QUARANTINE("Karantina"),
    REJECT("Red"),
    ACCEPTED("Kabul"),
    NO_STATUS("Atanmamış"),
    PRE_PROCESSING("Ön İşlem"), // artık Raw product listesinde gözükmesin. product oluşacak buradan sonra...
    MEDICAL_WASTE("Tibbi Atık");

    private final String name;

    EnumRawProductStatus(String status) {
        this.name = status;
    }

    public String getName() {
        return name;
    }

    public static EnumRawProductStatus findByName(String name) {
        for (EnumRawProductStatus s : EnumRawProductStatus.values()) {
            if (s.getName().equals(name)) {
                return s;
            }
        }
        return null;
    }
}
