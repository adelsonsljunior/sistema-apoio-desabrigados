package com.adelsonsljunior.core.domain.enums;

public enum ClothingSize {
    INFANTIL("Infantil"),
    PP("PP"),
    P("P"),
    M("M"),
    G("G"),
    GG("GG");

    private final String size;

    ClothingSize(String size) {
        this.size = size;
    }

    public static ClothingSize fromSize(String size){
        for (ClothingSize value : values()){
            if (value.size.equalsIgnoreCase(size)){
                return value;
            }
        }
        throw new IllegalArgumentException("Tamanho da roupa inválida!");
    }
}
