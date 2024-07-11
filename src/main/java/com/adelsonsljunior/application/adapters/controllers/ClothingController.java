package com.adelsonsljunior.application.adapters.controllers;

import com.adelsonsljunior.core.domain.entities.Clothing;
import com.adelsonsljunior.core.domain.ports.services.IClothingService;

import java.util.List;

public class ClothingController {

    private final IClothingService clothingService;

    public ClothingController(IClothingService clothingService) {
        this.clothingService = clothingService;
    }

    public void create(Clothing clothing, int centerId) {

        try {
            clothingService.create(clothing, centerId);
            System.out.println("\n*** ROUPA DOADA COM SUCESSO ***\n");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

    }

    public List<Clothing> findAllByCenterId(int centerId) {
        return clothingService.findAllByCenterId(centerId);
    }

    public void delete(Long clothingId) {
        try {
            clothingService.delete(clothingId);
            System.out.println("\n*** ROUPA APAGADA COM SUCESSO ***\n");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void update(Clothing clothing, Long clothingId) {
        try {
            clothingService.update(clothing, clothingId);
            System.out.println("\n*** ROUPA ATUALIZADA COM SUCESSO ***\n");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }


}
