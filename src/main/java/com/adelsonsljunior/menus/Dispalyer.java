package com.adelsonsljunior.menus;

import com.adelsonsljunior.core.domain.entities.Clothing;
import com.github.freva.asciitable.AsciiTable;

import java.util.List;
import java.util.stream.Stream;

// Classe para fazer o output formatado em tabelas entre os menus
public class Dispalyer {

    public static void displayMainMenu() {
        String[] headers = {"Opção", "Ação"};
        String[][] options = {
                {"1", "Abrir Menu de doaçoẽs"},
                {"2", "Sair"}
        };
        System.out.println(AsciiTable.getTable(headers, options));
    }

    public static void displayDonationMenu() {
        String[] headers = {"Opção", "Ação"};
        String[][] options = {
                {"1", "Gerenciar roupas"},
                {"2", "Gerenciar produtos de higiêne"},
                {"3", "Gerenciar alimentos"},
                {"4", ""},
                {"5", "Voltar para o Menu Principal"}
        };

        System.out.println(AsciiTable.getTable(headers, options));
    }

    public static void displayClothingMenu() {
        String[] headers = {"Opção", "Ação"};
        String[][] options = {
                {"1", "Doar roupa para um Centro de Distribuição"},
                {"2", "Listar roupas de um Centro de Distribuição"},
                {"3", "Editar roupa para um Centro de Distribuição"},
                {"4", "Apagar roupa para um Centro de Distribuição"},
                {"5", "Voltar para o Menu de Doações"}
        };

        System.out.println(AsciiTable.getTable(headers, options));
    }

    public static void displayCenters() {

        String[] headers = {"Id", "Nome"};

        String[][] options = {
                {"1", "Centro de Distribuição Esperança"},
                {"2", "Centro de Distribuição Prosperidade"},
                {"3", "Centro de Distribuição Reconstrução"}};


        System.out.println("\n" + AsciiTable.getTable(headers, options));
    }

    public static void displayClothes(List<Clothing> clothes) {
        Stream<Clothing> clothesStream = clothes.stream();

        String[] headers = {"Id", "Descrição", "Gênero", "Tamanho"};

        // transformando a lista de roupas em uma matriz
        String[][] options = clothesStream
                .map(clothing -> new String[]{clothing.getId().toString(), clothing.getDescription(), clothing.getGender(), clothing.getSize()})
                .toArray(String[][]::new);

        System.out.println("\n" + AsciiTable.getTable(headers, options) + "\n");
    }

}
