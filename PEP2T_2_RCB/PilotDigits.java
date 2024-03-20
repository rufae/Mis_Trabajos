package PEP2T_2_RCB;

import java.util.Scanner;

public class PilotDigits {
    public static void main(String[] args) {
        String NEGRITA = "\033[1m";
        String SUBRAYO = "\033[4m";
        String FINAL = "\033[0m";

        // Declaración de la variable
        int opcion;

        // Creación de los objetos según las clases
        DigiControl digiC = new DigiControl();
        DigiIban digiI = new DigiIban();
        Menu menu = new Menu();
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n\t\t\t" + NEGRITA + SUBRAYO + "MICROAPLICACIÓN DE UN BANCO" + FINAL + "\n");

        do {
            menu.menu();
            System.out.print("\nTeclee la opción: ");
            opcion = scanner.nextInt();
            System.out.println();

            switch (opcion) {
                case 1:
                    digiC.generadigits();
                    break;
                case 2:
                    digiC.verifdigits();
                    break;
                case 3:
                    digiI.generaIBAN();
                    break;
                case 4:
                    digiI.verifIBAN();
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Ingrese un número entre el 1 y el 5.");
                    break;
            }
        } while (opcion != 5);

    }
}
