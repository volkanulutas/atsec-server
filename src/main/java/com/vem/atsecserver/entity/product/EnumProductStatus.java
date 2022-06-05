package com.vem.atsecserver.entity.product;

/**
 * @author volkanulutas
 * @since 12.12.2020
 */
public enum EnumProductStatus {
    PRE_PROCESSING("Ön İşlem"), // artık Raw product listesinde gözükmesin. product oluşacak buradan sonra...

    PRE_PROCESSING_ACCEPTED("Ön İşlem - Kabul"),

    FREEZING_1_ACCEPTED("Dondurma 1 Sonrası"),

    // COURSE_GRINDING_ACCEPTED("Öğütme (Course) Sonrası"), // silinecek

    GRANULATION_AFTER("Öğütme Sonrası"),

    DEFATTING_ACCEPTED("Defatting Sonrası"), // -> Sterilazasyon

    FREEZING_3_ACCEPTED("Dondurma 3 Sonrası"),

    STERILIZATION_ACCEPTED("Kimyasal Sterilizasyon"),

    STERILIZATION_AFTER("Kimyasal Sterilizasyon Sonrası"),

    DETERMINATION_OF_MOISTURE("Nem Tayini"),

    PRE_PACKING("Paketleme Öncesi"),

    PACKING("Paketleme");

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