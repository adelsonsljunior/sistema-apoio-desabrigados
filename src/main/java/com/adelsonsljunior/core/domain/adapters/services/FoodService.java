package com.adelsonsljunior.core.domain.adapters.services;

import com.adelsonsljunior.core.domain.entities.Food;
import com.adelsonsljunior.core.domain.ports.repositories.IFoodRepository;
import com.adelsonsljunior.core.domain.ports.services.IFoodService;

import java.util.List;

public class FoodService implements IFoodService {

    private final IFoodRepository foodRepository;

    public FoodService(IFoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }


    @Override
    public void create(Food food, int centerId) {

        int totalFood = foodRepository.countByCenterId(centerId);

        if (totalFood + 1 > 1000) {
            throw new IllegalArgumentException("--- O limite do estoque de alimentos foi excedido, não é possível adicionar mais alimentos! ---");
        }

        foodRepository.create(food, centerId);
    }

    @Override
    public void update(Food food, Long foodId) {

        Food fooFound = foodRepository.findById(foodId);

        if (fooFound == null) {
            throw  new IllegalArgumentException("\n--- ALIMENTO DE ID " + foodId + " NÃO ENCONTRADO ---\n");
        }

        foodRepository.update(food, foodId);
    }

    @Override
    public void delete(Long foodId) {
        Food fooFound = foodRepository.findById(foodId);

        if (fooFound == null) {
            throw  new IllegalArgumentException("\n--- ALIMENTO DE ID " + foodId + " NÃO ENCONTRADO ---\n");
        }

        foodRepository.delete(foodId);
    }

    @Override
    public List<Food> findAllByCenterId(int centerId) {
        return foodRepository.findAllByCenterId(centerId);
    }
}
