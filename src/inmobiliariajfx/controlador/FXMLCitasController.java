/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package inmobiliariajfx.controlador;

import inmobiliariajfx.modelo.daos.CitaDAO;
import inmobiliariajfx.pojos.Cita;
import inmobiliariajfx.utilidades.Constantes;
import inmobiliariajfx.utilidades.Utils;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jonathan
 */
public class FXMLCitasController implements Initializable {

    private ObservableList<Cita> citas;
    @FXML
    private TableView<Cita> tvCitas;
    @FXML
    private TableColumn colNombreInmueble;
    @FXML
    private TableColumn colCalle;
    @FXML
    private TableColumn colNombreCliente;
    @FXML
    private TableColumn colCorreo;
    @FXML
    private TableColumn colFechaCita;
    @FXML
    private TableColumn colHora;
    @FXML
    private TextField tfBuscarCita;
    @FXML
    private Label lbTitulo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarTabla();
        cargarDatosPacientes();
    }   
    
        private void configurarTabla(){
        colNombreInmueble.setCellValueFactory(new PropertyValueFactory("nombreInmueble"));
        colCalle.setCellValueFactory(new PropertyValueFactory("calleInmueble"));
        colNombreCliente.setCellValueFactory(new PropertyValueFactory("nombreCliente"));
        colCorreo.setCellValueFactory(new PropertyValueFactory("correoCliente"));
        colFechaCita.setCellValueFactory(new PropertyValueFactory("fecha"));
        colHora.setCellValueFactory(new PropertyValueFactory("hora"));
    }
    
    private void cargarDatosPacientes(){
        citas = FXCollections.observableArrayList();
        HashMap<String, Object> respuesta = CitaDAO.obtenerCitas();
        boolean isError = (boolean) respuesta.get(Constantes.KEY_ERROR);
        if(!isError){
            ArrayList<Cita> citasBD = (ArrayList<Cita>) respuesta.get("citas");
            citas.addAll(citasBD);
            tvCitas.setItems(citas);
        }else{
            Utils.mostrarAlertaSimple("Error","" + respuesta.get(Constantes.KEY_MENSAJE), Alert.AlertType.ERROR);
        }
        configurarBusquedaPaciente();
    }

    private void configurarBusquedaPaciente(){
        if(citas.size()>0){
            FilteredList<Cita> filtroPaciente = new FilteredList<>(citas,p -> true);
            tfBuscarCita.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    filtroPaciente.setPredicate(p -> {
                    
                    if(newValue == null || newValue.isEmpty()){
                        return true;
                    }
                    String palabraFiltro = newValue.toLowerCase();
                    if (p.getCalleInmueble().toLowerCase().contains(palabraFiltro)){
                        return true;
                    }
                    if (p.getNombreInmueble().toLowerCase().contains(palabraFiltro)){
                        return true;
                    }
                    if (p.getNombreCliente().toLowerCase().contains(palabraFiltro)){
                        return true;
                    }
                    if (p.getCorreoCliente().toLowerCase().contains(palabraFiltro)){
                        return true;
                    }
                    if (p.getFecha().toLowerCase().contains(palabraFiltro)){
                        return true;
                    }
                    return false;
                    });
                }
            });
            SortedList<Cita> listaOrdenada = new SortedList<>(filtroPaciente);
            listaOrdenada.comparatorProperty().bind(tvCitas.comparatorProperty());
            tvCitas.setItems(listaOrdenada);
        }
    }    
    
    @FXML
    private void btnClicAgendarCita(ActionEvent event) {
        try{
            Stage escenarioPrincipal = (Stage) tfBuscarCita.getScene().getWindow();
            Parent root = FXMLLoader.load(inmobiliariajfx.Inmobiliariajfx.class.getResource("vista/FXMLAgendarCitas.fxml"));
            Scene escenaPrincipal = new Scene(root);
            escenarioPrincipal.setTitle("Home");
            escenarioPrincipal.setScene(escenaPrincipal);
            escenarioPrincipal.show();
            } catch (IOException ex) {
            Logger.getLogger(FXMLAgendarCitasController.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }

    @FXML
    private void imgClicMenuPrincipal(MouseEvent event) {
        Utils.irMenuPrincipal(lbTitulo);
    }
    
    
}
