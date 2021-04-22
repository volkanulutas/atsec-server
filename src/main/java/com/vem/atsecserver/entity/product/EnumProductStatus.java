package com.vem.atsecserver.entity.product;

/**
 * @author volkanulutas
 * @since 12.12.2020
 */
public enum EnumProductStatus {
    PRE_PROCESSING("Ön İşlem"), // artık Raw product listesinde gözükmesin. product oluşacak buradan sonra...
    PRE_PROCESSING_ACCEPTED("Ön İşlem - Kabul"),
    FREEZING_AFTER_PRE("Dondurma (Pre)"),
    COURSE_GRINDING("Kaba Öğütme"),
    FREEZING_AFTER_COURSE("Dondurma (Course)"),
    FINE_GRINDING("İnce Öğütme"),
    DELIPIDATION("Yıkama"),
    FREEZING_AFTER_DELIPIDATION("Dondurma (Yıkama)"),
    STERILIZATION("Sterilizasyon"),



    // Alttakiler teyit edilecektir.
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