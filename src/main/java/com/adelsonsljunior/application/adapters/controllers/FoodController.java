package com.adelsonsljunior.application.adapters.controllers;

import com.adelsonsljunior.core.domain.entities.Food;
import com.adelsonsljunior.core.domain.ports.services.IFoodService;

import java.util.List;

public class FoodController {

    private final IFoodService foodService;

    public FoodController(IFoodService foodService) {
        this.foodService = foodService;
    }

    public void create(Food food, int centerId) {
        try{
            foodService.create(food, centerId);
            System.out.println("\n*** ALIMENTO DOADA COM SUCESSO ***\n");
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    public void delte(Long foodId) {

        try {
            this.foodService.delete(foodId);
            System.out.println("\n*** ALIMENTO APAGADO COM SUCESSO ***\n");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void update(Food food, Long foodId) {

        try {
            foodService.update(food, foodId);
            System.out.println("\n*** ALIMENTO DOADO COM SUCESSO ***\n");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Food> findAllByCenterId(int centerId) {
        return foodService.findAllByCenterId(centerId);
    }


}
