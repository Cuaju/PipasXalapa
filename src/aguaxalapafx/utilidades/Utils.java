/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aguaxalapafx.utilidades;

import aguaxalapafx.controlador.FXMLIniciarSesionController;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author Cuauhtemoc Calderon
 */
public class Utils {
        public static void mostrarAlertaSimple(String titulo, String mensaje, Alert.AlertType tipo){
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.show();
        //alerta.showAndWait();
    }
    
    public static boolean mostrarAlertaConfirmacion(String titulo, String mensaje){
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        Optional<ButtonType> botonSeleccionado = alerta.showAndWait();
        return (botonSeleccionado.get() == ButtonType.OK);
    }
    
    public static FXMLLoader obtenerLoader(String ruta){
        return new FXMLLoader(aguaxalapafx.Aguaxalapafx.class.getResource(ruta));
    }
    
    public static void irMenuPrincipal(Label lb){
        ((Stage)lb.getScene().getWindow()).close();
    }
    
}
