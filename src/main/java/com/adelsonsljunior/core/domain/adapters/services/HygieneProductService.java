package com.adelsonsljunior.core.domain.adapters.services;

import com.adelsonsljunior.core.domain.entities.HygieneProduct;
import com.adelsonsljunior.core.domain.ports.repositories.IHygieneProductRepository;
import com.adelsonsljunior.core.domain.ports.services.IHygieneProductService;

import java.util.List;

public class HygieneProductService implements IHygieneProductService {


    private final IHygieneProductRepository hygieneProductRepository;

    public HygieneProductService(IHygieneProductRepository hygieneProductRepository) {
        this.hygieneProductRepository = hygieneProductRepository;

    }

    @Override
    public void create(HygieneProduct hygieneProduct, int centerId) {
        int totalHygieneProducts = hygieneProductRepository.countByCenterId(centerId);

        if (totalHygieneProducts + 1 > 1000) {
            throw new IllegalArgumentException("--- O limite do estoque de produtos de higiene foi excedido, não é possível adicionar mais roupas! ---");
        }

        hygieneProductRepository.create(hygieneProduct, centerId);

    }

    @Override
    public List<HygieneProduct> findAllByCenterId(int centerId) {
        return hygieneProductRepository.findAllByCenterId(centerId);
    }

    @Override
    public void update(HygieneProduct hygieneProduct, Long productId) {

        HygieneProduct hygieneProductFound = hygieneProductRepository.findById(productId);
        if (hygieneProductFound == null) {
            throw new IllegalArgumentException("--- PRODUTO DE HIGIENE DE ID " + productId + " NÃO ENCONTRADO ---");
        }

        hygieneProductRepository.update(hygieneProduct, productId);

    }

    @Override
    public void delete(Long productId) {

        HygieneProduct hygieneProductFound = hygieneProductRepository.findById(productId);
        if (hygieneProductFound == null) {
            throw new IllegalArgumentException("\n--- PRODUTO DE HIGIENE DE ID " + productId + " NÃO ENCONTRADO ---\n");
        }

        hygieneProductRepository.delete(productId);

    }
}
