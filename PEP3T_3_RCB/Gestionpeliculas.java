package PEP3T_3_RCB;

import java.io.File;
import java.io.IOException;

import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;



public class Gestionpeliculas {
    private final String FILENAME="src/PEP3T_3_RCB/peliculas.xml";
    public void DocumentoXML() throws ParserConfigurationException, IOException, SAXException {

        File archivoXML = new File(FILENAME);
        // Crear arbol DOM y organizar el documento
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(archivoXML);
        // Depurar con normaliza
        doc.getDocumentElement().normalize();

        System.out.println("Elemento raíz: " + doc.getDocumentElement().getNodeName());

        NodeList nList = doc.getElementsByTagName("pelicula");
        System.out.println("-----------------------");
        for(int temp = 0 ; temp < nList.getLength() ; temp++){
            Node nNode = nList.item(temp);
            System.out.println("\nElemento actual: " + nNode.getNodeName());
            if(nNode.getNodeType() == Node.ELEMENT_NODE){
                Element eElement = (Element) nNode;

                System.out.println("Título: "+eElement.getElementsByTagName("titulo").item(0).getTextContent());
                System.out.println("Guionista: "+eElement.getElementsByTagName("guionista").item(0).getTextContent());
                System.out.println("Productora: "+eElement.getElementsByTagName("productora").item(0).getTextContent());
                System.out.println("Director: "+eElement.getElementsByTagName("director").item(0).getTextContent());
                System.out.println("Actor: "+eElement.getElementsByTagName("actor").item(0).getTextContent());
                System.out.println("Sinopsis: "+eElement.getElementsByTagName("sinopsis").item(0).getTextContent());

            }
        }
    }
    public void mostrarMenuInsertar() throws ParserConfigurationException, IOException, TransformerException, SAXException{

        Scanner scanner = new Scanner(System.in);

        System.out.println("-----------------------");
        System.out.println("\t\t\t\t1) Insertar nodo al principio del documento");
        System.out.println("\t\t\t\t2) Insertar nodo en posiciones intermedias del documento");
        System.out.println("\t\t\t\t3) Insertar nodo al final del documento");
        System.out.println("\t\t\t\t4) Volver al menú principal");
        System.out.print("\n\t\t\t\tOpcion: ");

        int opcion = scanner.nextInt();
        System.out.print("\n");

        switch(opcion){
            case 1:
                Principio();
                break;
            case 2:
                Medio();
                break;
            case 3:
                Final();
                break;
            case 4:
                break;
            default:
                System.out.println("Opción no válida");
                break;
        }
    }
    private void Principio() throws TransformerException, ParserConfigurationException, IOException, SAXException{
        Scanner scanner = new Scanner(System.in);
        File archivoXML = new File(FILENAME);

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc;
        if(archivoXML.exists()){
            doc = dBuilder.parse(archivoXML);
            doc.getDocumentElement().normalize();
        } else{
            // Si el archivo no existe, creamos un nuevo documento XML con el elemento raíz "peliculas"
            doc = dBuilder.newDocument();
            Element peliculas = doc.createElement("peliculas");
            doc.appendChild(peliculas);
        }

        System.out.println("Introduce los datos de la nueva película:");

        Element nuevaPelicula = doc.createElement("pelicula");

        Element titulo = doc.createElement("titulo");
        System.out.print("Título: ");
        titulo.appendChild(doc.createTextNode(scanner.nextLine()));
        nuevaPelicula.appendChild(titulo);

        Element guionista = doc.createElement("guionista");
        System.out.print("Guionista: ");
        guionista.appendChild(doc.createTextNode(scanner.nextLine()));
        nuevaPelicula.appendChild(guionista);

        Element productora = doc.createElement("productora");
        System.out.print("Productora: ");
        productora.appendChild(doc.createTextNode(scanner.nextLine()));
        nuevaPelicula.appendChild(productora);

        Element director = doc.createElement("director");
        System.out.print("Director: ");
        director.appendChild(doc.createTextNode(scanner.nextLine()));
        nuevaPelicula.appendChild(director);

        Element actor = doc.createElement("actor");
        System.out.print("Actor: ");
        actor.appendChild(doc.createTextNode(scanner.nextLine()));
        nuevaPelicula.appendChild(actor);

        Element sinopsis = doc.createElement("sinopsis");
        System.out.print("Sinopsis: ");
        sinopsis.appendChild(doc.createTextNode(scanner.nextLine()));
        nuevaPelicula.appendChild(sinopsis);

        Element e = doc.getDocumentElement();
        Node primerNodo = e.getFirstChild();
        doc.getDocumentElement().insertBefore(nuevaPelicula,primerNodo);

        TransformerFactory transformerFactory=TransformerFactory.newInstance();
        Transformer transformer=transformerFactory.newTransformer();
        DOMSource source=new DOMSource(doc);
        StreamResult result=new StreamResult(new File(FILENAME));
        transformer.transform(source,result);

        System.out.println("Película insertada correctamente al principio del documento.");
    }
    private void Medio() throws TransformerException, ParserConfigurationException, IOException, SAXException{
        Scanner scanner=new Scanner(System.in);
        File archivoXML=new File(FILENAME);
        DocumentBuilderFactory dbFactory=DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder=dbFactory.newDocumentBuilder();
        Document doc=dBuilder.parse(archivoXML);
        doc.getDocumentElement().normalize();

        // Pedir la posición en la que insertar la nueva película
        System.out.print("Posición de inserción (1-"+doc.getElementsByTagName("pelicula").getLength()+"): ");
        int posicion=Integer.parseInt(scanner.nextLine());

        if(posicion<1||posicion>doc.getElementsByTagName("pelicula").getLength()+1){
            throw new IllegalArgumentException("Posición no válida");
        }

        System.out.println("Introduce los datos de la nueva película:");

        Element nuevaPelicula=doc.createElement("pelicula");

        Element titulo=doc.createElement("titulo");
        System.out.print("Título: ");
        titulo.appendChild(doc.createTextNode(scanner.nextLine()));
        nuevaPelicula.appendChild(titulo);

        Element guionista=doc.createElement("guionista");
        System.out.print("Guionista: ");
        guionista.appendChild(doc.createTextNode(scanner.nextLine()));
        nuevaPelicula.appendChild(guionista);

        Element productora=doc.createElement("productora");
        System.out.print("Productora: ");
        productora.appendChild(doc.createTextNode(scanner.nextLine()));
        nuevaPelicula.appendChild(productora);

        Element director=doc.createElement("director");
        System.out.print("Director: ");
        director.appendChild(doc.createTextNode(scanner.nextLine()));
        nuevaPelicula.appendChild(director);

        Element actor=doc.createElement("actor");
        System.out.print("Actor: ");
        actor.appendChild(doc.createTextNode(scanner.nextLine()));
        nuevaPelicula.appendChild(actor);

        Element sinopsis=doc.createElement("sinopsis");
        System.out.print("Sinopsis: ");
        sinopsis.appendChild(doc.createTextNode(scanner.nextLine()));
        nuevaPelicula.appendChild(sinopsis);

        // Obtener el nodo de la película en la posición indicada
        Node nodoPosicion = doc.getElementsByTagName("pelicula").item(posicion-1);

        // Insertar la nueva película antes del nodo de la posición indicada
        doc.getDocumentElement().insertBefore(nuevaPelicula,nodoPosicion);

        // Guardar los cambios en el archivo XML
        TransformerFactory transformerFactory=TransformerFactory.newInstance();
        Transformer transformer=transformerFactory.newTransformer();
        DOMSource source=new DOMSource(doc);
        StreamResult result=new StreamResult(new File(FILENAME));
        transformer.transform(source,result);

        System.out.println("Película insertada correctamente en la posición "+posicion);
    }
    private void Final() throws TransformerException, ParserConfigurationException, IOException, SAXException{
        Scanner scanner=new Scanner(System.in);
        File archivoXML=new File(FILENAME);
        DocumentBuilderFactory dbFactory=DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder=dbFactory.newDocumentBuilder();
        Document doc=dBuilder.parse(archivoXML);
        doc.getDocumentElement().normalize();

        System.out.println("Introduce los datos de la nueva película:");

        Element nuevaPelicula=doc.createElement("pelicula");

        Element titulo=doc.createElement("titulo");
        System.out.print("Título: ");
        titulo.appendChild(doc.createTextNode(scanner.nextLine()));
        nuevaPelicula.appendChild(titulo);

        Element guionista=doc.createElement("guionista");
        System.out.print("Guionista: ");
        guionista.appendChild(doc.createTextNode(scanner.nextLine()));
        nuevaPelicula.appendChild(guionista);

        Element productora=doc.createElement("productora");
        System.out.print("Productora: ");
        productora.appendChild(doc.createTextNode(scanner.nextLine()));
        nuevaPelicula.appendChild(productora);

        Element director=doc.createElement("director");
        System.out.print("Director: ");
        director.appendChild(doc.createTextNode(scanner.nextLine()));
        nuevaPelicula.appendChild(director);

        Element actor=doc.createElement("actor");
        System.out.print("Actor: ");
        actor.appendChild(doc.createTextNode(scanner.nextLine()));
        nuevaPelicula.appendChild(actor);

        Element sinopsis=doc.createElement("sinopsis");
        System.out.print("Sinopsis: ");
        sinopsis.appendChild(doc.createTextNode(scanner.nextLine()));
        nuevaPelicula.appendChild(sinopsis);

        NodeList peliculas = doc.getElementsByTagName("pelicula");
        Node ultimoNodo = peliculas.item(peliculas.getLength()-1);
        Node siguienteNodo = ultimoNodo.getNextSibling();

        // Si el siguiente nodo no es null, insertamos el nuevo nodo antes de él
        if(siguienteNodo != null){
            doc.getDocumentElement().insertBefore(nuevaPelicula,siguienteNodo);
        } else{ // si el siguiente nodo es null, lo insertamos al final
            doc.getDocumentElement().appendChild(nuevaPelicula);
        }

        TransformerFactory transformerFactory=TransformerFactory.newInstance();
        Transformer transformer=transformerFactory.newTransformer();
        DOMSource source=new DOMSource(doc);
        StreamResult result=new StreamResult(new File(FILENAME));
        transformer.transform(source,result);

        System.out.println("Película insertada correctamente al final del archivo.");
    }
    public void ModificarMenu() throws IOException, ParserConfigurationException, TransformerException, SAXException{
        Scanner scanner=new Scanner(System.in);

        System.out.println("-----------------------");
        System.out.println("\t\t\t\t1) Modificar nodo al principio del documento");
        System.out.println("\t\t\t\t2) Modificar nodo en posiciones intermedias del documento");
        System.out.println("\t\t\t\t3) Modificar nodo al final del documento");
        System.out.println("\t\t\t\t4) Volver al menú principal");
        System.out.print("\n\t\t\t\tOpcion: ");

        int opcion=scanner.nextInt();

        System.out.print("\n");

        switch(opcion){
            case 1:
                ModificarNodoInicio();
                break;
            case 2:
                mostrarMenuModificarPosicionIntermedia();
                break;
            case 3:
                modificarNodoAlFinal();
                break;
            case 4:
                break;
            default:
                System.out.println("Opción no válida");
                break;
        }
    }
    private void ModificarNodoInicio() throws ParserConfigurationException, IOException, SAXException, TransformerException{
        // Obtener el archivo XML y crear el objeto Document
        File archivoXML = new File(FILENAME);
        Scanner sc = new Scanner(System.in);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document documento = dBuilder.parse(archivoXML);

        // Obtener la primera pelicula del documento
        NodeList listaDePeliculas = documento.getElementsByTagName("pelicula");
        // Obtener la primera pelicula del documento como un Element
        Element primeraPelicula = (Element) listaDePeliculas.item(0);

        // Obtener los nodos que deseas modificar
        Element titulo = (Element) primeraPelicula.getElementsByTagName("titulo").item(0);
        Element guionista = (Element) primeraPelicula.getElementsByTagName("guionista").item(0);
        Element productora = (Element) primeraPelicula.getElementsByTagName("productora").item(0);
        Element director = (Element) primeraPelicula.getElementsByTagName("director").item(0);
        Element actor = (Element) primeraPelicula.getElementsByTagName("actor").item(0);
        Element sinopsis = (Element) primeraPelicula.getElementsByTagName("sinopsis").item(0);
        boolean salirmenu = false;

        while (!salirmenu) {
            // Modificar los datos de los nodos
            System.out.println("-----------------------");
            System.out.println("\t\t\t\t1) Modificar título de la primera película");
            System.out.println("\t\t\t\t2) Modificar guionista de la primera película");
            System.out.println("\t\t\t\t3) Modificar productora de la primera película");
            System.out.println("\t\t\t\t4) Modificar director de la primera película");
            System.out.println("\t\t\t\t5) Modificar actor de la primera película");
            System.out.println("\t\t\t\t6) Modificar sinopsis de la primera película");
            System.out.println("\t\t\t\t7) Volver al menú principal");
            System.out.print("\n\t\t\t\tOpcion: ");

            String Eligirnodo =sc.nextLine();
            System.out.print("\n");

            // Modificar los datos de los nodos
            switch(Eligirnodo){
                case "1" -> {
                    System.out.print("Introduzca el nuevo titulo: ");
                    String nuevoTitulo=sc.nextLine();
                    titulo.setTextContent(nuevoTitulo);
                }
                case "2" -> {
                    System.out.print("Introduzca el nuevo guionista: ");
                    String nuevoGuionista=sc.nextLine();
                    guionista.setTextContent(nuevoGuionista);
                }
                case "3" -> {
                    System.out.print("Introduzca la nueva productora: ");
                    String nuevaProductora=sc.nextLine();
                    productora.setTextContent(nuevaProductora);
                }
                case "4" -> {
                    System.out.print("Introduzca el nuevo director: ");
                    String nuevoDirector=sc.nextLine();
                    director.setTextContent(nuevoDirector);
                }
                case "5" -> {
                    System.out.print("Introduzca el nuevo actor: ");
                    String nuevoActor=sc.nextLine();
                    actor.setTextContent(nuevoActor);
                }
                case "6" -> {
                    System.out.print("Introduzca la nueva sinopsis: ");
                    String nuevaSinopsis=sc.nextLine();
                    sinopsis.setTextContent(nuevaSinopsis);
                }
                case "7" -> salirmenu=true;
                default -> System.out.println("Introduce un número válido");
            }
        }
        // Guardar los cambios en el archivo XML
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(documento);
        StreamResult result = new StreamResult(new File(FILENAME));
        transformer.transform(source, result);
    }
    public void mostrarMenuModificarPosicionIntermedia() throws IOException, ParserConfigurationException, TransformerException, SAXException{
        Scanner scanner=new Scanner(System.in);
        System.out.println("-----------------------");
        System.out.println("\t\t\t\t1) Modificar título de una película");
        System.out.println("\t\t\t\t2) Modificar guionista de una película");
        System.out.println("\t\t\t\t3) Modificar productora de una película");
        System.out.println("\t\t\t\t4) Modificar director de una película");
        System.out.println("\t\t\t\t5) Modificar actor de una película");
        System.out.println("\t\t\t\t6) Modificar sinopsis de una película");
        System.out.println("\t\t\t\t7) Volver al menú principal");
        System.out.print("\n\t\t\t\tOpcion: ");

        int opcion=scanner.nextInt();
        System.out.print("\n");

        switch(opcion){
            case 1:
                modificarTitulo();
                break;
            case 2:
                modificarGuionista();
                break;
            case 3:
                modificarProductora();
                break;
            case 4:
                modificarDirector();
                break;
            case 5:
                modificarActor();
                break;
            case 6:
                modificarSinopsis();
                break;
            case 7:
                break;
            default:
                System.out.println("Opción no válida");
                break;
        }
    }
    private void modificarNodoAlFinal() throws ParserConfigurationException, IOException, SAXException, TransformerException{
        // Obtener el archivo XML y crear el objeto Document
        File archivoXML2 = new File(FILENAME);
        Scanner sc = new Scanner(System.in);
        DocumentBuilderFactory dbFactory2 = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder2 = dbFactory2.newDocumentBuilder();
        Document documento2 = dBuilder2.parse(archivoXML2);

        // Obtener la lista de peliculas del documento
        NodeList listaDePeliculas2 = documento2.getElementsByTagName("pelicula");

        // Obtener la última pelicula del documento
        Element ultimaPelicula = (Element) listaDePeliculas2.item(listaDePeliculas2.getLength() - 1);

        // Obtener los nodos que deseas modificar
        Element titulo2 = (Element) ultimaPelicula.getElementsByTagName("titulo").item(0);
        Element guionista2 = (Element) ultimaPelicula.getElementsByTagName("guionista").item(0);
        Element productora2 = (Element) ultimaPelicula.getElementsByTagName("productora").item(0);
        Element director2 = (Element) ultimaPelicula.getElementsByTagName("director").item(0);
        Element actor2 = (Element) ultimaPelicula.getElementsByTagName("actor").item(0);
        Element sinopsis2 = (Element) ultimaPelicula.getElementsByTagName("sinopsis").item(0);
        boolean salir2menu = false;
        while (!salir2menu) {
            System.out.println("-----------------------");
            System.out.println("\t\t\t\t1) Modificar título de la última película");
            System.out.println("\t\t\t\t2) Modificar guionista de la última película");
            System.out.println("\t\t\t\t3) Modificar productora de la última película");
            System.out.println("\t\t\t\t4) Modificar director de la última película");
            System.out.println("\t\t\t\t5) Modificar actor de la última película");
            System.out.println("\t\t\t\t6) Modificar sinopsis de la última película");
            System.out.println("\t\t\t\t7) Volver al menú principal");
            System.out.print("\n\t\t\t\tOpcion: ");

            String Eligirnodo =sc.nextLine();
            System.out.print("\n");
            switch(Eligirnodo){
                // Modificar los datos de los nodos
                case "1" -> {
                    System.out.print("Introduzca el nuevo titulo: ");
                    String nuevoTitulo2=sc.nextLine();
                    titulo2.setTextContent(nuevoTitulo2);
                }
                case "2" -> {
                    System.out.print("Introduzca el nuevo guionista: ");
                    String nuevoGuionista2=sc.nextLine();
                    guionista2.setTextContent(nuevoGuionista2);
                }
                case "3" -> {
                    System.out.print("Introduzca la nueva productora: ");
                    String nuevaProductora2=sc.nextLine();
                    productora2.setTextContent(nuevaProductora2);
                }
                case "4" -> {
                    System.out.print("Introduzca el nuevo director: ");
                    String nuevoDirector2=sc.nextLine();
                    director2.setTextContent(nuevoDirector2);
                }
                case "5" -> {
                    System.out.print("Introduzca el nuevo actor: ");
                    String nuevoActor2=sc.nextLine();
                    actor2.setTextContent(nuevoActor2);
                }
                case "6" -> {
                    System.out.print("Introduzca la nueva sinopsis: ");
                    String nuevaSinopsis2=sc.nextLine();
                    sinopsis2.setTextContent(nuevaSinopsis2);
                }
                case "7" -> salir2menu=true;
                default -> System.out.println("Introduce un numero valido");
            }
        }
        // Guardar los cambios en el archivo XML
        TransformerFactory transformerFactory2 = TransformerFactory.newInstance();
        Transformer transformer2 = transformerFactory2.newTransformer();
        DOMSource source2 = new DOMSource(documento2);
        StreamResult result2 = new StreamResult(new File(FILENAME));
        transformer2.transform(source2, result2);

    }
    private void modificarTitulo() throws TransformerException, IOException, SAXException, ParserConfigurationException{
        Scanner scanner=new Scanner(System.in);
        File archivoXML=new File(FILENAME);
        DocumentBuilderFactory dbFactory=DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder=dbFactory.newDocumentBuilder();
        Document doc=dBuilder.parse(archivoXML);
        doc.getDocumentElement().normalize();
        NodeList nList=doc.getElementsByTagName("pelicula");
        System.out.println("-----------------------");
        System.out.println("Introduce el título de la película a modificar: ");
        String tituloAntiguo=scanner.nextLine();
        for(int temp=0 ; temp<nList.getLength() ; temp++){
            Node nNode=nList.item(temp);

            if(nNode.getNodeType()==Node.ELEMENT_NODE){
                Element eElement=(Element) nNode;
                String titulo=eElement.getElementsByTagName("titulo").item(0).getTextContent();

                if(titulo.equals(tituloAntiguo)){
                    System.out.println("Introduce el nuevo título: ");
                    String nuevoTitulo=scanner.nextLine();
                    eElement.getElementsByTagName("titulo").item(0).setTextContent(nuevoTitulo);

                    TransformerFactory transformerFactory=TransformerFactory.newInstance();
                    Transformer transformer=transformerFactory.newTransformer();
                    DOMSource source=new DOMSource(doc);
                    StreamResult result=new StreamResult(new File(FILENAME));
                    transformer.transform(source,result);
                    System.out.println("Título modificado con éxito");
                    break;
                }
            }
        }
        System.out.println("-----------------------");
    }
    private void modificarGuionista() throws TransformerException, ParserConfigurationException, IOException, SAXException{
        Scanner scanner=new Scanner(System.in);
        File archivoXML=new File(FILENAME);
        DocumentBuilderFactory dbFactory=DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder=dbFactory.newDocumentBuilder();
        Document doc=dBuilder.parse(archivoXML);
        doc.getDocumentElement().normalize();
        NodeList nList=doc.getElementsByTagName("pelicula");
        System.out.println("-----------------------");
        System.out.println("Introduce el título de la película cuyo guionista quieres modificar: ");
        String tituloAntiguo=scanner.nextLine();

        for(int temp=0 ; temp<nList.getLength() ; temp++){
            Node nNode=nList.item(temp);

            if(nNode.getNodeType()==Node.ELEMENT_NODE){
                Element eElement=(Element) nNode;
                String titulo=eElement.getElementsByTagName("titulo").item(0).getTextContent();

                if(titulo.equals(tituloAntiguo)){
                    System.out.println("Introduce el nuevo guionista: ");
                    String nuevoTitulo=scanner.nextLine();
                    eElement.getElementsByTagName("guionista").item(0).setTextContent(nuevoTitulo);

                    TransformerFactory transformerFactory=TransformerFactory.newInstance();
                    Transformer transformer=transformerFactory.newTransformer();
                    DOMSource source=new DOMSource(doc);
                    StreamResult result=new StreamResult(new File(FILENAME));
                    transformer.transform(source,result);
                    System.out.println("Guionista modificado con éxito");
                    break;
                }
            }
        }
        System.out.println("-----------------------");
    }
    private void modificarProductora() throws TransformerException, IOException, SAXException, ParserConfigurationException{
        Scanner scanner=new Scanner(System.in);
        File archivoXML=new File(FILENAME);
        DocumentBuilderFactory dbFactory=DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder=dbFactory.newDocumentBuilder();
        Document doc=dBuilder.parse(archivoXML);
        doc.getDocumentElement().normalize();
        NodeList nList=doc.getElementsByTagName("pelicula");
        System.out.println("-----------------------");
        System.out.println("Introduce el título de la película cuya productora quieres modificar: ");
        String tituloAntiguo=scanner.nextLine();

        for(int temp=0 ; temp<nList.getLength() ; temp++){
            Node nNode=nList.item(temp);

            if(nNode.getNodeType()==Node.ELEMENT_NODE){
                Element eElement=(Element) nNode;
                String titulo=eElement.getElementsByTagName("titulo").item(0).getTextContent();

                if(titulo.equals(tituloAntiguo)){
                    System.out.println("Introduce la nueva productora: ");
                    String nuevoTitulo=scanner.nextLine();
                    eElement.getElementsByTagName("productora").item(0).setTextContent(nuevoTitulo);

                    TransformerFactory transformerFactory=TransformerFactory.newInstance();
                    Transformer transformer=transformerFactory.newTransformer();
                    DOMSource source=new DOMSource(doc);
                    StreamResult result=new StreamResult(new File(FILENAME));
                    transformer.transform(source,result);
                    System.out.println("Productora modificado con éxito");
                    break;
                }
            }
        }
        System.out.println("-----------------------");
    }
    private void modificarDirector() throws TransformerException, ParserConfigurationException, IOException, SAXException{
        Scanner scanner=new Scanner(System.in);
        File archivoXML=new File(FILENAME);
        DocumentBuilderFactory dbFactory=DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder=dbFactory.newDocumentBuilder();
        Document doc=dBuilder.parse(archivoXML);
        doc.getDocumentElement().normalize();
        NodeList nList=doc.getElementsByTagName("pelicula");
        System.out.println("-----------------------");
        System.out.println("Introduce el título de la película cuyo director quieres modificar: ");
        String tituloAntiguo=scanner.nextLine();

        for(int temp=0 ; temp<nList.getLength() ; temp++){
            Node nNode=nList.item(temp);

            if(nNode.getNodeType()==Node.ELEMENT_NODE){
                Element eElement=(Element) nNode;
                String titulo=eElement.getElementsByTagName("titulo").item(0).getTextContent();

                if(titulo.equals(tituloAntiguo)){
                    System.out.println("Introduce el nuevo director: ");
                    String nuevoTitulo=scanner.nextLine();
                    eElement.getElementsByTagName("director").item(0).setTextContent(nuevoTitulo);

                    TransformerFactory transformerFactory=TransformerFactory.newInstance();
                    Transformer transformer=transformerFactory.newTransformer();
                    DOMSource source=new DOMSource(doc);
                    StreamResult result=new StreamResult(new File(FILENAME));
                    transformer.transform(source,result);
                    System.out.println("Director modificado con éxito");
                    break;
                }
            }
        }
        System.out.println("-----------------------");
    }
    private void modificarActor() throws TransformerException, IOException, SAXException, ParserConfigurationException{
        Scanner scanner=new Scanner(System.in);
        File archivoXML=new File(FILENAME);
        DocumentBuilderFactory dbFactory=DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder=dbFactory.newDocumentBuilder();
        Document doc=dBuilder.parse(archivoXML);
        doc.getDocumentElement().normalize();
        NodeList nList=doc.getElementsByTagName("pelicula");
        System.out.println("-----------------------");
        System.out.println("Introduce el título de la película cuyo actor quieres modificar: ");
        String tituloAntiguo=scanner.nextLine();

        for(int temp=0 ; temp<nList.getLength() ; temp++){
            Node nNode=nList.item(temp);

            if(nNode.getNodeType()==Node.ELEMENT_NODE){
                Element eElement=(Element) nNode;
                String titulo=eElement.getElementsByTagName("titulo").item(0).getTextContent();

                if(titulo.equals(tituloAntiguo)){
                    System.out.println("Introduce el nuevo actor: ");
                    String nuevoTitulo=scanner.nextLine();
                    eElement.getElementsByTagName("actor").item(0).setTextContent(nuevoTitulo);

                    TransformerFactory transformerFactory=TransformerFactory.newInstance();
                    Transformer transformer=transformerFactory.newTransformer();
                    DOMSource source=new DOMSource(doc);
                    StreamResult result=new StreamResult(new File(FILENAME));
                    transformer.transform(source,result);
                    System.out.println("Actor modificado con éxito");
                    break;
                }
            }
        }
        System.out.println("-----------------------");
    }
    private void modificarSinopsis() throws TransformerException, IOException, SAXException, ParserConfigurationException{
        Scanner scanner=new Scanner(System.in);
        File archivoXML=new File(FILENAME);
        DocumentBuilderFactory dbFactory=DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder=dbFactory.newDocumentBuilder();
        Document doc=dBuilder.parse(archivoXML);
        doc.getDocumentElement().normalize();
        NodeList nList=doc.getElementsByTagName("pelicula");
        System.out.println("-----------------------");
        System.out.println("Introduce el título de la película cuya sinopsis quieres modificar: ");
        String tituloAntiguo=scanner.nextLine();
        for(int temp=0 ; temp<nList.getLength() ; temp++){
            Node nNode=nList.item(temp);

            if(nNode.getNodeType()==Node.ELEMENT_NODE){
                Element eElement=(Element) nNode;
                String titulo=eElement.getElementsByTagName("titulo").item(0).getTextContent();

                if(titulo.equals(tituloAntiguo)){
                    System.out.println("Introduce la nueva sinopsis: ");
                    String nuevoTitulo=scanner.nextLine();
                    eElement.getElementsByTagName("sinopsis").item(0).setTextContent(nuevoTitulo);

                    TransformerFactory transformerFactory=TransformerFactory.newInstance();
                    Transformer transformer=transformerFactory.newTransformer();
                    DOMSource source=new DOMSource(doc);
                    StreamResult result=new StreamResult(new File(FILENAME));
                    transformer.transform(source,result);
                    System.out.println("Sinopsis modificado con éxito");
                    break;
                }
            }
        }
        System.out.println("-----------------------");
    }
    public void mostrarMenuEliminar() throws TransformerException, ParserConfigurationException, IOException, SAXException{
        Scanner scanner=new Scanner(System.in);

        System.out.println("-----------------------");
        System.out.println("\t\t\t\t1) Eliminar nodo al principio del documento");
        System.out.println("\t\t\t\t2) Eliminar nodo en posiciones intermedias del documento");
        System.out.println("\t\t\t\t3) Eliminar nodo al final del documento");
        System.out.println("\t\t\t\t4) Volver al menú principal");
        System.out.print("\n\t\t\t\tOpcion: ");

        int opcion=scanner.nextInt();

        System.out.print("\n");

        switch(opcion){
            case 1:
                eliminarNodoAlPrincipio();
                break;
            case 2:
                eliminarNodoEnPosicionIntermedia();
                break;
            case 3:
                eliminarNodoAlFinal();
                break;
            case 4:
                break;
            default:
                System.out.println("Opción no válida");
                break;
        }
    }
    public void eliminarNodoAlPrincipio() throws ParserConfigurationException, IOException, SAXException, TransformerException{
        File inputFile=new File(FILENAME);
        DocumentBuilderFactory dbFactory=DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder=dbFactory.newDocumentBuilder();
        Document doc=dBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();
        NodeList nList=doc.getElementsByTagName("pelicula");
        Node firstNode=nList.item(0);
        firstNode.getParentNode().removeChild(firstNode);
        TransformerFactory transformerFactory=TransformerFactory.newInstance();
        Transformer transformer=transformerFactory.newTransformer();
        DOMSource source=new DOMSource(doc);
        StreamResult result=new StreamResult(inputFile);
        transformer.transform(source,result);
        System.out.println("Nodo eliminado del principio del documento.");
    }
    public void eliminarNodoAlFinal() throws ParserConfigurationException, IOException, SAXException, TransformerException{
        File inputFile=new File(FILENAME);
        DocumentBuilderFactory dbFactory=DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder=dbFactory.newDocumentBuilder();
        Document doc=dBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();
        NodeList nList=doc.getElementsByTagName("pelicula");
        Node lastNode=nList.item(nList.getLength()-1);
        lastNode.getParentNode().removeChild(lastNode);
        TransformerFactory transformerFactory=TransformerFactory.newInstance();
        Transformer transformer=transformerFactory.newTransformer();
        DOMSource source=new DOMSource(doc);
        StreamResult result=new StreamResult(inputFile);
        transformer.transform(source,result);
        System.out.println("Nodo eliminado del final del documento.");
    }
    public void eliminarNodoEnPosicionIntermedia() throws TransformerException, ParserConfigurationException, IOException, SAXException{
        File inputFile=new File(FILENAME);
        DocumentBuilderFactory dbFactory=DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder=dbFactory.newDocumentBuilder();
        Document doc=dBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();
        NodeList nList=doc.getElementsByTagName("pelicula");
        Scanner scanner=new Scanner(System.in);
        System.out.println("Introduce el título de la película que deseas eliminar:");
        String titulo=scanner.nextLine();
        boolean encontrado=false;
        for(int i=0 ; i<nList.getLength() ; i++){
            Node nNode=nList.item(i);
            if(nNode.getNodeType()==Node.ELEMENT_NODE){
                Element element=(Element) nNode;
                String tituloActual=element.getElementsByTagName("titulo").item(0).getTextContent();
                if(tituloActual.equalsIgnoreCase(titulo)){
                    encontrado=true;
                    nNode.getParentNode().removeChild(nNode);
                    TransformerFactory transformerFactory=TransformerFactory.newInstance();
                    Transformer transformer=transformerFactory.newTransformer();
                    DOMSource source=new DOMSource(doc);
                    StreamResult result=new StreamResult(inputFile);
                    transformer.transform(source,result);
                    System.out.println("Nodo eliminado en posición intermedia del documento.");
                    break;
                }
            }
        }
        if(!encontrado){
            System.out.println("No se encontró el título de la película en el documento.");
        }
    }
}
