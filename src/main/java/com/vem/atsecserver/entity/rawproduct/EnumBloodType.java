package com.vem.atsecserver.entity.rawproduct;

public enum EnumBloodType {
    A_POSITIVE("A Rh (+)"),
    A_NEGATIVE("A Rh (-)"),
    B_POSITIVE("B Rh (+)"),
    B_NEGATIVE("B Rh (-)"),
    AB_POSITIVE("AB Rh (+)"),
    AB_NEGATIVE("AB Rh (-)"),
    ZERO_POSITIVE("0 Rh (+)"),
    ZERO_NEGATIVE("0 Rh (-)");

    private String name;

    EnumBloodType(String name) {
        this.name = name;
    }

    public static EnumBloodType getBloodTypeByName(String name){
        for (EnumBloodType bloodType: EnumBloodType.values()){
            if(bloodType.getName().equals(name)){
                return bloodType;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
