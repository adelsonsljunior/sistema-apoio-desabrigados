package com.adelsonsljunior.core.domain.ports.services;

import com.adelsonsljunior.core.domain.entities.HygieneProduct;

import java.util.List;

public interface IHygieneProductService {
    void create(HygieneProduct hygieneProduct, int centerId);
    List<HygieneProduct> findAllByCenterId(int centerId);
    void update(HygieneProduct hygieneProduct, Long productId);
    void delete(Long productId);
}
