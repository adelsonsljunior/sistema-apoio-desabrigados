package com.adelsonsljunior.core.domain.enums;

public enum FoodUnitOfMeasurement {
    KG("Kg"),
    ML("Ml");


    private String measure;

    FoodUnitOfMeasurement(String measure) {
        this.measure = measure;
    }

    public static FoodUnitOfMeasurement fromMeasure(String measure) {
        for (FoodUnitOfMeasurement value : FoodUnitOfMeasurement.values()) {
            if (value.measure.equals(measure)) {
                return value;
            }
        }

        throw new IllegalArgumentException("Unidade de medida inválida para alimento");
    }



}
