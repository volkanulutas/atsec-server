package com.vem.atsecserver.entity.rawproduct;

/**
 * @author volkanulutas
 * @since 12.12.2020
 */
public enum EnumPoolStatus {
    QUARANTINE("Karantina"),
    STOCK("Stok");

    private final String status;

    EnumPoolStatus(String status) {
        this.status = status;
    }
}
