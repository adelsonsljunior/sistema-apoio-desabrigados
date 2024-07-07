package com.adelsonsljunior.menus;

import com.adelsonsljunior.application.adapters.controllers.DistributionController;
import com.adelsonsljunior.core.domain.adapters.services.DistribuitionCenterService;
import com.adelsonsljunior.infra.adapters.repositories.DistributionCenterRepository;
import com.github.freva.asciitable.AsciiTable;

import java.util.Scanner;

public class DonateMenu {

    private Scanner sc = new Scanner(System.in);
    private Scanner sc2 = new Scanner(System.in);

    DistributionCenterRepository distributionCenterRepository = new DistributionCenterRepository();
    DistribuitionCenterService distribuitionCenterService = new DistribuitionCenterService(distributionCenterRepository);
    DistributionController distributionController = new DistributionController(distribuitionCenterService);

    public DonateMenu() {

    }

    public void open() {
        int option;

        String[] headers = {"Opção", "Ação"};
        String[][] options = {
                {"1", "Doação para um Centro de Distribuição"},
                {"2", "Listar doações de um Centro de Distribuição"},
                {"3", "Editar doação de um Centro de Distribuição"},
                {"4", "Excluir doação de um Centro de Distribuição"},
                {"5", "Voltar para o Menu Principal"}
        };

        do {
            System.out.println(AsciiTable.getTable(headers, options));
            System.out.print("Digite a opção de que deseja realizar: ");

            option = sc.nextInt();

            switch (option) {
                case 1:

                    break;
                case 2:

                    break;
                case 3:

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
