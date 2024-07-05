package com.adelsonsljunior;

import com.adelsonsljunior.application.adapters.controllers.DistributionController;
import com.adelsonsljunior.core.domain.DistributionCenter;
import com.adelsonsljunior.core.domain.adapters.services.DistribuitionCenterService;
import com.adelsonsljunior.infra.adapters.repositories.DistributionCenterRepository;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        DistributionCenterRepository repository = new DistributionCenterRepository();
        DistribuitionCenterService service = new DistribuitionCenterService(repository);
        DistributionController controller = new DistributionController(service);

        List<DistributionCenter> distributionCenters = controller.findAll();

        System.out.println(distributionCenters.size());
    }
}