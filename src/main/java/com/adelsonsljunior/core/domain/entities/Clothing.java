package com.adelsonsljunior.core.domain.entities;

public class Clothing extends Donate {

    private String gender;
    private String size;

    public Clothing() {
    }

    public Clothing(String description, String gender, String size) {
        super(description);
        this.gender = gender;
        this.size = size;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Clothing [id=" + getId() + ", description=" + getDescription()
                + ", gender=" + gender + ", size=" + size + "]";
    }

}
