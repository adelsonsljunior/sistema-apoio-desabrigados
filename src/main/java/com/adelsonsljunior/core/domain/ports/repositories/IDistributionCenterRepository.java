package com.adelsonsljunior.core.domain.ports.repositories;

import com.adelsonsljunior.core.domain.DistributionCenter;

import java.util.List;

public interface IDistributionCenterRepository {

    void create(DistributionCenter center);

    List<DistributionCenter> findAll();

}
