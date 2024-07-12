package com.adelsonsljunior.core.domain.ports.repositories;

import com.adelsonsljunior.core.domain.entities.HygieneProduct;

import java.util.List;

public interface IHygieneProductRepository {
    void create(HygieneProduct hygieneProduct, int centerId);
    HygieneProduct findById(Long productId);
    List<HygieneProduct> findAllByCenterId(int centerId);
    void update(HygieneProduct hygieneProduct, Long productId);
    void delete(Long productId);
    int countByCenterId(int centerId);
}
