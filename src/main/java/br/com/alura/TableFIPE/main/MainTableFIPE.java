package br.com.alura.TableFIPE.main;

import br.com.alura.TableFIPE.service.ConsumeAPI;

import java.io.IOException;
import java.util.Scanner;

public class MainTableFIPE {
    private Scanner scan = new Scanner(System.in);
    private final String URL_BASE = "https://parallelum.com.br/fipe/api/v1/";
    private ConsumeAPI consumeAPI = new ConsumeAPI();

    public void ShowMenu() throws IOException, InterruptedException {
        String option = "";
        Integer choice = 0;

        var menu = """
                    *** OPTIONS ***
                    1 - Car
                    2 - Motorcycle
                    3 - Truck
                    ***************
                    """;

        System.out.println(menu);

        while (choice != 1 && (choice != 2) && choice != 3) {
            System.out.printf("Enter an option to consult the values: ");
            choice = scan.nextInt();
        }

        switch (choice) {
            case 1:
                option = "carros/marcas/59";
                break;
            case 2:
                option = "motos/marcas";
                break;
            case 3:
                option = "caminhoes/marcas";
                break;
        }

        String address = URL_BASE + option;

        var json = consumeAPI.getData(address);

        System.out.println(json);

        System.out.printf("Enter the brand code: ");
        scan.nextLine();

        System.out.printf("Enter the model: ");
        scan.nextLine();
    }
}