import java.io.*;
import java.util.Scanner;

public class CambiaVocales {
    String NEGRITA = "\033[1m";
    String FINAL = "\033[0m";
    Scanner scanner = new Scanner(System.in);
    public void escribir_fichero(){

        int numeroLineas = 1;
        int numeroCaracteres = 80;

        PrintWriter pw = null;

        try {
            pw = new PrintWriter("src/rafa.txt");

            System.out.println("Introduce un texto, pulsa Enter al acabar e introduce entonces la cadena FIN:");
            String texto = scanner.nextLine();

            while (!texto.equalsIgnoreCase("FIN") && numeroLineas < 7) {
                if (texto.length() > numeroCaracteres) {
                    texto = texto.substring(0, numeroCaracteres);
                    System.out.println("Se ha sobrepasado el límite, se ha limitado a 80 caracteres");
                }
                pw.println(texto);
                numeroLineas++;
                texto = scanner.nextLine();

            }

            pw.flush();
        } catch (FileNotFoundException fnfe){
            System.out.println(fnfe.getMessage());
        } finally {
            pw.close();
        }

    }
    public void leer_archivo() {
        FileReader leer = null;

        try{
            leer = new FileReader("src/rafa.txt");
            BufferedReader entrada = new BufferedReader(leer);
            String cadena = entrada.readLine();

            System.out.println(NEGRITA + "\n\t\t\too00 Las líneas se han grabado en el fichero 00oo" + FINAL);
            System.out.println(NEGRITA + "\nEl texto almacenado, una vez procesado:" + FINAL);

            while (cadena != null){
                String cadena2 = "";
                for (int i=0; i < cadena.length(); i++){
                    char caracter = cadena.charAt(i);

                    if (caracter == 'a'){
                        cadena2 += 'i';
                    } else if (caracter == 'e') {
                        cadena2 += 'o';
                    } else if (caracter == 'A') {
                        cadena2 += 'I';
                    } else if (caracter == 'E') {
                        cadena2 += 'O';
                    } else {
                        cadena2 += caracter;
                    }
                }
                System.out.println("\tEn el fichero --> " + cadena + " y en pantalla --> " + cadena2);
                cadena = entrada.readLine();
            }
            System.out.println(NEGRITA + "\n\t\t\t\too00 Se acabó el programa 00oo" + FINAL);
        }catch (IOException ie){
            System.out.println(ie.getMessage());
        }finally {
            try {
                if (leer != null) {
                    leer.close();
                }
            } catch (IOException ioe) {
                System.out.println(ioe.getMessage());
            }
        }

    }
}
