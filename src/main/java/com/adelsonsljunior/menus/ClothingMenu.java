package com.adelsonsljunior.menus;

import com.adelsonsljunior.application.adapters.controllers.ClothingController;
import com.adelsonsljunior.core.domain.adapters.services.ClothingService;
import com.adelsonsljunior.core.domain.entities.Clothing;
import com.adelsonsljunior.core.domain.enums.ClothingGender;
import com.adelsonsljunior.core.domain.enums.ClothingSize;
import com.adelsonsljunior.infra.adapters.repositories.ClothingRepository;


import java.util.List;
import java.util.Scanner;

public class ClothingMenu {

    ClothingRepository clothingRepository = new ClothingRepository();
    ClothingService clothingService = new ClothingService(clothingRepository);
    ClothingController clothingController = new ClothingController(clothingService);

    public void open() {
        Scanner sc = new Scanner(System.in);
        int option;

        do {
            Dispalyer.displayClothingMenu();
            System.out.print("Digite a opção de que deseja realizar: ");

            option = sc.nextInt();

            switch (option) {
                case 1:
                    donateClothes();
                    break;
                case 2:
                    listClothes();
                    break;
                case 3:
                    updateClothing();
                    break;
                case 4:
                    deleteClothing();
                    break;
                case 5:
                    System.out.println("\n*** VOLTANDO PARA O MENU DE DOAÇÕES ***\n");
                    break;
                default:
                    System.out.println("\n*** OPÇÃO INVÁLIDA ***\n");
                    break;
            }

        } while (option != 5);

    }

    private void donateClothes() {

        Scanner sc = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);

        Dispalyer.displayCenters();

        System.out.print("Digite o id do Centro de Distribuição: ");
        int centerId = sc.nextInt();

        System.out.print("Digite a descrição da roupa: ");
        String description = sc2.nextLine();

        String gender;
        // loop para permitir que somente inputs válidos sejam aceitos
        do {
            System.out.print("Digite o gênero (M/F): ");
            gender = sc2.nextLine();
            try {
                gender = String.valueOf(ClothingGender.fromGender(gender));
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (!gender.equalsIgnoreCase("M") && !gender.equalsIgnoreCase("F"));

        String size;
        // loop para permitir que somente inputs válidos sejam aceitos
        do {
            System.out.print("Digite o tamanho (Infantil/PP/P/M/G/GG): ");
            size = sc2.nextLine();
            try {
                size = String.valueOf(ClothingSize.fromSize(size));
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (!size.equalsIgnoreCase("Infantil") && !size.equalsIgnoreCase("PP") && !size.equalsIgnoreCase("P") && !size.equalsIgnoreCase("M") && !size.equalsIgnoreCase("G") && !size.equalsIgnoreCase("GG"));


        Clothing clothing = new Clothing(description, gender, size);
        clothingController.create(clothing, centerId);

    }

    private void listClothes() {
        Scanner sc = new Scanner(System.in);
        Dispalyer.displayCenters();

        System.out.print("Digite o id do Centro de Distribuição: ");
        int centerId = sc.nextInt();

        List<Clothing> clothes = clothingRepository.findAllByCenterId(centerId);

        Dispalyer.displayClothes(clothes);

    }

    private void updateClothing() {
        Scanner sc = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);

        System.out.print("\nDigite o id da roupa que deseja atualizar: ");
        Long clothingId = sc.nextLong();

        System.out.print("Digite a nova descrição: ");
        String description = sc2.nextLine();

        String gender;
        // loop para permitir que somente inputs válidos sejam aceitos
        do {
            System.out.print("Digite o novo gênero (M/F): ");
            gender = sc2.nextLine();
            try {
                gender = String.valueOf(ClothingGender.fromGender(gender));
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (!gender.equalsIgnoreCase("M") && !gender.equalsIgnoreCase("F"));

        String size;
        // loop para permitir que somente inputs válidos sejam aceitos
        do {
            System.out.print("Digite o novo tamanho (Infantil/PP/P/M/G/GG): ");
            size = sc2.nextLine();
            try {
                size = String.valueOf(ClothingSize.fromSize(size));
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (!size.equalsIgnoreCase("Infantil") && !size.equalsIgnoreCase("PP") && !size.equalsIgnoreCase("P") && !size.equalsIgnoreCase("M") && !size.equalsIgnoreCase("G") && !size.equalsIgnoreCase("GG"));

        Clothing clothing = new Clothing(description, gender, size);
        clothingController.update(clothing, clothingId);
    }


    private void deleteClothing() {

        Scanner sc = new Scanner(System.in);

        System.out.print("\nDigite o id da roupa que deseja apagar: ");
        Long centerId = sc.nextLong();

        clothingController.delete(centerId);

    }


}
