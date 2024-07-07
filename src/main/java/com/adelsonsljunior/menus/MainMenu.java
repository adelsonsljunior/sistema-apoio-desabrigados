package com.adelsonsljunior.menus;

import com.github.freva.asciitable.AsciiTable;

import java.util.Scanner;

public class MainMenu {

    private Scanner sc = new Scanner(System.in);
    private Scanner sc2 = new Scanner(System.in);

    private DonateMenu donateMenu = new DonateMenu();

    public MainMenu() {

    }

    public void open() {

        int option;

        String[] headers = {"Opção", "Ação"};
        String[][] options = {
                {"1", "Abrir Menu de doaçoẽs"},
                {"2", "Sair"}
        };

        do {
            System.out.println(AsciiTable.getTable(headers, options));
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
