package com.vem.atsecserver.entity.product;

import java.util.ArrayList;
import java.util.List;

/**
 * @author volkanulutas
 * @since 12.12.2020
 */
public enum EnumProductPreProcessingType {
    TAKING_CARTILAGE("Kartilaj Alma"),
    CUTTING("Kesme"),
    WASHING("Yıkama");

    private final String name;

    EnumProductPreProcessingType(String status) {
        this.name = status;
    }

    public String getName() {
        return name;
    }

    public static EnumProductPreProcessingType findByName(String name) {
        for (EnumProductPreProcessingType s : EnumProductPreProcessingType.values()) {
            if (s.getName().equals(name)) {
                return s;
            }
        }
        return null;
    }

    public static List<String> valuesByName() {
        List<String> valuesList = new ArrayList<>();
        for (EnumProductPreProcessingType s : EnumProductPreProcessingType.values()) {
            valuesList.add(s.getName());
        }
        return valuesList;
    }
}