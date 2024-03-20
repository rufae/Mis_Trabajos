package PEP2T_2_RCB;

import java.math.BigInteger;
import java.util.Scanner;

public class DigiControl {
    // Declaración de variables
    int[] valoresConstantes = {1, 2, 4, 8, 5, 10, 9, 7, 3, 6};
    String numeros_dc1;

    // Creacion del objeto Scanner
    Scanner scanner = new Scanner(System.in);

    // Método que pide al usuario los datos necesarios para crear los digitos de control
    public void generadigits() {

        System.out.print("\tIntroduzca los 4 dígitos correspondientes al banco: ");
        String numBanco = scanner.nextLine();
        System.out.print("\tIntroduzca los 4 dígitos correspondientes a la sucursal: ");
        String numSucur = scanner.nextLine();
        System.out.print("\tIntroduzca los 10 dígitos correspondientes al nº cuenta: ");
        String numCuenta = scanner.nextLine();
        System.out.println();

        numeros_dc1 = "00" + numBanco + numSucur;

        int dc1 = dc(numeros_dc1);
        int dc2 = dc(numCuenta);

        System.out.println("\t" + numBanco + " " + numSucur + " " + dc1 + dc2 + " " + numCuenta + "\n");
    }

    // Método que crea los dígitos de control
    public int dc(String digitos){

        int suma_tot = 0;
        int fin = 0;

        for (int i = 0; i < 10 ; i++) {

            int digito_00 = Integer.parseInt(String.valueOf(digitos.charAt(i)));

            suma_tot += digito_00 * valoresConstantes[i];
            int resto_00 = suma_tot % 11;
            fin = 11 - resto_00;

            if(fin == 10){
                fin = 1;
            } else if (fin == 11) {
                fin = 0;
            }
        }
        return fin;
    }

    // Método que verifica si los dígitos de control introducidos por el usuario concuerdan con los datos de cuenta
    public void verifdigits(){

        String verificacion;

        System.out.print("\tIntroduzca los 4 dígitos correspondientes al banco: ");
        String numBanco = scanner.nextLine();
        System.out.print("\tIntroduzca los 4 dígitos correspondientes a la sucursal: ");
        String numSucur = scanner.nextLine();
        System.out.print("\tIntroduzca los 2 dígitos de control: ");
        String dcont = scanner.nextLine();
        System.out.print("\tIntroduzca los 10 dígitos correspondientes al nº cuenta: ");
        String numCuenta = scanner.nextLine();
        System.out.println();

        numeros_dc1 = "00" + numBanco + numSucur;

        int dc1 = dc(numeros_dc1);
        int dc2 = dc(numCuenta);

        String dc1dc2 = dc1 + String.valueOf(dc2);

        if (dcont.equals(dc1dc2)){
            verificacion = "Correcta";
        }else {
            verificacion = "Incorrecta";
        }

        System.out.println("\tNúmero de cuenta a validar: " + numBanco + " " + numSucur + " " + dcont + " " + numCuenta);
        System.out.println("\tDígitos calculados: " +dc1dc2 + "\tVerificación: " + verificacion + "\n");

    }

}

class DigiIban {

    // Creacion del objeto Scanner
    Scanner scanner = new Scanner(System.in);

    // Método que pide al usuario los datos necesarios para crear el IBAN
    public void generaIBAN(){
        System.out.print("\tIntroduzca los 4 dígitos correspondientes al banco: ");
        String numBanco = scanner.nextLine();
        System.out.print("\tIntroduzca los 4 dígitos correspondientes a la sucursal: ");
        String numSucur = scanner.nextLine();
        System.out.print("\tIntroduzca los dígitos de control: ");
        String dcont = scanner.nextLine();
        System.out.print("\tIntroduzca los 10 dígitos correspondientes al nº cuenta: ");
        String numCuenta = scanner.nextLine();
        System.out.println();

        String IBAN = IBAN(numCuenta, numBanco, numSucur, dcont);

        System.out.println("\tES" + IBAN + " " + numBanco + " " + numSucur + " " + dcont + " " + numCuenta + "\n");
    }

    // Método que crea el IBAN
    public String IBAN(String numCuenta, String numBanco, String numSucur, String dcont){
        String IBAN = null;
        String todo = numBanco + numSucur + dcont + numCuenta + "142800";

        BigInteger calculo = new BigInteger(todo);
        BigInteger Result = calculo.mod(BigInteger.valueOf(97));
        int resultado = 98 - Result.intValue();

        if (resultado < 10 && resultado >= 0){
            IBAN = "0" + resultado;
        }else if (resultado > 9){
            IBAN = String.valueOf(resultado);
        }
        return IBAN;
    }

    // Método que verifica si el IBAN introducido por el usuario concuerda con los datos de cuenta
    public void verifIBAN(){
        String verificacion;

        System.out.print("\tIntroduzca los digitos IBAN: ");
        String numIBAN = scanner.nextLine();
        System.out.print("\tIntroduzca los 4 dígitos correspondientes al banco: ");
        String numBanco = scanner.nextLine();
        System.out.print("\tIntroduzca los 4 dígitos correspondientes a la sucursal: ");
        String numSucur = scanner.nextLine();
        System.out.print("\tIntroduzca los dígitos de control: ");
        String dcont = scanner.nextLine();
        System.out.print("\tIntroduzca los 10 dígitos correspondientes al nº cuenta: ");
        String numCuenta = scanner.nextLine();
        System.out.println();

        String IBAN = IBAN(numCuenta, numBanco, numSucur, dcont);

        if (numIBAN.equals(IBAN)){
            verificacion = "Correcta";
        }else {
            verificacion = "Incorrecta";
        }

        System.out.println("\tNúmero de cuenta a validar: " + "ES" + numIBAN + " " + numBanco + " " + numSucur + " " + dcont + " " + numCuenta + "\n");
        System.out.println("\tIBAN calculado: " + "ES" + IBAN + "\tVerificación: " + verificacion + "\n");

    }
}
class Menu{
    // Menú usado para la interfaz
    public void menu() {
        System.out.println("1) Generación de los Dígitos de Control");
        System.out.println("2) Validación de los Dígitos de Control");
        System.out.println("3) Generación de los dígitos IBAN");
        System.out.println("4) Verificar dígitos IBAN");
        System.out.println("5) Salir");
    }
}