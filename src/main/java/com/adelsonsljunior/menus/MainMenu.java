package com.adelsonsljunior.menus;

import java.util.Scanner;

public class MainMenu {

    private Scanner sc = new Scanner(System.in);
    private Scanner sc2 = new Scanner(System.in);

    private DonateMenu donateMenu = new DonateMenu();

    public MainMenu() {

    }

    public void open() {

        int option;

        do {
            Displayer.displayMainMenu();
            System.out.print("Digite a opção de que deseja realizar: ");

            option = sc.nextInt();

            switch (option) {
                case 1:
                    System.out.println("\n*** INDO PARA O MENU DOAÇOẼS ***\n");
                    donateMenu.open();
                    break;
                case 2:
                    System.out.println("\n*** ENCERRANDO APLICAÇÃO ***");
                    break;
                default:
                    System.out.println("\n*** OPÇÃO INVÁLIDA ***\n");
                    break;
            }

        } while (option != 2);
    }

}
