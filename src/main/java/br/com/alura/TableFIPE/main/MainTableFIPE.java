package br.com.alura.TableFIPE.main;

import br.com.alura.TableFIPE.model.DataReceived;
import br.com.alura.TableFIPE.model.Vehicle;
import br.com.alura.TableFIPE.service.ConsumeAPI;
import br.com.alura.TableFIPE.service.ConvertData;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Scanner;

public class MainTableFIPE {
    private Scanner scan = new Scanner(System.in);
    private final String URL_BASE = "https://parallelum.com.br/fipe/api/v1/";
    private ConsumeAPI consumeAPI = new ConsumeAPI();
    private final ConvertData convertData = new ConvertData();

    public void ShowMenu() {
        String option = "", optionBrand = "/", optionModel = "/", optionYear = "/";
        Integer choice = 0;
        String json;

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
                option = "carros/marcas";
                break;
            case 2:
                option = "motos/marcas";
                break;
            case 3:
                option = "caminhoes/marcas";
                break;
        }

        String address = URL_BASE + option;
        json = consumeAPI.getData(address);

        System.out.println(json);

        System.out.printf("Enter the brand code: ");
        optionBrand += scan.next();

        optionBrand += "/modelos";

        address += optionBrand;
        json = consumeAPI.getData(address);

        System.out.println(json);

        System.out.printf("Enter the model code: ");
        optionModel += scan.next();

        optionModel += "/anos/";

        address += optionModel;
        json = consumeAPI.getData(address);

        System.out.println(json);

        String addressYears = address;

        List<DataReceived> data = List.of(convertData.getData(json, DataReceived[].class));
        List<String> codes = data.stream()
                .map(DataReceived::code)
                .toList();

        List<Vehicle> vehicles = codes.stream()
                .map(code -> {
                    String json2 = null;
                    json2 = consumeAPI.getData(addressYears + code);
                    return convertData.getData(json2, Vehicle.class);
                })
                .toList();

        vehicles.forEach(System.out::println);
    }
}