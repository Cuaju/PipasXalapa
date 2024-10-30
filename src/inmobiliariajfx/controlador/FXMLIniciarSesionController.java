/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package inmobiliariajfx.controlador;

import inmobiliariajfx.modelo.daos.AutenticacionDAO;
import inmobiliariajfx.pojos.RespuestaLogin;
import inmobiliariajfx.pojos.Secretaria;
import inmobiliariajfx.utilidades.Utils;
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
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Cuauhtemoc Calderon
 */
public class FXMLIniciarSesionController implements Initializable {

    @FXML
    private TextField tfUsuario;
    @FXML
    private PasswordField pfContrasena;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private boolean validarCampos(String numPersonal, String password){
        if(tfUsuario.getText()!= null || pfContrasena.getText()!= null) {
        return true;
        }else{
        return false; 
   }
}
                
                
    // 1.0.0
    private void irPantallaPrincipal(Secretaria secretaria){
        try {
            Stage escenarioPrincipal = (Stage) tfUsuario.getScene().getWindow();
            FXMLLoader loader = Utils.obtenerLoader("vista/FXMLMenuPrincipal.fxml");
            Parent root = loader.load();
            FXMLMenuPrincipalController controlador =loader.getController();
            controlador.inicializarValores(secretaria);
            
            Scene escenaPrincipal = new Scene(root);
            escenarioPrincipal.setTitle("Home");
            escenarioPrincipal.setScene(escenaPrincipal);
            escenarioPrincipal.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLMenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @FXML
    private void btnClicIniciarSesion(ActionEvent event) {
        String user = tfUsuario.getText();
        String password = pfContrasena.getText();
        if(validarCampos (user, password)!= false){
            RespuestaLogin respuesta = AutenticacionDAO.iniciarSesion(user, password);
            System.out.println(respuesta.getMenasje());
            if(!respuesta.getError()){
                Utils.mostrarAlertaSimple("Bienvenido(a)", "Bienvenido(a)"
                        + respuesta.getSecretaria().getNombre()+" al sistema"
                        , Alert.AlertType.INFORMATION);
                irPantallaPrincipal(respuesta.getSecretaria());
                System.out.println("si llega");
            }else{
                Utils.mostrarAlertaSimple("Error", respuesta.getMenasje(), Alert.AlertType.WARNING);
            }
        }
    }

    
}
