import java.util.Scanner;

public class PilotCajero {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Cajero_nuevo cajero = new Cajero_nuevo();
        int opcion;
        float[] saldoMax = {Float.parseFloat(args[1]), Float.parseFloat(args[0])};

        System.out.println(cajero.NEGRITA + "\n\t\t" + cajero.SUBRAYO + "PROGRAMA CAJERO AUTOMÁTICO" + cajero.FINAL);

        do {
            System.out.println(cajero.NEGRITA + "\n\t\t\tMenú de opciones" + cajero.FINAL);
            System.out.println(cajero.NEGRITA + "\t\t\t================\n" + cajero.FINAL);

            cajero.menu();

            System.out.print("\n\t\t\tOpción: ");
            opcion = scanner.nextInt();

            cajero.opciones(opcion, saldoMax);

        } while (opcion != 3);

        System.out.println("\n\t\t  ¡Hasta luego!");

        scanner.close();
    }
}
