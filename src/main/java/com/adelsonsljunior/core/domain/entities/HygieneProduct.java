package com.adelsonsljunior.core.domain.entities;

public class HygieneProduct extends Donate {

    private String type;

    public HygieneProduct() {
    }

    public HygieneProduct(String type) {
        this.type = type;
    }

    public HygieneProduct(String description, String type) {
        super(description);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
