import java.util.Scanner;

public class Cajero_nuevo {
    String NEGRITA = "\033[1m";
    String SUBRAYO = "\033[4m";
    String FINAL = "\033[0m";
    float retiro_total;
    Scanner scanner = new Scanner(System.in);

    public void menu() {
        System.out.println("1) Retirada de dinero");
        System.out.println("2) Ingreso de dinero");
        System.out.println("3) Salir");
    }

    public void opciones(int opcion, float[] saldoMax) {
        switch (opcion) {
            case 1:
                retirada(saldoMax);
                break;
            case 2:
                ingreso(saldoMax);
                break;
            case 3:
                break;
            default:
                System.out.println("Ingrese un número entre el 1 y el 3.");
                break;
        }
    }

    public void verif_saldo(float saldoTotal) {
        System.out.println("\n\tSu saldo actual es de " + saldoTotal + "€");
    }

    public void verif_tope(float saldoTotal, float max, float retira) {
        retiro_total -= retira;
        System.out.println("\tIntenta retirar " + retira + "€\n" +
                "\tTiene establecido ahora un tope de " + max + "€");
        System.out.println("\tSu saldo actual es de " + saldoTotal + "€");
    }

    public void retirada(float[] saldoMax) {
        float saldoTotal = saldoMax[0];
        float max = saldoMax[1];

        this.verif_saldo(saldoTotal);
        System.out.print("\tTeclee dinero a retirar: ");

        float retira = scanner.nextFloat();

        while (retira < 0 || retira > 600) {
            System.out.println("\tInserte un número entre 0 y 600 por favor.");
            System.out.print("\tTeclee dinero a retirar: ");
            retira = scanner.nextFloat();
        }

        retiro_total += retira;

        if (retira > saldoTotal) {
            System.out.println("\tNo tiene suficiente saldo en la cuenta");
        } else {
            if (retira > 0 && retiro_total <= 600) {
                max -= retira;
                saldoTotal -= retira;
                System.out.println("\tUsted retiró " + retira + "€");
                this.verif_saldo(saldoTotal);
            } else if (retiro_total > 600) {
                this.verif_tope(saldoTotal, max, retira);
            } else {
                System.out.println("Cantidad no válida.");
            }
        }

        saldoMax[0] = saldoTotal;
        saldoMax[1] = max;
    }

    public void ingreso(float[] saldoMax) {
        float saldoTotal = saldoMax[0];

        this.verif_saldo(saldoTotal);
        System.out.print("\tTeclee dinero a ingresar: ");
        float ingreso = scanner.nextFloat();

        while (ingreso < 0) {
            System.out.println("\tInserte un número positivo por favor.");
            System.out.print("\tTeclee dinero a ingresar: ");
            ingreso = scanner.nextFloat();
        }

        saldoTotal += ingreso;
        System.out.println("\tUsted ingresó " + ingreso + "€");
        this.verif_saldo(saldoTotal);

        saldoMax[0] = saldoTotal;
    }
}
