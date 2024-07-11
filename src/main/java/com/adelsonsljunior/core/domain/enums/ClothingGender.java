package com.adelsonsljunior.core.domain.enums;

public enum ClothingGender {
    M("M"),
    F("F");

    private final String abbreviation;

    ClothingGender(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public static ClothingGender fromGender(String abbreviation) {
        for (ClothingGender value : ClothingGender.values()) {
            if (value.abbreviation.equalsIgnoreCase(abbreviation)) {
                return value;
            }
        }
        throw new IllegalArgumentException("Gênero da roupa roupa inválida!");
    }
}
