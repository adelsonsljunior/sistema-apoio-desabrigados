package com.adelsonsljunior.core.domain.ports.services;

import com.adelsonsljunior.core.domain.entities.Clothing;

import java.util.List;

public interface IClothingService {
    void create(Clothing clothing, int centerId);
    void update(Clothing clothing, Long clothingId);
    void delete(Long clothingId);
    List<Clothing> findAllByCenterId(int centerId);
}
