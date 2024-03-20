package PEP2T_3_RCB;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;

public class Aeronave implements Propulsor{
    String cod_aparato;
    String fecha_entrega;
    String linea_montaje;
    Integer horas_previstas;
    Integer horas_empleadas;
    Integer capacidad_pasajeros;
    Double coste;
    Double precio_venta;
    Aeronave(String cod_aparato, String fecha_entrega, String linea_montaje, Integer horas_previstas, Integer horas_empleadas, Integer capacidad_pasajeros, Double coste, Double precio_venta){
        this.cod_aparato = cod_aparato;
        this.fecha_entrega = formadate(fecha_entrega);
        this.linea_montaje = linea_montaje;
        this.horas_previstas = horas_previstas;
        this.horas_empleadas = horas_empleadas;
        this.capacidad_pasajeros = capacidad_pasajeros;
        this.coste = coste;
        this.precio_venta = precio_venta;

    }
    Aeronave(){

    }
    public String fechaEntrega(){
        return fecha_entrega +" para " + capacidad_pasajeros + " pasajeros.";
    }
    public String getFechaEntrega() {
        return fecha_entrega;
    }
    public void horasBenef(){
        DecimalFormat formatomon= new DecimalFormat("###,###,###");
        String beneficio = formatomon.format(precio_venta-coste);
        System.out.println("\tEsto supuso " + horas_empleadas + " horas de trabajo generando un beneficio de " + beneficio + "€");
    }
    public void propul(){

    }
    public String formadate(String fecha){
        LocalDate localDateObject = LocalDate.parse(fecha, DateTimeFormatter.ofPattern("MM-dd-yyyy"));
        DateTimeFormatter dtformat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return localDateObject.format(dtformat);
    }
    public void totalHoras(Aeronave[] manolo){
        int sumamanolo=0;
        DecimalFormat formatomon= new DecimalFormat("###,###,###");
        for (int i = 0; i < manolo.length; i++) {

            if (manolo[i] instanceof Avioneta) {
                sumamanolo += manolo[i].horas_empleadas;
            }
        }
        String frase = "El total de horas de trabajo empleados en el tipo Avioneta fueron: " +formatomon.format(sumamanolo);
        System.out.println(frase);
    }
    public void facturacion(Aeronave[] manolo){
        int sumamanolo=0;
        DecimalFormat formatomon= new DecimalFormat("###,###,###");
        for (int i = 0; i < manolo.length; i++) {
            sumamanolo += manolo[i].precio_venta;
        }
        String frase = "La facturación total de la compañía a día de hoy: " +formatomon.format(sumamanolo)+" €";
        System.out.println(frase);
    }

    public static void main(String[] args) {
        Aeronave aeronave = new Aeronave();

        Dron dron = new Dron("100","10-02-2008", "L2GA",250,205,0,2700.0,5400.0);
        Dron dron2 = new Dron("101","10-04-2008", "L2GA",250,205,0,2700.0,5400.0);

        Avioneta avioneta = new Avioneta("102","10-03-2008","L3P4",300,289455,8, 5500000.0,8500000.0);
        Avioneta avioneta2 = new Avioneta("103","10-06-2008","L3P4",300,289455,8, 5500000.0,8500000.0);

        Jet jet = new Jet("104","05-22-2008", "L5T9", 11000, 10550, 6, 500000.0, 1250000.0);
        Jet jet2 = new Jet("105","12-22-2008", "L5T9", 11000, 10550, 6, 500000.0, 1250000.0);

        AvComMed avcommed = new AvComMed("106","05-22-2008", "L6Y3", 23000, 22785, 120, 2500000.0,5000000.0);
        AvComMed avcommed2 = new AvComMed("107", "11-22-2008", "L6Y3", 23000, 22785, 120, 2500000.0,5000000.0);

        Cohete cohete = new Cohete("108", "09-14-2022", "L9G1", 23000,22785, 0, 2500000.0, 5000000.0);
        Cohete cohete2 = new Cohete("109", "07-14-2022", "L9G1", 23000,22785, 0, 2500000.0, 5000000.0);

        AvComGrand avcomgran = new AvComGrand("110", "05-15-2008", "L2F5", 25000, 24785, 200, 3392850.0,6000000.0);
        AvComGrand avcomgran2 = new AvComGrand("111", "06-15-2008", "L2F5", 25000, 24785, 200, 3392850.0,6000000.0);

        Aeronave[] Arrayaereo = new Aeronave[12];

        Arrayaereo[0] = avcomgran;
        Arrayaereo[1] = avcomgran2;
        Arrayaereo[2] = avcommed;
        Arrayaereo[3] = avcommed2;
        Arrayaereo[4] = avioneta;
        Arrayaereo[5] = avioneta2;
        Arrayaereo[6] = cohete;
        Arrayaereo[7] = cohete2;
        Arrayaereo[8] = dron;
        Arrayaereo[9] = dron2;
        Arrayaereo[10] = jet;
        Arrayaereo[11] = jet2;

        Arrays.sort(Arrayaereo, Comparator.comparing (a -> LocalDate.parse(a.getFechaEntrega(), DateTimeFormatter.ofPattern("dd/MM/yyyy"))) );

        String frase = "Esta fábrica construyó su primera aeronave, ";
        if (Arrayaereo[0] instanceof Avioneta){
            frase += "una ";
        }else {
            frase += "un ";
        }
        frase += Arrayaereo[0].getClass().getSimpleName() + ", el " + Arrayaereo[0].fechaEntrega();

        System.out.println("\n\t\t\t\t\t\tPROGRAMA AERONAVES\n");

        System.out.println(frase);
        Arrayaereo[0].horasBenef();
        Arrayaereo[0].propul();
        System.out.print("\n");

        for (int i =1; i < Arrayaereo.length; i++){

            frase = "Después construyó ";
            if (Arrayaereo[i] instanceof Avioneta){
                frase += "una ";
            }
            else {
                frase += "un ";
            }
            frase += Arrayaereo[i].getClass().getSimpleName() +" el "+ Arrayaereo[i].fechaEntrega();
            System.out.println(frase);
            Arrayaereo[i].horasBenef();
            Arrayaereo[i].propul();
            System.out.println("\t");

        }

        dron.totalHoras(Arrayaereo);
        dron.facturacion(Arrayaereo);


    }
}
