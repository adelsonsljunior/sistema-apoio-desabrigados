package com.adelsonsljunior.core.domain.ports.repositories;

import com.adelsonsljunior.core.domain.entities.Food;

import java.util.List;

public interface IFoodRepository {
    void create(Food food, int centerId);
    void update(Food food, Long foodId);
    void delete(Long foodId);
    List<Food> findAllByCenterId(int centerId);
    Food findById(Long id);
    int countByCenterId(int centerId);
}
