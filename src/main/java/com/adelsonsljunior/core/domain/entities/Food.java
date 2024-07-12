package com.adelsonsljunior.core.domain.entities;

import java.time.LocalDate;

public class Food extends Donate {

    private int quantity;
    private String unitOfMeasurement;
    private LocalDate validity;

    public Food() {
    }

    public Food(String description, int quantity, String unitOfMeasurement, LocalDate validity) {
        super(description);
        this.quantity = quantity;
        this.unitOfMeasurement = unitOfMeasurement;
        this.validity = validity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDate getValidity() {
        return validity;
    }

    public void setValidity(LocalDate validity) {
        this.validity = validity;
    }

    public String getUnitOfMeasurement() {
        return unitOfMeasurement;
    }

    public void setUnitOfMeasurement(String unitOfMeasurement) {
        this.unitOfMeasurement = unitOfMeasurement;
    }

}
