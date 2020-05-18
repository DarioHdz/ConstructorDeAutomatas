package sample;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.regex.Pattern;

public class VentanaProceso implements Initializable {

    // Elementos de interfaz y alertas
    @FXML TextArea txtSalida;
    @FXML Button btnEstado,btnRegresar;

    // Elementos de archivos
    File archivoA = null;
    File archivoB = null;
    Scanner leerArchivo = null;

    // Variables auxiliares de metodos
    String auxCadena = null;
    int numAutomata = 0;
    int auxLinea = 0;
    int numNodos = 0;
    String auxNombre;
    boolean[] validaciones = new boolean[5];
    boolean Valida = true;
    public static String[] nombres = new String[5];
    public static int auxPosicion = 0;
    int NodoInicial = 0;
    int NodoFinal;
    int NodoActual;

    // Elementos de las matrices soportadas
    // ===== BUSCAR OPTIMIZAR ===== //
    static int nFinal1;
    static int nFinal2;
    static int nFinal3;
    static int nFinal4;
    static int nFinal5;
    public static Objeto[][] matriz1 = null;
    public static Objeto[][] matriz2 = null;
    public static Objeto[][] matriz3 = null;
    public static Objeto[][] matriz4;
    public static Objeto[][] matriz5;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // El area de texto no es editable
        txtSalida.setEditable(false);

        // Recupera los archivos abiertos
        // Preferible usar get y set REVISAR
        archivoA = Controller.archivoA;
        archivoB = Controller.archivoB;

        // Pone fondo rojo porque no se ha cargado
        // ===== NO ES INDISPENSABLE, REPENSAR,TAL VEZ BARRA DE PROGRESO? =====
        btnEstado.setBackground(new Background(new BackgroundFill(Color.web("#cb3234"), CornerRadii.EMPTY, Insets.EMPTY)));

        // Acciones de los botones
        btnRegresar.setOnAction(event -> {
            Stage ventanaActual = (Stage) btnEstado.getScene().getWindow();
            ventanaActual.close();
        });

        // Al inicializar la ventana se elije el archivo que se va a procesar
        if(Controller.indicador == 0){

            // Parte para procesar automatas
            try {
                leerArchivo = new Scanner(archivoA);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            numAutomata++;
            crearAutomata(numAutomata);

        }else {

            // Parte para procesar ejemplos
            try {
                leerArchivo = new Scanner(archivoB);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            ProcesoEjemplos();
        }

        // Pone fondo verde porque se ha cargado
        // ===== NO ES INDISPENSABLE, REPENSAR,TAL VEZ BARRA DE PROGRESO? =====
        btnEstado.setBackground(new Background(new BackgroundFill(Color.web("#008000"), CornerRadii.EMPTY, Insets.EMPTY)));

    }

    // ===== METODOS PARA AUTOMATAS ===== //
    @SuppressWarnings("All")

    // Permite crear hasta 5 automatas, cada un con sus caracteristicas y matriz //
    private void crearAutomata(int numAutomata) {
        switch (numAutomata){

            case 1 :
                auxCadena = leerArchivo.nextLine();
                auxLinea++;
                auxCadena = auxCadena.replaceAll("[,]","");
                auxNombre = auxCadena.substring(7);
                txtSalida.insertText(txtSalida.getLength(),"==== NUEVO AUTOMATA ENCONTRADO ====" + "\nNombre: " + auxNombre + "\nNumero: " + numAutomata);
                System.out.println("Nombre: " + auxNombre);
                System.out.println("Numero: " + numAutomata);

                Controller.numAutomata = numAutomata;
                Controller.lbl1 = auxNombre;
                nombres[numAutomata] = auxNombre;

                auxCadena = leerArchivo.nextLine();
                auxLinea++;
                numNodos = leerNumero(auxCadena,0);
                txtSalida.insertText(txtSalida.getLength(),"\nNumero de nodos: " + numNodos);

                matriz1 = new Objeto[numNodos][numNodos];
                llenarMatriz(matriz1);
                leerNodos(leerArchivo,1,auxLinea);

                nFinal1 = numNodos - 1;

                break;

            case 2 :
                auxCadena = auxCadena.replaceAll("[,]","");
                auxNombre = auxCadena.substring(7);
                txtSalida.insertText(txtSalida.getLength(),"\n\n==== NUEVO AUTOMATA ENCONTRADO ====" + "\nNombre: " + auxNombre + "\nNumero: " + numAutomata);
                System.out.println("Nombre: " + auxNombre);
                System.out.println("Numero: " + numAutomata);

                Controller.numAutomata = numAutomata;
                Controller.lbl2 = auxNombre;
                nombres[numAutomata] = auxNombre;

                auxCadena = leerArchivo.nextLine();
                auxLinea++;
                numNodos = leerNumero(auxCadena,0);
                txtSalida.insertText(txtSalida.getLength(),"\nNumero de nodos: " + numNodos);

                matriz2 = new Objeto[numNodos][numNodos];
                llenarMatriz(matriz2);
                auxLinea = 0;
                leerNodos(leerArchivo,2,auxLinea);

                nFinal2 = numNodos - 1;

                break;

            case 3 :
                auxCadena = auxCadena.replaceAll("[,]","");
                auxNombre = auxCadena.substring(7);
                txtSalida.insertText(txtSalida.getLength(),"\n\n==== NUEVO AUTOMATA ENCONTRADO ====" + "\nNombre: " + auxNombre + "\nNumero: " + numAutomata);
                System.out.println("Nombre: " + auxNombre);
                System.out.println("Numero: " + numAutomata);

                Controller.numAutomata = numAutomata;
                Controller.lbl3 = auxNombre;
                nombres[numAutomata] = auxNombre;

                auxCadena = leerArchivo.nextLine();
                auxLinea++;
                numNodos = leerNumero(auxCadena,0);
                txtSalida.insertText(txtSalida.getLength(),"\nNumero de nodos: " + numNodos);

                matriz3 = new Objeto[numNodos][numNodos];
                llenarMatriz(matriz3);
                auxLinea = 0;
                leerNodos(leerArchivo,3,auxLinea);

                nFinal3 = numNodos - 1;

                break;

            case 4 :
                auxCadena = auxCadena.replaceAll("[,]","");
                auxNombre = auxCadena.substring(7);
                txtSalida.insertText(txtSalida.getLength(),"\n\n==== NUEVO AUTOMATA ENCONTRADO ====" + "\nNombre: " + auxNombre + "\nNumero: " + numAutomata);
                System.out.println("Nombre: " + auxNombre);
                System.out.println("Numero: " + numAutomata);

                Controller.numAutomata = numAutomata;
                Controller.lbl4 = auxNombre;
                nombres[numAutomata] = auxNombre;

                auxCadena = leerArchivo.nextLine();
                auxLinea++;
                numNodos = leerNumero(auxCadena,0);
                txtSalida.insertText(txtSalida.getLength(),"\nNumero de nodos: " + numNodos);

                matriz4 = new Objeto[numNodos][numNodos];
                llenarMatriz(matriz4);
                auxLinea = 0;
                leerNodos(leerArchivo,4,auxLinea);

                nFinal4 = numNodos - 1;

                break;

            case 5 :
                auxCadena = auxCadena.replaceAll("[,]","");
                auxNombre = auxCadena.substring(7);
                txtSalida.insertText(txtSalida.getLength(),"\n\n==== NUEVO AUTOMATA ENCONTRADO ====" + "\nNombre: " + auxNombre + "\nNumero: " + numAutomata);
                System.out.println("Nombre: " + auxNombre);
                System.out.println("Numero: " + numAutomata);

                Controller.numAutomata = numAutomata;
                Controller.lbl5 = auxNombre;
                nombres[numAutomata] = auxNombre;

                auxCadena = leerArchivo.nextLine();
                auxLinea++;
                numNodos = leerNumero(auxCadena,0);
                txtSalida.insertText(txtSalida.getLength(),"\nNumero de nodos: " + numNodos);

                matriz5 = new Objeto[numNodos][numNodos];
                llenarMatriz(matriz5);
                auxLinea = 0;
                leerNodos(leerArchivo,5,auxLinea);

                nFinal5 = numNodos - 1;

                break;

        }
        numAutomata++;
    }

    // Se manda llamar desde crearAutomatas(), permite leer el contenido que sigue del nombre y guardarlo //
    // en la matriz correspondiente //
    private void leerNodos(Scanner leerArchivo, int nummatriz, int auxLinea){

        Objeto[][] matriz = null;
        int nodoOrigen;
        int nodoDestino;
        String validacion;

        switch (nummatriz){
            case 1: matriz = matriz1; break;
            case 2: matriz = matriz2; break;
            case 3: matriz = matriz3; break;
            case 4: matriz = matriz4; break;
            case 5: matriz = matriz5; break;
        }

        System.out.println("[Leyendo nodos");
        System.out.println("Tamaño matriz: " + matriz.length);

        while (leerArchivo.hasNextLine()) {

            auxCadena = leerArchivo.nextLine();
            auxLinea++;

            if(auxCadena.charAt(0) == 'N'){
                System.out.println("\n\nNuevo automata encontrado");
                numAutomata++;
                crearAutomata(numAutomata);
                break;
            }

            txtSalida.insertText(txtSalida.getLength(),"\n\n---- LINEA #" + auxLinea + " ----" + "\nContenido: " + auxCadena);
            System.out.println("\n\nLinea #" + auxLinea + "\nContenido: " + auxCadena);
            auxPosicion = 0;

            // Extrae el primer elemento, que corresponde al nodo origen
            nodoOrigen = leerNumero(auxCadena,auxPosicion);
            //nodoOrigen = Integer.parseInt(auxCadena.substring(0,1));
            txtSalida.insertText(txtSalida.getLength(),"\nNodo origen: " + nodoOrigen);
            //System.out.println("Nodo origen: " + nodoOrigen);

            // Extrae el tercer caracter, que corresponde al nodo destino
            nodoDestino = leerNumero(auxCadena,auxPosicion);
            //nodoDestino = Integer.parseInt(auxCadena.substring(2,3));
            txtSalida.insertText(txtSalida.getLength(),"\nNodo destino: " + nodoDestino);
            //System.out.println("Nodo destino: " + nodoDestino);

            // Extrae el contenido despues del nodo destino, que corresponde a la validacion
            validacion = auxCadena.substring(auxPosicion);
            txtSalida.insertText(txtSalida.getLength(),"\nValidacion: " + validacion);
            //System.out.println("Validacion: " + validacion);

            // Guarda la informacion en la matriz
            matriz[nodoOrigen][nodoDestino] = new Objeto(nodoOrigen,nodoDestino,validacion);
            System.out.println("\nContenido guardado: " +
                    "\nNodo origen:" + matriz[nodoOrigen][nodoDestino].NodoOrigen +
                    "\nNodo destino:" + matriz[nodoOrigen][nodoDestino].NodoDestino +
                    "\nValidacion:" + matriz[nodoOrigen][nodoDestino].validacion);
        }

    }

    // ===== METODOS PARA EJEMPLOS ===== //
    private void ProcesoEjemplos(){

        txtSalida.clear();
        validaciones = Controller.validaciones;
        auxLinea = 1;

        while (leerArchivo.hasNextLine()) {

            auxCadena = leerArchivo.nextLine();
            txtSalida.insertText(txtSalida.getLength(), "\n\n---- LINEA #" + auxLinea + " ----" + "\nContenido: " + auxCadena);
            auxLinea++;

            System.out.println("Contenido de cadena: " + auxCadena);

            for(int i = 0 ; i < 5 ; i++){

                if(validaciones[i]){

                    System.out.println("Numero de validacion: " + (i+1));
                    revisarCadena(auxCadena, txtSalida, i+1);

                }

            }

        }


    }

    // Se manda llamar desde ProcesoEjmplos(), permite validar cada linea del archivo, //
    // hace tantas validaciones como hayan sido seleccionadas en la ventana anterior //
    private void revisarCadena(String auxCadena, TextArea txtSalida,int numMatriz){

        System.out.println("Numero de matriz: " + numMatriz);
        Objeto[][] matrizArevisar = null;

        switch (numMatriz){
            case 1: matrizArevisar = matriz1;
                    NodoFinal = nFinal1;
                    break;
            case 2: matrizArevisar = matriz2;
                    NodoFinal = nFinal2;
                    break;
            case 3: matrizArevisar = matriz3;
                    NodoFinal = nFinal3;
                    break;
            case 4: matrizArevisar = matriz4;
                    NodoFinal = nFinal4;
                    break;
            case 5: matrizArevisar = matriz5;
                    NodoFinal = nFinal5;
                    break;
        }

        System.out.println("Nodo final: " + NodoFinal);

        txtSalida.insertText(txtSalida.getLength(), "\n\nTipo de validacion: " + nombres[numMatriz]);

        Valida = true;
        char[] cadena = auxCadena.toCharArray();
        NodoActual = NodoInicial;
        int auxNoValido = 0 ;

        for(int ContadorCharCadena = 0 ; ContadorCharCadena < cadena.length ; ContadorCharCadena++){

            for(int Columna = 0 ; Columna < matrizArevisar.length ; Columna++){

                if(matrizArevisar[NodoActual][Columna] == null){
                    continue;
                }

                //System.out.println("char: " + cadena[ContadorCharCadena]);
                //System.out.println("Fila: " + NodoActual);
                //System.out.println("Columna: " + Columna);
                //System.out.println("\n");

                txtSalida.insertText(txtSalida.getLength(),"\n\nNodo actual: q" + NodoActual);
                txtSalida.insertText(txtSalida.getLength(),"\nCaracter a evaluar: " + cadena[ContadorCharCadena]);

                if(Pattern.matches(matrizArevisar[NodoActual][Columna].validacion,String.valueOf(cadena[ContadorCharCadena]))){

                    txtSalida.insertText(txtSalida.getLength(),"\nValidacion correcta, cambia al nodo: q" + matrizArevisar[NodoActual][Columna].NodoDestino);
                    NodoActual = matrizArevisar[NodoActual][Columna].NodoDestino;
                    Valida = true;
                    break;

                }else {
                    txtSalida.insertText(txtSalida.getLength(),"\nNo hay validacion, buscando otro camino");
                    Valida = false;
                }
            }

            if(!Valida){
                break;
            }

        }
        if(NodoActual == NodoFinal && Valida){
            txtSalida.insertText(txtSalida.getLength(),"\n\n---- CADENA [" + auxCadena + "] VALIDA ----");
        }else {
            txtSalida.insertText(txtSalida.getLength(),"\n\n---- CADENA [" + auxCadena + "] NO VALIDA ----");
        }

    }

    //Permite leer numero de nodos, o nodos origen y destino mas alla de un solo caracter
    private int leerNumero(String auxCadena, int posicion){

        //System.out.println("\n[Leyendo numero");
        //System.out.println("Tamaño cadena: " + auxCadena.length());
        String numero = "";
        int numeroNodos = 0;

        for (int i = posicion ; i < auxCadena.length() ; i++){

            //System.out.println("Auxiliar de posicion: " + posicion);
            //System.out.println("Caracter: " + auxCadena.substring(i,i+1));

            if(auxCadena.charAt(i) == ','){
                break;
            }

            numero = numero + auxCadena.substring(i,i+1);
            //System.out.println("Numero dentro del ciclo: " + numero);
            posicion++;

        }

        numeroNodos = Integer.parseInt(numero);
        auxPosicion = posicion+1;
        //System.out.println("Numero:" + numeroNodos + "]");
        return numeroNodos;

    }
    //Llena con null
    private void llenarMatriz(Objeto[][] matriz){
        for (int i = 0 ; i < numNodos-1 ; i++){
            for(int k = 0 ; k < numNodos-1 ; k++){
                matriz[i][k] = null;
            }
        }
    }
    // REVISAR PARA MATRICES QUE TIENEN NODOS NULL
    private void imprimeMatriz(Objeto[][] matriz){
        System.out.println("[Entra a imprimir matriz]");
        for (int i = 0 ; i < matriz.length ; i++){
            for(int k = 0 ; k < matriz.length ; k++){
                System.out.println(matriz[i][k].NodoOrigen);
                System.out.println(matriz[i][k].NodoDestino);
                System.out.println(matriz[i][k].validacion);
            }
        }
        System.out.println("[Sale de imprimir matriz]");
    }

}
