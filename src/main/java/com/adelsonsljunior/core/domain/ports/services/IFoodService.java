package com.adelsonsljunior.core.domain.ports.services;

import com.adelsonsljunior.core.domain.entities.Food;

import java.util.List;

public interface IFoodService {
    void create(Food food, int centerId);
    void update(Food food, Long foodId);
    void delete(Long foodId);
    List<Food> findAllByCenterId(int centerId);
}
