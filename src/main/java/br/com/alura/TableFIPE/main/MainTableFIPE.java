package br.com.alura.TableFIPE.main;

import java.util.Scanner;

public class MainTableFIPE {
    public void ShowMenu() {
        Scanner scan = new Scanner(System.in);
        System.out.println("*** OPTIONS ***");
        System.out.println("1 - Car" +
                           "\n2 - Motorcycle" +
                           "\n3 - Truck"+
                           "\n***************");

        System.out.printf("Enter an option to consult the values: ");
        scan.nextLine();
    }
}
