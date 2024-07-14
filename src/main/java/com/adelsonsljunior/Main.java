package com.adelsonsljunior;

import com.adelsonsljunior.database.Seed;
import com.adelsonsljunior.infra.adapters.repositories.ClothingRepository;
import com.adelsonsljunior.infra.adapters.repositories.FoodRepository;
import com.adelsonsljunior.infra.adapters.repositories.HygieneProductRepository;
import com.adelsonsljunior.menus.MainMenu;

public class Main {
    public static void main(String[] args) {


        ClothingRepository clothingRepository = new ClothingRepository();
        HygieneProductRepository hygieneProductRepository = new HygieneProductRepository();
        FoodRepository foodRepository = new FoodRepository();
        Seed seed = new Seed(clothingRepository, hygieneProductRepository, foodRepository);
        seed.up();

        MainMenu mainMenu = new MainMenu();
        mainMenu.open();

    }
}