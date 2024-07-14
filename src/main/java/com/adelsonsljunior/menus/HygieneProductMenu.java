package com.adelsonsljunior.menus;

import com.adelsonsljunior.application.adapters.controllers.HygieneProductController;
import com.adelsonsljunior.core.domain.adapters.services.HygieneProductService;
import com.adelsonsljunior.core.domain.entities.HygieneProduct;
import com.adelsonsljunior.core.domain.enums.HygieneProductType;
import com.adelsonsljunior.infra.adapters.repositories.HygieneProductRepository;
import com.adelsonsljunior.reader.Reader;

import java.util.List;
import java.util.Scanner;

public class HygieneProductMenu {

    HygieneProductRepository hygieneProductRepository = new HygieneProductRepository();
    HygieneProductService hygieneProductService = new HygieneProductService(hygieneProductRepository);
    HygieneProductController hygieneProductController = new HygieneProductController(hygieneProductService);

    public void open() {

        Scanner sc = new Scanner(System.in);

        int option;

        do {
            Displayer.displayHygieneProductMenu();
            System.out.print("Digite a opção de que deseja realizar: ");
            option = sc.nextInt();

            switch (option) {
                case 1:
                    createHygieneProduct();
                    break;
                case 2:
                    listHygieneProducts();
                    break;
                case 3:
                    updateHygieneProduct();
                    break;
                case 4:
                    deleteHygieneProduct();
                    break;
                case 5:
                    createHyGieneProducFromCsv();
                    break;
                case 6:
                    System.out.println("\n*** VOLTANDO PARA O MENU DE DOAÇÕES ***\n");
                    break;
                default:
                    System.out.println("\n*** OPÇÃO INVÁLIDA ***\n");
                    break;
            }

        } while (option != 5);


    }

    private void createHygieneProduct() {

        Scanner sc = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);

        Displayer.displayCenters();

        System.out.print("Digite o id do Centro de Distribuição: ");
        int centerId = sc.nextInt();

        String type;
        // loop para permitir que somente inputs válidos sejam aceitos
        do {
            System.out.println("Digite o tipo (sabonete / escova de dentes / pasta de dentes / absorvente):");
            type = sc2.nextLine();

            try {
                type = String.valueOf(HygieneProductType.fromType(type));
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

        } while (!type.equalsIgnoreCase("sabonete")
                && !type.equalsIgnoreCase("escova de dentes")
                && !type.equalsIgnoreCase("pasta de dentes")
                && !type.equalsIgnoreCase("absorvente"));


        System.out.print("Digite a descrição do produto de higiene: ");
        String description = sc2.nextLine();

        HygieneProduct hygieneProduct = new HygieneProduct(description, type);
        hygieneProductController.create(hygieneProduct, centerId);

    }

    private void listHygieneProducts() {
        Scanner sc = new Scanner(System.in);

        Displayer.displayCenters();

        System.out.print("Digite o id do Centro de Distribuição: ");
        int centerId = sc.nextInt();

        List<HygieneProduct> hygieneProducts = hygieneProductController.findAllByCenterId(centerId);

        Displayer.displayHygieneProducts(hygieneProducts);

    }

    private void updateHygieneProduct() {
        Scanner sc = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);

        System.out.print("Digite o id do produto de higiene: ");
        Long productId = sc.nextLong();

        String type;
        do {
            System.out.println("Digite o novo tipo (sabonete / escova de dentes / pasta de dentes / absorvente):");
            type = sc2.nextLine();

            try {
                type = String.valueOf(HygieneProductType.fromType(type));
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

        } while (!type.equalsIgnoreCase("sabonete")
                && !type.equalsIgnoreCase("escova de dentes")
                && !type.equalsIgnoreCase("pasta de dentes")
                && !type.equalsIgnoreCase("absorvente"));


        System.out.print("Digite a nova descrição do produto de higiene: ");
        String description = sc2.nextLine();

        HygieneProduct hygieneProduct = new HygieneProduct(description, type);
        hygieneProductController.update(hygieneProduct, productId);
    }

    private void deleteHygieneProduct() {
        Scanner sc = new Scanner(System.in);

        System.out.print("\nDigite o id da roupa que deseja apagar: ");
        Long centerId = sc.nextLong();

        hygieneProductController.delete(centerId);
    }

    public void createHyGieneProducFromCsv() {
        Scanner sc = new Scanner(System.in);

        Displayer.displayCenters();
        System.out.print("Digite o id do Centro de Distribuição: ");
        int centerId = sc.nextInt();

        HygieneProduct hygieneProduct = Reader.readHygieneProduct();
        hygieneProductController.create(hygieneProduct, centerId);
    }


}
