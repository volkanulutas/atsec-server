package com.vem.atsecserver.entity.product;

import java.util.ArrayList;
import java.util.List;

/**
 * @author volkanulutas
 * @since 12.12.2020
 */
public enum EnumWashingType {
    MK_SOLUTION("Defatting İçin Solüsyon Hazırlama"),
    SONICATOR_1("Sonicatör 35 Derece 1.Saat"),
    MK_WASHING_1("Ultrasonik Banyo 1"),
    SONICATOR_2("Sonicatör 35 Derece 2.Saat"),
    MK_WASHING_2("Ultrasonik Banyo 2"),
    SONICATOR_3("Sonicatör 35 Derece 3.Saat"),
    MK_WASHING_3("Ultrasonik Banyo 3"),
    IZOTONIC_WASHING_1("İzotonik Yıkama 1"),
    IZOTONIC_WASHING_2("İzotonik Yıkama 2"),
    IZOTONIC_WASHING_3("İzotonik Yıkama 3");

    private final String name;

    EnumWashingType(String status) {
        this.name = status;
    }

    public static List<String> valuesByName() {
        List<String> valuesList = new ArrayList<>();
        for (EnumWashingType s : EnumWashingType.values()) {
            valuesList.add(s.getName());
        }
        return valuesList;
    }

    public String getName() {
        return name;
    }

    public static EnumWashingType findByName(String name) {
        for (EnumWashingType s : EnumWashingType.values()) {
            if (s.getName().equals(name)) {
                return s;
            }
        }
        return null;
    }
}