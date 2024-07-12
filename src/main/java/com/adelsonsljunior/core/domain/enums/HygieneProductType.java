package com.adelsonsljunior.core.domain.enums;

public enum HygieneProductType {
    SABONETE("Sabonete"),
    ESCOVA("Escova de dentes"),
    PASTA("Pasta de dentes"),
    ABSORVENTE("Absorvente");

    private final String type;

    HygieneProductType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static String fromType(String type){
        for (HygieneProductType value : HygieneProductType.values()) {
            if(value.type.equalsIgnoreCase(type)){
                return value.getType();
            }
        }

        throw new IllegalArgumentException("Tipo de produto de higiene inválido");
    }

}
