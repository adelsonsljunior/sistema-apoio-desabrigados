package com.adelsonsljunior;

import com.adelsonsljunior.database.Seed;
import com.adelsonsljunior.infra.adapters.repositories.DistributionCenterRepository;
import com.adelsonsljunior.menus.MainMenu;

public class Main {
    public static void main(String[] args) {

        //DistributionCenterRepository distributionCenterRepository = new DistributionCenterRepository();
        //Seed seed = new Seed(distributionCenterRepository);
        //seed.up();

        MainMenu mainMenu = new MainMenu();
        mainMenu.open();

    }
}