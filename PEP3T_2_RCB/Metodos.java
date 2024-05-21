import java.io.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;


public class Metodos {

    protected final Scanner teclado = new Scanner(System.in);
    private final DecimalFormat moneda = new DecimalFormat("###.##€", new DecimalFormatSymbols(Locale.US));
    private final File fichero = new File("src/facturas_telf.dat");

    protected void menu(){
        System.out.println("\n\n\t\t\t\tMenú de Opciones");
        System.out.println("\t\t\t\t=================");
        System.out.println("\n\t\t\t\t1) Alta de nuevas facturas");
        System.out.println("\t\t\t\t2) Modificación del valor de factura");
        System.out.println("\t\t\t\t3) Consulta del dato de facturación de un abonado");
        System.out.println("\t\t\t\t4) Consulta del dato de facturación total de la compañía");
        System.out.println("\t\t\t\t5) Eliminar el fichero");
        System.out.print("\n\t\t\t\t\t\t\t Opción:");
    }

    protected void altaFactura() {
        try {
            FileOutputStream raf = new FileOutputStream(fichero, true);
            DataOutputStream pep = new DataOutputStream(raf);
            boolean encon = true;
            boolean correcto = false;
            int numero = 0;
            float valor = 0;
            System.out.println("\n\t\t\t\tAlta de factura\n");
            while (encon){
                numero = inputInt();
                teclado.nextLine();
                FileInputStream fis = new FileInputStream(fichero);
                DataInputStream dis = new DataInputStream(fis);
                encon = false;

                while (dis.available() > 0) {
                    int numRep = dis.readInt();
                    String nombreRep = dis.readUTF();
                    float valorRep = dis.readFloat();

                    if (numRep == numero){
                        encon = true;
                        System.out.println("\t\t\t\t\tIngrese otro número, este ya existe");
                        break;
                    }
                }
                dis.close();
                fis.close();
            }
            System.out.print("\t\t\t\tNombre: ");
            String nombre = teclado.nextLine();
            while (!correcto) {
                try {
                    System.out.print("\t\t\t\tValor de la factura: ");
                    teclado.useLocale(Locale.US);
                    valor = teclado.nextFloat();
                    correcto = true;
                } catch (InputMismatchException e) {
                    System.out.println("\t\t\t\t\tValor de factura no válido, ingrese otro");
                    teclado.nextLine();
                }
            }
            pep.writeInt(numero);
            pep.writeUTF(nombre);
            pep.writeFloat(valor);
            System.out.println("\n\t\t\t\tDatos incorporados al fichero");
            pep.close();
            raf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void modificar() {
        int i = 0;
        float nuevoValor = 0;
        boolean encon = false;
        boolean correcto = false;
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(fichero, "rw");
            System.out.println("\n\t\t\t\tModificación del valor de factura\n");
            i = inputInt();

            while (raf.getFilePointer() < raf.length()) {
                int numeroAbonado = raf.readInt();
                String nombre = raf.readUTF();
                float valor = raf.readFloat();

                if (numeroAbonado == i) {
                    encon = true;
                    System.out.println("\n\t\t\t\tValor de la factura: " + moneda.format(valor));
                    while (!correcto) {
                        try {
                            System.out.print("\n\t\t\t\tNuevo valor factura: ");
                            teclado.useLocale(Locale.US);
                            nuevoValor = teclado.nextFloat();
                            correcto = true;
                        } catch (InputMismatchException e) {
                            System.out.print("\t\t\t\tNuevo valor de factura no válido, ingrese otro");
                            teclado.nextLine();
                        }
                    }
                    raf.seek(raf.getFilePointer() - 4);
                    raf.writeFloat(nuevoValor);
                    System.out.println("\n\t\t\t\tDato modificado en el fichero");
                    break;
                }
            }
            if (!encon) {
                System.out.println("\n\t\t\t\tAbonado no registrado");
            }
        } catch (IOException ioex) {
            System.out.println(ioex.getMessage());
        } finally {
            try {
                if (raf != null) {
                    raf.close();
                }
            } catch (IOException ioe) {
                System.out.println(ioe.getMessage());
            }
        }
    }

    protected String consultaAbonado() {
        int i = 0;
        String nombre;
        float valor;
        int numeroAbonado;
        FileInputStream fis = null;
        DataInputStream dis = null;

        System.out.println("\n\t\t\t\tConsulta facturación abonado\n");
        i = inputInt();
        try {
            fis = new FileInputStream(fichero);
            dis = new DataInputStream(fis);
            while (dis.available() > 0) {
                numeroAbonado = dis.readInt();
                nombre = dis.readUTF();
                valor = dis.readFloat();

                if (numeroAbonado == i){
                    return moneda.format(valor);
                }
            }
            System.out.println("\n\t\t\t\tAbonado no registrado");
        } catch (IOException ioex) {
            System.out.println(ioex.getMessage());
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                if (dis != null) {
                    dis.close();
                }
            } catch (IOException ioe) {
                System.out.println(ioe.getMessage());
            }
        }
        return null;
    }

    protected String consultaTotal() {
        int num;
        String nombre;
        float valor;
        float total = 0;
        FileInputStream fis = null;
        DataInputStream dis = null;
        try {
            fis = new FileInputStream(fichero);
            dis = new DataInputStream(fis);
            while (dis.available() > 0) {
                num = dis.readInt();
                nombre = dis.readUTF();
                valor = dis.readFloat();
                total += valor;
            }
            System.out.println("\n\t\t\t\tConsulta facturación total\n");
            return moneda.format(total);
        } catch (IOException ioex) {
            System.out.println(ioex.getMessage());
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                if (dis != null) {
                    dis.close();
                }
            } catch (IOException ioe) {
                System.out.println(ioe.getMessage());
            }
        }
        return null;
    }

    protected void Eliminar() {
        System.out.println("\n\t\t\t\tEliminar fichero");

        if (fichero.exists()){
            fichero.delete();
            System.out.println("\n\t\t\t\tFichero eliminado.");
        }else{
            System.out.println("\n\t\t\t\tNo existe el fichero");
        }
    }

    private int inputInt (){
        boolean correcto = false;
        int numero = 0;
        while (!correcto) {
            try {
                System.out.print("\t\t\t\tNúmero del abonado: ");
                numero = teclado.nextInt();
                correcto = true;
            } catch (InputMismatchException e) {
                System.out.println("\t\t\t\tNúmero de abonado no válido, ingrese otro");
                teclado.nextLine();
            }
        }
        return numero;
    }



}
