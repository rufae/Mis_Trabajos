public class CompañiaTelef {
    public static void main(String[] args){
        Metodos metodos = new Metodos();
        System.out.println("\n\t\t\t\tPROGRAMA GESTIÓN COMPAÑÍA TELEFÓNICA");

        while (true) {
            metodos.menu();
            String numero = metodos.teclado.nextLine();
            int opcion;

            try {
                opcion = Integer.parseInt(numero);
            } catch (NumberFormatException excepcion) {
                System.out.println("\n\t\t\t\t\t\t\t\tOpción no válida");
                continue;
            }

            switch (opcion) {
                case 1 -> {metodos.altaFactura(); metodos.teclado.nextLine();}
                case 2 -> {metodos.modificar(); metodos.teclado.nextLine();}
                case 3-> {String valor = metodos.consultaAbonado();
                    if(valor != null) {
                        System.out.println("\n\t\t\t\tValor de la factura: " + valor);
                    }
                    metodos.teclado.nextLine();
                }
                case 4-> {String total = metodos.consultaTotal();
                    if(total != null) {
                        System.out.println("\t\t\t\tFacturación total: " + total);
                    }
                }
                case 5 -> metodos.Eliminar();
                case 6-> {metodos.teclado.close(); System.exit(0);}
                default-> System.out.println("\n\t\t\t\t\t\t\t\tOpción no válida");
            }
        }
    }
}