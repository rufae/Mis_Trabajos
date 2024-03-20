package PEP2T_4_RCB;

import java.util.Scanner;

public class PilotAtlas {
    static final String NEGRITA = "\033[1m";
    static final String SUBRAYO = "\033[4m";
    static final String FINAL = "\033[0m";
    public void menu(){
        System.out.println("1. Añadir una entrada al atlas\t\t2. Mostrar el contenido actual del atlas");
        System.out.println("3. Buscar una entrada del atlas\t\t4. Modificar una entrada del atlas");
        System.out.println("5. Ordenar por nombre de país\t\t6. Mostrar el contenido del atlas con iterador");
        System.out.println("7. Eliminar una entrada del atlas\t8. Eliminar el contenido completo del atlas");
        System.out.println("9. Salir");
    }

    public static void main(String[] args) {
        int opcion;

        PilotAtlas atlas = new PilotAtlas();
        Scanner scanner = new Scanner(System.in);
        Metodos_Hashmap metodosH = new Metodos_Hashmap();
        Metodos_Arraylist metodosA = new Metodos_Arraylist();

        System.out.println("\n\t\t\t\t\t" + NEGRITA + SUBRAYO + "PROGRAMA ATLAS" + FINAL);
        do {
            atlas.menu();
            System.out.print("\nOpción: ");
            opcion = scanner.nextInt();
            switch (opcion) {
                case 1 -> metodosA.entrada();
                case 2 -> metodosA.mostrar_contenido();
                case 3 -> metodosA.busqueda();
                case 4 -> metodosA.modificar();
                case 5 -> metodosA.ordenar();
                case 6 -> metodosA.mostrar_iterator();
                case 7 -> metodosA.eliminar_entrada();
                case 8 -> metodosA.eliminar_todo();
                case 9 -> System.out.println("Saliendo...");
                default -> System.out.println("Ingrese un número entre el 1 y el 9.");
            }
        } while (opcion != 9);
    }
}
