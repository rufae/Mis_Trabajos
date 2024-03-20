package PEP2T_3_RCB;

public class AvComGrand extends Aeronave{
    AvComGrand(String cod_aparato, String fecha_entrega, String linea_montaje, Integer horas_previstas, Integer horas_empleadas, Integer capacidad_pasajeros, Double coste, Double precio_venta){
        super(cod_aparato, fecha_entrega, linea_montaje, horas_previstas, horas_empleadas, capacidad_pasajeros, coste, precio_venta);
    }
    public void propul(){
        System.out.println("\t\tEsta aeronave utiliza queroxeno.");
    }
}
