package com.adelsonsljunior.application.adapters.controllers;

import com.adelsonsljunior.core.domain.DistributionCenter;
import com.adelsonsljunior.core.domain.ports.services.IDistributionCenterService;

import java.util.List;

public class DistributionController {

    private final IDistributionCenterService distributionCenterService;

    public DistributionController(IDistributionCenterService distributionCenterService) {

        this.distributionCenterService = distributionCenterService;
    }

    public List<DistributionCenter> findAll() {
        return distributionCenterService.findAll();
    }


}
