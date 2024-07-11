package com.adelsonsljunior.core.domain.ports.repositories;

import com.adelsonsljunior.core.domain.entities.Food;

import java.util.List;

public interface IFoodRepository {

    void create(Food food, Long centerId);
    Food findById(Long id);
    List<Food> findAll(Long centerId);
    void update(Food food, Long centerId);
    void delete(Food food, Long centerId);
}
