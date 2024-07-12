package com.adelsonsljunior.application.adapters.controllers;

import com.adelsonsljunior.core.domain.adapters.services.HygieneProductService;
import com.adelsonsljunior.core.domain.entities.HygieneProduct;

import java.util.List;

public class HygieneProductController {

    private final HygieneProductService hygieneProductService;

    public HygieneProductController(HygieneProductService hygieneProductService) {
        this.hygieneProductService = hygieneProductService;
    }

    public void create(HygieneProduct hygieneProduct, int centerId) {
        try {
            hygieneProductService.create(hygieneProduct, centerId);
            System.out.println("\n*** PRODUTO DE HIGIENE DOADA COM SUCESSO ***\n");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<HygieneProduct> findAllByCenterId(int centerId) {
        return hygieneProductService.findAllByCenterId(centerId);
    }

    public void delete(Long id) {

        try {
            hygieneProductService.delete(id);
            System.out.println("\n*** PRODUTO DE HIGIENE APAGADO COM SUCESSO ***\n");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void update(HygieneProduct hygieneProduct, Long hygieneProductId) {

        try {
            hygieneProductService.update(hygieneProduct, hygieneProductId);
            System.out.println("\n*** PRODUTO DE HIGIENE ATUALIZADO COM SUCESSO ***\n");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }


}
