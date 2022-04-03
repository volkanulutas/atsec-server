package com.vem.atsecserver.entity.rawproduct;

public enum EnumLocationType {

    NORMAL("NORMAL"), // K1, K2, ...
    REJECT("REJECT"), // B1, B2, ...
    ACCEPT("ACCEPT"); // A1, A2, ...

    private String name;

    EnumLocationType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
