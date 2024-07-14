package com.adelsonsljunior.menus;

import com.adelsonsljunior.application.adapters.controllers.FoodController;
import com.adelsonsljunior.core.domain.adapters.services.FoodService;
import com.adelsonsljunior.core.domain.entities.Food;
import com.adelsonsljunior.core.domain.enums.FoodUnitOfMeasurement;
import com.adelsonsljunior.infra.adapters.repositories.FoodRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class FoodMenu {

    FoodRepository foodRepository = new FoodRepository();
    FoodService foodService = new FoodService(foodRepository);
    FoodController foodController = new FoodController(foodService);

    public void open() {

        Scanner sc = new Scanner(System.in);

        int option = 0;

        do {
            Displayer.displayFoodMenu();
            System.out.print("Digite a opção que deseja realizar: ");
            option = sc.nextInt();

            switch (option) {

                case 1:
                    createFood();
                    break;
                case 2:
                    listFoods();
                    break;
                case 3:
                    updateFood();
                    break;
                case 4:
                    deleteFood();
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

    private void createFood() {
        Scanner sc = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);

        Displayer.displayCenters();
        System.out.print("Digite o id do Centro de Distribuição: ");
        int centerId = sc.nextInt();

        System.out.print("Digite a descrição do alimento: ");
        String description = sc2.nextLine();

        String measure;
        // loop para permitir que somente inputs válidos sejam aceitos
        do {
            System.out.print("Digite a unidade de medida ( G / KG / ML / L ): ");
            measure = sc2.nextLine();
            try {
                measure = String.valueOf(FoodUnitOfMeasurement.fromMeasure(measure));
            } catch (IllegalArgumentException e) {
                System.out.print(e.getMessage());
            }
        } while (!measure.equalsIgnoreCase("k")
                && !measure.equalsIgnoreCase("g")
                && !measure.equalsIgnoreCase("ml")
                && !measure.equalsIgnoreCase("l"));

        System.out.print("Digite a quantidade: ");
        int quantity = sc.nextInt();

        int year, month, day;
        LocalDate validity = null;

        // loop para permitir que somente inputs válidos sejam aceitos
        do {
            System.out.print("Digite o dia da validade: ");
            day = sc.nextInt();
            System.out.print("Digite o mês da validade: ");
            month = sc.nextInt();
            System.out.print("Digite o ano da validade: ");
            year = sc.nextInt();

            try {
                validity = LocalDate.of(year, month, day);
            } catch (Exception e) {
                System.out.println("Data inválida");
            }
        } while (validity == null);

        Food food = new Food(description, quantity, measure, validity);
        foodController.create(food, centerId);

    }

    private void listFoods() {

        Scanner sc = new Scanner(System.in);

        Displayer.displayCenters();
        System.out.print("Digite o id do Centro de Distribuição: ");
        int centerId = sc.nextInt();

        List<Food> foods = foodController.findAllByCenterId(centerId);
        Displayer.displayFoods(foods);
    }

    private void deleteFood() {
        Scanner sc = new Scanner(System.in);
        System.out.print("\nDigite o id do alimento que deseja apagar: ");
        Long foodId = sc.nextLong();

        foodController.delte(foodId);
    }

    private void updateFood() {
        Scanner sc = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);

        System.out.print("\nDigite o id do alimento atualizar: ");
        Long foodId = sc.nextLong();

        System.out.print("Digite a nova descrição do alimento: ");
        String description = sc2.nextLine();
        System.out.print("Digite a nova quantidade: ");
        int quantity = sc.nextInt();

        String measure;
        // loop para permitir que somente inputs válidos sejam aceitos
        do {
            System.out.print("Digite a nova unidade de medida ( G / KG / ML / L ): ");
            measure = sc2.nextLine();
            try {
                measure = String.valueOf(FoodUnitOfMeasurement.fromMeasure(measure));
            } catch (IllegalArgumentException e) {
                System.out.print(e.getMessage());
            }
        } while (!measure.equalsIgnoreCase("k")
                && !measure.equalsIgnoreCase("g")
                && !measure.equalsIgnoreCase("ml")
                && !measure.equalsIgnoreCase("l"));

        int year, month, day;
        LocalDate validity = null;

        // loop para permitir que somente inputs válidos sejam aceitos
        do {
            System.out.print("Digite o novo dia da validade: ");
            day = sc.nextInt();
            System.out.print("Digite o novo mês da validade: ");
            month = sc.nextInt();
            System.out.print("Digite o novo ano da validade: ");
            year = sc.nextInt();

            try {
                validity = LocalDate.of(year, month, day);
            } catch (Exception e) {
                System.out.println("Data inválida");
            }
        } while (validity == null);

        Food food = new Food(description, quantity, measure, validity);
        foodController.update(food, foodId);
    }


}
