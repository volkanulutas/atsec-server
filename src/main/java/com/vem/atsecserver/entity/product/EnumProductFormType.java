package com.vem.atsecserver.entity.product;

import java.util.ArrayList;
import java.util.List;

/**
 * @author volkanulutas
 * @since 12.12.2020
 */
public enum EnumProductFormType {
    CIPS("Çips"),
    CUBE("Küp"),
    NONE("None");
    private String name;

    EnumProductFormType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static EnumProductFormType findByName(String name) {
        for (EnumProductFormType t : EnumProductFormType.values()) {
            if (t.getName().equals(name)) {
                return t;
            }
        }
        return null;
    }

    public static List<String> valuesByName() {
        List<String> valuesList = new ArrayList<>();
        for (EnumProductFormType s : EnumProductFormType.values()) {
            valuesList.add(s.getName());
        }
        return valuesList;
    }
}