package com.adelsonsljunior.core.domain.adapters.services;

import com.adelsonsljunior.core.domain.entities.Clothing;
import com.adelsonsljunior.core.domain.ports.repositories.IClothingRepository;
import com.adelsonsljunior.core.domain.ports.services.IClothingService;

import java.util.List;

public class ClothingService implements IClothingService {

    private final IClothingRepository clothingRepository;

    public ClothingService(IClothingRepository clothingRepository) {
        this.clothingRepository = clothingRepository;
    }

    @Override
    public void create(Clothing clothing, int centerId) {
        int totalClothes = 10000;

        if (totalClothes + 1 > 1000) {
            throw new IllegalArgumentException("--- O limite do estoque de roupas foi excedido, não é possível adicionar mais roupas! ---");
        }

        clothingRepository.create(clothing, centerId);
    }

    @Override
    public void update(Clothing clothing, Long clothingId) {

        Clothing clothingFound = clothingRepository.findById(clothingId);

        if (clothingFound == null) {
            throw new IllegalArgumentException("--- ROUPA DE ID " + clothingId + " NÃO ENCONTRADA! ---");
        }

        clothingRepository.update(clothing, clothingId);
    }

    @Override
    public void delete(Long clothingId) {

        Clothing clothingFound = clothingRepository.findById(clothingId);

        if (clothingFound == null) {
            throw new IllegalArgumentException("--- ROUPA DE ID " + clothingId + " NÃO ENCONTRADA! ---");
        }

        clothingRepository.delete(clothingId);
    }

    @Override
    public List<Clothing> findAllByCenterId(int centerId) {
        return clothingRepository.findAllByCenterId(centerId);
    }
}
