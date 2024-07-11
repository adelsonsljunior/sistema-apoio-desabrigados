package com.adelsonsljunior.core.domain.ports.repositories;

import com.adelsonsljunior.core.domain.entities.Clothing;

import java.util.List;

public interface IClothingRepository {
    void create(Clothing clothing, int centerId);
    void update(Clothing clothing, Long clothingId);
    void delete(Long clothingId);
    Clothing findById(Long clothingId);
    List<Clothing> findAllByCenterId(int centerId);
    int countByCenterId(int centerId);
}
