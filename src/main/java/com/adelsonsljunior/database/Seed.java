package com.adelsonsljunior.database;

import com.adelsonsljunior.core.domain.entities.DistributionCenter;
import com.adelsonsljunior.core.domain.ports.repositories.IDistributionCenterRepository;
import com.adelsonsljunior.reader.Reader;

import java.util.List;

public class Seed {

    private final IDistributionCenterRepository distributionCenterRepository;

    public Seed(IDistributionCenterRepository distributionCenterRepository) {
        this.distributionCenterRepository = distributionCenterRepository;
    }

    public void up() {
        List<DistributionCenter> distributionCenters = Reader.readerDistributionCenters();

        for (DistributionCenter distributionCenter : distributionCenters) {
            distributionCenterRepository.create(distributionCenter);
        }

    }

}
