package com.adelsonsljunior.core.domain.ports.services;

import com.adelsonsljunior.core.domain.DistributionCenter;

import java.util.List;

public interface IDistributionCenterService {

    List<DistributionCenter> findAll();
}
