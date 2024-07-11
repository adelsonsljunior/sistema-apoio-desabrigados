package com.adelsonsljunior.core.domain.entities;

public abstract class Donate {

    private Long id;
    private String description;

    public Donate(){

    }

    public Donate(String description){
        this.description = description;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
