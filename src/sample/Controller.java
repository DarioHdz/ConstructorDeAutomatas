package sample;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Controller implements Initializable {

    // Elementos de interfaz y alertas
    Stage ventanaPrincipal;
    Alert alert = new Alert(Alert.AlertType.ERROR);
    @FXML Button btnArchivoA,btnArchivoB,btnProcesarAutomatas,btnProcesarEjemplos;
    @FXML Button btnAut1,btnAut2,btnAut3,btnAut4,btnAut5;
    @FXML CheckBox chkArchivoA,chkArchivoB,chkAut1,chkAut2,chkAut3,chkAut4,chkAut5;
    @FXML Label lblNombreArchivoA,lblNombreArchivoB,lblAut1,lblAut2,lblAut3,lblAut4,lblAut5;

    // Elementos de archivos
    Scanner leerArchivo = null;
    FileChooser fileChooser1 = new FileChooser();
    FileChooser fileChooser2 = new FileChooser();
    public static File archivoA = null;
    public static File archivoB = null;

    // Variables auxiliares de metodos
    public static int indicador;
    public static String lbl1,lbl2,lbl3,lbl4,lbl5;
    public static int numAutomata;
    public static boolean[] validaciones = {false,false,false,false,false,false};


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Titulo de la venta para buscar archivos y filtros para el tipo de archivo aceptado
        // Para automatas acepta .csv y .txt para ejemplos solo .txt
        fileChooser1.setTitle("Buscar archivo de automatas");
        fileChooser2.setTitle("Buscar archivo de ejemplos");
        fileChooser1.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Archivo de Excel separado por comas", "*.csv"),
                new FileChooser.ExtensionFilter("Archivo de texto", "*.txt"));
        fileChooser2.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Archivo de texto", "*.txt"));

        // Desactiva los checkbox de archivos
        chkArchivoA.setDisable(true);
        chkArchivoB.setDisable(true);

        // Acciones de los checkbox para validar
        chkAut1.setOnAction(event -> validaciones[0] = !validaciones[0]);
        chkAut2.setOnAction(event -> validaciones[1] = !validaciones[1]);
        chkAut3.setOnAction(event -> validaciones[2] = !validaciones[2]);
        chkAut4.setOnAction(event -> validaciones[3] = !validaciones[3]);
        chkAut5.setOnAction(event -> validaciones[4] = !validaciones[4]);

        // Define el fondo de los botones de estado antes de cargar el archivo de automatas
        btnAut1.setBackground(new Background(new BackgroundFill(Color.web("#cb3234"), CornerRadii.EMPTY, Insets.EMPTY)));
        btnAut2.setBackground(new Background(new BackgroundFill(Color.web("#cb3234"), CornerRadii.EMPTY, Insets.EMPTY)));
        btnAut3.setBackground(new Background(new BackgroundFill(Color.web("#cb3234"), CornerRadii.EMPTY, Insets.EMPTY)));
        btnAut4.setBackground(new Background(new BackgroundFill(Color.web("#cb3234"), CornerRadii.EMPTY, Insets.EMPTY)));
        btnAut5.setBackground(new Background(new BackgroundFill(Color.web("#cb3234"), CornerRadii.EMPTY, Insets.EMPTY)));

        //Desactiva los checkbox de los automatas antes de cargar el archivo de automatas
        chkAut1.setDisable(true);
        chkAut2.setDisable(true);
        chkAut3.setDisable(true);
        chkAut4.setDisable(true);
        chkAut5.setDisable(true);

        // ===== Acciones de los botones ===== //

        // Botones para cargar archivos
        btnArchivoA.setOnAction(event -> {
            desactivar();
            buscarArchivo('A');
        });
        btnArchivoB.setOnAction(event -> {
            buscarArchivo('B');
        });

        // Botones para procesar
        btnProcesarAutomatas.setOnAction(event -> {
            if (archivoA != null) {
                try {

                    indicador = 0;

                    ocultarActual();

                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ventanaProceso.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.setTitle("Procesando archivo de autómatas");
                    stage.setScene(new Scene(root1));
                    stage.showAndWait();

                    mostrarActual();

                    activar(numAutomata);

                    archivoA = null;
                    chkArchivoA.setSelected(false);
                    lblNombreArchivoA.setText("Nombre archivo");
                    leerArchivo = null;

                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("No se ha seleccionado archivo de origen.");
                alert.showAndWait();
            }
        });
        btnProcesarEjemplos.setOnAction(event -> {

            // Si ninguna opcion esta seleccionada muestra mensaje de error
            if(!chkAut1.isSelected() && !chkAut2.isSelected() && !chkAut3.isSelected() && !chkAut4.isSelected() &&
                !chkAut5.isSelected()){
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("Ninguna opcion seleccionada.");
                alert.showAndWait();
            }else {

                if (archivoB != null) {
                    try {

                        indicador = 1;

                        ocultarActual();

                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ventanaProceso.fxml"));
                        Parent root1 = (Parent) fxmlLoader.load();
                        Stage stage = new Stage();
                        stage.setTitle("Procesando archivo de ejemplos");
                        stage.setScene(new Scene(root1));
                        stage.showAndWait();

                        mostrarActual();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    alert.setHeaderText(null);
                    alert.setTitle("Error");
                    alert.setContentText("No se ha seleccionado archivo de origen.");
                    alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                    alert.showAndWait();
                    archivoB = null;
                    chkArchivoB.setSelected(false);
                    lblNombreArchivoB.setText("Nombre archivo");
                    leerArchivo = null;

                }

            }


        });

    }

    // Metodo para buscar archivo, tanto de automataa [A], como  de ejemplos [B] //
    private void buscarArchivo(char boton) {

        if (boton == 'A') {
            archivoA = fileChooser1.showOpenDialog(null);
            if ((archivoA == null) || (archivoA.getName().equals(""))) {
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("Archivo invalido o no seleccionado.");
                alert.showAndWait();
            } else {
                chkArchivoA.setSelected(true);
                lblNombreArchivoA.setText(archivoA.getName());
                //leerArchivo = new Scanner(archivoA);
            }
        } else {
            archivoB = fileChooser2.showOpenDialog(null);
            if ((archivoB == null) || (archivoB.getName().equals(""))) {
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("Archivo invalido o no seleccionado.");
                alert.showAndWait();
                archivoB = null;
                chkArchivoB.setSelected(false);
                lblNombreArchivoB.setText("Nombre archivo");
                leerArchivo = null;
            } else {
                chkArchivoB.setSelected(true);
                lblNombreArchivoB.setText(archivoB.getName());
                //leerArchivo = new Scanner(archivoB);
            }
        }


    }

    // Metodos para ocultar / mostrar la ventana principal, se usan cuando se abre la ventana de procesar //
    public void ocultarActual() {
        ventanaPrincipal = (Stage) chkArchivoA.getScene().getWindow();
        ventanaPrincipal.hide();
    }
    public void mostrarActual() {
        ventanaPrincipal = (Stage) chkArchivoA.getScene().getWindow();
        ventanaPrincipal.show();
    }

    // Activa / desactiva los elementos de la interfaz asociados a los automatas si estan cargados o no //
    public void activar(int numAutomata){

        switch (numAutomata) {

            case 1 :
                lblAut1.setText(lbl1);
                btnAut1.setBackground(new Background(new BackgroundFill(Color.web("#008000"), CornerRadii.EMPTY, Insets.EMPTY)));
                chkAut1.setDisable(false);
                break;

            case 2 :
                lblAut1.setText(lbl1);
                btnAut1.setBackground(new Background(new BackgroundFill(Color.web("#008000"), CornerRadii.EMPTY, Insets.EMPTY)));
                chkAut1.setDisable(false);
                lblAut2.setText(lbl2);
                btnAut2.setBackground(new Background(new BackgroundFill(Color.web("#008000"), CornerRadii.EMPTY, Insets.EMPTY)));
                chkAut2.setDisable(false);
                break;

            case 3 :
                lblAut1.setText(lbl1);
                btnAut1.setBackground(new Background(new BackgroundFill(Color.web("#008000"), CornerRadii.EMPTY, Insets.EMPTY)));
                chkAut1.setDisable(false);
                lblAut2.setText(lbl2);
                btnAut2.setBackground(new Background(new BackgroundFill(Color.web("#008000"), CornerRadii.EMPTY, Insets.EMPTY)));
                chkAut2.setDisable(false);
                lblAut3.setText(lbl3);
                btnAut3.setBackground(new Background(new BackgroundFill(Color.web("#008000"), CornerRadii.EMPTY, Insets.EMPTY)));
                chkAut3.setDisable(false);
                break;

            case 4 :
                lblAut1.setText(lbl1);
                btnAut1.setBackground(new Background(new BackgroundFill(Color.web("#008000"), CornerRadii.EMPTY, Insets.EMPTY)));
                chkAut1.setDisable(false);
                lblAut2.setText(lbl2);
                btnAut2.setBackground(new Background(new BackgroundFill(Color.web("#008000"), CornerRadii.EMPTY, Insets.EMPTY)));
                chkAut2.setDisable(false);
                lblAut3.setText(lbl3);
                btnAut3.setBackground(new Background(new BackgroundFill(Color.web("#008000"), CornerRadii.EMPTY, Insets.EMPTY)));
                chkAut3.setDisable(false);
                lblAut4.setText(lbl4);
                btnAut4.setBackground(new Background(new BackgroundFill(Color.web("#008000"), CornerRadii.EMPTY, Insets.EMPTY)));
                chkAut4.setDisable(false);
                break;

            case 5 :
                lblAut1.setText(lbl1);
                btnAut1.setBackground(new Background(new BackgroundFill(Color.web("#008000"), CornerRadii.EMPTY, Insets.EMPTY)));
                chkAut1.setDisable(false);
                lblAut2.setText(lbl2);
                btnAut2.setBackground(new Background(new BackgroundFill(Color.web("#008000"), CornerRadii.EMPTY, Insets.EMPTY)));
                chkAut2.setDisable(false);
                lblAut3.setText(lbl3);
                btnAut3.setBackground(new Background(new BackgroundFill(Color.web("#008000"), CornerRadii.EMPTY, Insets.EMPTY)));
                chkAut3.setDisable(false);
                lblAut4.setText(lbl4);
                btnAut4.setBackground(new Background(new BackgroundFill(Color.web("#008000"), CornerRadii.EMPTY, Insets.EMPTY)));
                chkAut4.setDisable(false);
                lblAut5.setText(lbl5);
                btnAut5.setBackground(new Background(new BackgroundFill(Color.web("#008000"), CornerRadii.EMPTY, Insets.EMPTY)));
                chkAut5.setDisable(false);
                break;


        }


    }
    public void desactivar(){
        lblAut1.setText("Autómata 1");
        btnAut1.setBackground(new Background(new BackgroundFill(Color.web("#cb3234"), CornerRadii.EMPTY, Insets.EMPTY)));
        chkAut1.setDisable(true);
        lblAut2.setText("Autómata 2");
        btnAut2.setBackground(new Background(new BackgroundFill(Color.web("#cb3234"), CornerRadii.EMPTY, Insets.EMPTY)));
        chkAut2.setDisable(true);
        lblAut3.setText("Autómata 3");
        btnAut3.setBackground(new Background(new BackgroundFill(Color.web("#cb3234"), CornerRadii.EMPTY, Insets.EMPTY)));
        chkAut3.setDisable(true);
        lblAut4.setText("Autómata 4");
        btnAut4.setBackground(new Background(new BackgroundFill(Color.web("#cb3234"), CornerRadii.EMPTY, Insets.EMPTY)));
        chkAut4.setDisable(true);
        lblAut5.setText("Autómata 5");
        btnAut5.setBackground(new Background(new BackgroundFill(Color.web("#cb3234"), CornerRadii.EMPTY, Insets.EMPTY)));
        chkAut5.setDisable(true);
    }
}


