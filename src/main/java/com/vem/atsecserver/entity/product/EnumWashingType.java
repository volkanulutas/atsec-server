package com.vem.atsecserver.entity.product;

import java.util.ArrayList;
import java.util.List;

/**
 * @author volkanulutas
 * @since 12.12.2020
 */
public enum EnumWashingType {
    MK_SOLUTION("MK Solüsyon"),
    SENICATOR_35("Sonicatör 35 Derece"),
    MK_WASHING_1("MK Yıkama 1"),
    MK_WASHING_2("MK Yıkama 2"),
    MK_WASHING_3("MK Yıkama 3"),
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