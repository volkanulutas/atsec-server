package com.vem.atsecserver.entity.rawproduct;

public enum EnumSex {
    MALE("Erkek"),
    FEMALE("Kadın");

    private String name;

    EnumSex(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static EnumSex getSexByName(String name) {
        for (EnumSex sex : EnumSex.values()) {
            if (sex.getName().equals(name)) {
                return sex;
            }
        }
        return null;
    }
}
