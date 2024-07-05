package com.adelsonsljunior.core.domain;

public class DistributionCenter {

    private Long id;
    private String name;
    private String postalCode;
    private int number;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String address) {
        this.postalCode = address;
    }

    @Override
    public String toString() {
        return "DistributionCenter [id=" + id + ", name=" + name + ", postalCode="
                + postalCode + ", number=" + number + "]";

    }

}
