package com.vem.atsecserver.entity.product;

import java.util.ArrayList;
import java.util.List;

/**
 * @author volkanulutas
 * @since 12.12.2020
 */
public enum EnumProductGranulationType {
    F("F"),
    XF("XF"),
    XXF("XXF"),
    XXXF("XXXF");

    private String name;

    EnumProductGranulationType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static EnumProductGranulationType findByName(String name) {
        for (EnumProductGranulationType t : EnumProductGranulationType.values()) {
            if (t.getName().equals(name)) {
                return t;
            }
        }
        return null;
    }

    public static List<String> valuesByName() {
        List<String> valuesList = new ArrayList<>();
        for (EnumProductGranulationType s : EnumProductGranulationType.values()) {
            valuesList.add(s.getName());
        }
        return valuesList;
    }
}