package com.vem.atsecserver.entity.product;

/**
 * @author volkanulutas
 * @since 12.12.2020
 */
public enum EnumProductStatus {
    QUARANTINE("Karantina"),
    STOCK("Stok"),
    SAMPLE("Numune"),
    CASUALTY("Zaiyat"),
    RECALL("Geri Çağrılmış");

    private final String name;

    EnumProductStatus(String status) {
        this.name = status;
    }

    public String getName() {
        return name;
    }

    public static EnumProductStatus findByName(String name) {
        for (EnumProductStatus s : EnumProductStatus.values()) {
            if (s.getName().equals(name)) {
                return s;
            }
        }
        return null;
    }
}
