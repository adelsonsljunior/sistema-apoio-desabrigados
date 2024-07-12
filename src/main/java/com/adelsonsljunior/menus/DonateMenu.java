package com.adelsonsljunior.menus;

import com.adelsonsljunior.application.adapters.controllers.DistributionController;
import com.adelsonsljunior.core.domain.adapters.services.DistribuitionCenterService;
import com.adelsonsljunior.infra.adapters.repositories.DistributionCenterRepository;

import java.util.Scanner;

public class DonateMenu {

    private Scanner sc = new Scanner(System.in);
    private Scanner sc2 = new Scanner(System.in);

    DistributionCenterRepository distributionCenterRepository = new DistributionCenterRepository();
    DistribuitionCenterService distribuitionCenterService = new DistribuitionCenterService(distributionCenterRepository);
    DistributionController distributionController = new DistributionController(distribuitionCenterService);


    ClothingMenu clothingMenu = new ClothingMenu();
    HygieneProductMenu hygieneProductMenu = new HygieneProductMenu();
    FoodMenu foodMenu = new FoodMenu();

    public DonateMenu() {

    }

    public void open() {
        int option;

        do {
            Displayer.displayDonationMenu();
            System.out.print("Digite a opção de que deseja realizar: ");

            option = sc.nextInt();

            switch (option) {
                case 1:
                    System.out.println("\n*** INDO PARA O MENU DE ROUPAS ***\n");
                    clothingMenu.open();
                    break;
                case 2:
                    System.out.println("\n*** INDO PARA O MENU DE PRODUTOS DE HIGIENE ***\n");
                    hygieneProductMenu.open();
                    break;
                case 3:
                    System.out.println("\n*** INDO PARA O MENU DE ALIMENTOS ***\n");
                    foodMenu.open();
                    break;
                case 4:

                    break;
                case 5:
                    System.out.println("\n*** VOLTANDO PARA O MENU PRINCIPAL ***\n");
                    break;
                default:
                    System.out.println("\n*** OPÇÃO INVÁLIDA ***\n");
                    break;
            }

        } while (option != 5);
    }


}
