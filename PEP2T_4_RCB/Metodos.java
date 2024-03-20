package PEP2T_4_RCB;

import java.util.*;

class Metodos_Hashmap {
    Scanner scanner = new Scanner(System.in);
    HashMap<String, String> atlas = new HashMap<String, String>();

    public void entrada(){
        System.out.print("\nTeclea un País: ");
        String pais = scanner.next();
        System.out.print("Teclea su Capital: ");
        String capital = scanner.next();

        atlas.put(pais, capital);

        System.out.println("\nNueva entrada incorporada\n");
    }
    public void tamano(){
        Integer tamano = atlas.size();
        if (tamano == 1){
            System.out.println();
            System.out.println("Hay " + tamano + " elemento.\n");
        }else if (tamano != 1 && tamano != 0){
            System.out.println();
            System.out.println("Hay " + tamano + " elementos.\n");
        } else {
            System.out.println("Hay " + tamano + " elementos.\n");
        }
    }
    public void mostrar_contenido(){
        System.out.println();

        for (Map.Entry<String, String> entry : atlas.entrySet()) {
            String clave = entry.getKey();
            String valor = entry.getValue();
            System.out.println("País: " + clave + "\tCapital: " + valor);
        }
        tamano();

    }
    public void busqueda(){
        System.out.print("\nTeclea un pais: ");
        String pais = scanner.next();

        if (atlas.containsKey(pais)){
            System.out.println("Capital: " + atlas.get(pais) + "\n");
        }else {
            System.out.println("\nDe este país no se encuentra capital\n");
        }
    }
    public void modificar(){
        System.out.print("\nTeclea un País: ");
        String pais = scanner.next();
        System.out.print(" Teclea modificación de capital: ");
        String capital = scanner.next();
        System.out.println();
        atlas.replace(pais, capital);
    }
    public void ordenar(){
        System.out.println();
        TreeMap<String, String> treeMap = new TreeMap<>(atlas);

        for (Map.Entry<String, String> entry : treeMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println();

    }
    public void mostrar_iterator(){
        System.out.println();
        Iterator<Map.Entry<String, String>> iterator = atlas.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            String clave = entry.getKey();
            String valor = entry.getValue();
            System.out.println("Pais: " + clave + "\tCapital: " + valor);
        }

        tamano();
    }
    public void eliminar_entrada(){
        System.out.print("\nTeclea un País a eliminar: ");
        String pais = scanner.next();
        atlas.remove(pais);
    }
    public void eliminar_todo(){
        atlas.clear();
    }

}
class Metodos_Arraylist{
    Scanner scanner = new Scanner(System.in);
    ArrayList<String[]> atlas = new ArrayList<>();
    public void entrada(){
        System.out.print("\nTeclea un País: ");
        String pais = scanner.next();
        System.out.print("Teclee su Capital: ");
        String capital = scanner.next();

        String[] entrada = {pais, capital};
        atlas.add(entrada);

        System.out.println("Nueva entrada incorporada");
    }
    public void tamano() {
        int tamano = atlas.size();
        if (tamano == 1) {
            System.out.println("Hay " + tamano + " elemento.\n");
        } else {
            System.out.println("Hay " + tamano + " elementos.\n");
        }
    }
    public void mostrar_contenido(){
        System.out.println();
        for (String[] entrada : atlas) {
            System.out.println("País: " + entrada[0] + "\tCapital: " + entrada[1]);
        }
        tamano();
    }
    public void busqueda(){
        System.out.print("\nTeclea un país: ");
        String pais = scanner.next();

        boolean encontrado = false;
        for (String[] entrada : atlas) {
            if (entrada[0].equals(pais)) {
                System.out.println("Capital: " + entrada[1] + "\n");
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("\nDe este país no se encuentra capital\n");
        }
    }
    public void modificar(){
        System.out.print("\nTeclea un País: ");
        String pais = scanner.next();
        System.out.print("Teclea modificación de capital: ");
        String capital = scanner.next();
        System.out.println();

        for (String[] entrada : atlas) {
            if (entrada[0].equals(pais)) {
                entrada[1] = capital;
                break;
            }
        }
    }
    public void ordenar(){
        System.out.println();
        TreeMap<String, String> treeMap = new TreeMap<>();
        for (String[] entry : atlas) {
            treeMap.put(entry[0], entry[1]);
        }

        for (String pais : treeMap.keySet()) {
            System.out.println(pais + ": " + treeMap.get(pais));
        }
        System.out.println();
    }
    public void mostrar_iterator(){
        System.out.println();
        Iterator<String[]> iterator = atlas.iterator();

        while (iterator.hasNext()) {
            String[] entry = iterator.next();
            String pais = entry[0];
            String capital = entry[1];
            System.out.println("País: " + pais + "\tCapital: " + capital);
        }
        tamano();
    }
    public void eliminar_entrada(){
        System.out.print("\nTeclea un País a eliminar: ");
        String pais = scanner.next();

        for (int i = 0; i < atlas.size(); i++) {
            if (atlas.get(i)[0].equals(pais)) {
                atlas.remove(i);
                break;
            }
        }
        System.out.println("País eliminado del atlas.");
    }
    public void eliminar_todo(){
        atlas.clear();
        System.out.println("Atlas borrado.");
    }
}
