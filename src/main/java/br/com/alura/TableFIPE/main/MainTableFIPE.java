package br.com.alura.TableFIPE.main;

import br.com.alura.TableFIPE.model.DataReceived;
import br.com.alura.TableFIPE.model.Model;
import br.com.alura.TableFIPE.model.Vehicle;
import br.com.alura.TableFIPE.service.ConsumeAPI;
import br.com.alura.TableFIPE.service.ConvertData;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class MainTableFIPE {

    private final Scanner scan = new Scanner(System.in);
    private final String BASE_URL = "https://parallelum.com.br/fipe/api/v1/";
    private final ConsumeAPI api = new ConsumeAPI();
    private final ConvertData converter = new ConvertData();

    public void showMenu() {
        String option = chooseVehicleType();
        String brandCode = chooseBrand(option);
        String modelCode = chooseModel(option, brandCode);
        List<String> vehicleCodes = chooseVehicleOptions(option, brandCode, modelCode);
        fetchAndDisplayVehicleDetails(option, brandCode, modelCode, vehicleCodes);
    }

    private String chooseVehicleType() {
        String[] vehicleTypes = {"carros", "motos", "caminhoes"};
        String menu = """
                *** OPTIONS ***
                1 - Car
                2 - Motorcycle
                3 - Truck
                ***************
                """;
        System.out.println(menu);

        int choice;
        do {
            System.out.print("Enter an option to consult the values: ");
            while (!scan.hasNextInt()) {
                System.out.print("Invalid input. Enter 1, 2 or 3: ");
                scan.next();
            }
            choice = scan.nextInt();
        } while (choice < 1 || choice > 3);

        return vehicleTypes[choice - 1];
    }

    private String chooseBrand(String vehicleType) {
        String url = BASE_URL + vehicleType + "/marcas";
        String json = api.getData(url);
        List<DataReceived> brands = converter.getList(json, DataReceived.class);

        brands.stream()
                .sorted(Comparator.comparing(DataReceived::code))
                .forEach(System.out::println);

        System.out.print("Enter the brand code: ");
        return scan.next().trim();
    }

    private String chooseModel(String vehicleType, String brandCode) {
        String url = String.format("%s%s/marcas/%s/modelos", BASE_URL, vehicleType, brandCode);
        String json = api.getData(url);
        Model model = converter.getData(json, Model.class);

        model.vehicleModels().stream()
                .sorted(Comparator.comparing(DataReceived::code))
                .forEach(System.out::println);

        System.out.print("Enter the model code: ");
        return scan.next().trim();
    }

    private List<String> chooseVehicleOptions(String vehicleType, String brandCode, String modelCode) {
        String url = String.format("%s%s/marcas/%s/modelos/%s/anos", BASE_URL, vehicleType, brandCode, modelCode);
        String json = api.getData(url);

        List<DataReceived> vehicle = converter.getList(json, DataReceived.class);

        return vehicle.stream()
                .map(DataReceived::code)
                .toList();
    }

    private void fetchAndDisplayVehicleDetails(String vehicleType, String brandCode, String modelCode, List<String> vehicleCodes) {
        vehicleCodes.stream()
                .map(vehicle -> {
                    String url = String.format("%s%s/marcas/%s/modelos/%s/anos/%s",
                            BASE_URL, vehicleType, brandCode, modelCode, vehicle);
                    String json = api.getData(url);
                    return converter.getData(json, Vehicle.class);
                })
                .forEach(System.out::println);
    }
}