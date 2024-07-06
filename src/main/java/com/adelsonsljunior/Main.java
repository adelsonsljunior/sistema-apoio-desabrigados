package com.adelsonsljunior;

import com.adelsonsljunior.database.Seed;
import com.adelsonsljunior.infra.adapters.repositories.DistributionCenterRepository;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");


        DistributionCenterRepository distributionCenterRepository = new DistributionCenterRepository();
        Seed seed = new Seed(distributionCenterRepository);
        seed.up();

    }
}