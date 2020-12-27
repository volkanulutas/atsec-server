package com.vem.atsecserver.entity.product;

/**
 * @author volkanulutas
 * @since 12.12.2020
 */
public enum EnumRawProductStatus {
    QUARANTINE("Karantina"),
    STOCK("Stok"),
    SAMPLE("Numune"),
    CASUALTY("Zaiyat"),
    RECALL("Geri Çağrılmış");

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