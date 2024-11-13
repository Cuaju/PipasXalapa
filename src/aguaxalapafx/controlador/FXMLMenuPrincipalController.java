/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package aguaxalapafx.controlador;

import aguaxalapafx.pojos.Secretaria;
import aguaxalapafx.utilidades.Utils;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Cuauhtemoc Calderon
 */
public class FXMLMenuPrincipalController implements Initializable {
    private Secretaria secretaria;
    @FXML
    private Label lbBienvenido;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    public void inicializarValores(Secretaria secretaria){
        this.secretaria = secretaria;
                lbBienvenido.setText("Bienvenido: "+this.secretaria.getNombre()+" "+this.secretaria.getApellidoPaterno()+" "+this.secretaria.getApellidoMaterno());
    }

    @FXML
    private void btnClicAgendarCita(ActionEvent event) {
        try {
            Stage escenario = new Stage();
            FXMLLoader loader = Utils.obtenerLoader("vista/FXMLAgendarCitas.fxml");
            Parent root = loader.load();
            FXMLAgendarPipasController controlador = loader.getController();
            Scene escena = new Scene(root);
            escenario.setScene(escena);
            escenario.setTitle("Pacientes");
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void btnClicVerCitas(ActionEvent event) {
        try {
            Stage escenario = new Stage();
            FXMLLoader loader = Utils.obtenerLoader("vista/FXMLCitas.fxml");
            Parent root = loader.load();
            FXMLPipasController controlador = loader.getController();
            Scene escena = new Scene(root);
            escenario.setScene(escena);
            escenario.setTitle("Pacientes");
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void btnClicVerInmuebles(ActionEvent event) {
        try {
            Stage escenario = new Stage();
            FXMLLoader loader = Utils.obtenerLoader("vista/FXMLInmuebles.fxml");
            Parent root = loader.load();
            FXMLInmueblesController controlador = loader.getController();
            Scene escena = new Scene(root);
            escenario.setScene(escena);
            escenario.setTitle("Pacientes");
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();
        } catch (IOException ex) {
            ex.printStackTrace();
        }  
    }

    @FXML
    private void imgClicAgendarCitas(MouseEvent event) {
        try {
            Stage escenario = new Stage();
            FXMLLoader loader = Utils.obtenerLoader("vista/FXMLAgendarCitas.fxml");
            Parent root = loader.load();
            FXMLAgendarPipasController controlador = loader.getController();
            Scene escena = new Scene(root);
            escenario.setScene(escena);
            escenario.setTitle("Pacientes");
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }

    @FXML
    private void imgClicVerCitas(MouseEvent event) {
        try {
            Stage escenario = new Stage();
            FXMLLoader loader = Utils.obtenerLoader("vista/FXMLCitas.fxml");
            Parent root = loader.load();
            FXMLPipasController controlador = loader.getController();
            Scene escena = new Scene(root);
            escenario.setScene(escena);
            escenario.setTitle("Pacientes");
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void imgClicVerInmuebles(MouseEvent event) {
        try {
            Stage escenario = new Stage();
            FXMLLoader loader = Utils.obtenerLoader("vista/FXMLInmuebles.fxml");
            Parent root = loader.load();
            FXMLInmueblesController controlador = loader.getController();
            Scene escena = new Scene(root);
            escenario.setScene(escena);
            escenario.setTitle("Pacientes");
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();
        } catch (IOException ex) {
            ex.printStackTrace();
        }       
    }

    @FXML
    private void imgCerrarSesion(MouseEvent event) {
        try{
            Stage escenarioPrincipal = (Stage) lbBienvenido.getScene().getWindow();
            Parent root = FXMLLoader.load(aguaxalapafx.Aguaxalapafx.class.getResource("vista/FXMLIniciarSesion.fxml"));
            Scene escenaPrincipal = new Scene(root);
            escenarioPrincipal.setTitle("Home");
            escenarioPrincipal.setScene(escenaPrincipal);
            escenarioPrincipal.show();
            } catch (IOException ex) {
            Logger.getLogger(FXMLIniciarSesionController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    
}
