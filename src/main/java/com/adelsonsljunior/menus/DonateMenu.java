package com.adelsonsljunior.menus;

import java.util.Scanner;

public class DonateMenu {

    private final ClothingMenu clothingMenu = new ClothingMenu();
    private final HygieneProductMenu hygieneProductMenu = new HygieneProductMenu();
    private final FoodMenu foodMenu = new FoodMenu();

    public DonateMenu() {

    }

    public void open() {
        Scanner sc = new Scanner(System.in);
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
                    System.out.println("\n*** VOLTANDO PARA O MENU PRINCIPAL ***\n");
                    break;
                default:
                    System.out.println("\n*** OPÇÃO INVÁLIDA ***\n");
                    break;
            }

        } while (option != 4);
    }


}
