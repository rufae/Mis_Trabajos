package PEP3T_3_RCB;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ParserConfigurationException, IOException, TransformerException, SAXException {

        Scanner scanner = new Scanner ( System.in );
        Gestionpeliculas peliculas = new Gestionpeliculas();
        while ( true ) {
            System.out.println ( "\n\t\t\t\t\t\t\tPROGRAMA GESTIÓN PELÍCULAS" );
            System.out.println ( "\n\t\t\t\t1) Presentar el documento XML completo" );
            System.out.println ( "\t\t\t\t2) Añadir nuevo nodo al documento" );
            System.out.println ( "\t\t\t\t3) Modificar datos de un nodo del documento" );
            System.out.println ( "\t\t\t\t4) Eliminar un nodo concreto del documento" );
            System.out.println ( "\t\t\t\t5) Salir" );
            System.out.print("\n\t\t\t\tOpcion: ");

            int opcion = scanner.nextInt ( );

            System.out.print("\n");

            switch ( opcion ) {
                case 1 -> peliculas.DocumentoXML( );
                case 2 -> peliculas.mostrarMenuInsertar ( );
                case 3 -> peliculas.ModificarMenu( );
                case 4 -> peliculas.mostrarMenuEliminar ( );
                case 5 -> System.exit ( 0 );
                default -> System.out.println ( "\t\t\t\tOpción no válida" );
            }
        }
    }
}
