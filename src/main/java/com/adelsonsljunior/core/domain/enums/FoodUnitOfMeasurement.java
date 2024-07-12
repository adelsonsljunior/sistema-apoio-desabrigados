package com.adelsonsljunior.core.domain.enums;

public enum FoodUnitOfMeasurement {
    G("KG"),
    KG("G"),
    ML("ML"),
    L("L");

    private final String measure;

    FoodUnitOfMeasurement(String measure) {
        this.measure = measure;
    }

    public static FoodUnitOfMeasurement fromMeasure(String measure) {
        for (FoodUnitOfMeasurement value : FoodUnitOfMeasurement.values()) {
            if (value.measure.equalsIgnoreCase(measure)) {
                return value;
            }
        }

        throw new IllegalArgumentException("Unidade de medida inválida para alimento");
    }



}
