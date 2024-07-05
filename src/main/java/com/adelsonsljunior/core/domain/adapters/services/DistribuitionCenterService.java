package com.adelsonsljunior.core.domain.adapters.services;

import com.adelsonsljunior.core.domain.DistributionCenter;
import com.adelsonsljunior.core.domain.ports.repositories.IDistributionCenterRepository;
import com.adelsonsljunior.core.domain.ports.services.IDistributionCenterService;
import com.adelsonsljunior.infra.adapters.repositories.DistributionCenterRepository;

import java.util.List;

public class DistribuitionCenterService implements IDistributionCenterService {

    private final IDistributionCenterRepository distributionCenterRepository;

    public DistribuitionCenterService(IDistributionCenterRepository distributionCenterRepository) {
        this.distributionCenterRepository = distributionCenterRepository;
    }

    @Override
    public List<DistributionCenter> findAll() {
        return distributionCenterRepository.findAll();
    }
}
