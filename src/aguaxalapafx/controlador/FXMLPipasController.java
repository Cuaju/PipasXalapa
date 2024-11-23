/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package aguaxalapafx.controlador;

import aguaxalapafx.modelo.daos.PipaDAO;
import aguaxalapafx.pojos.Pipa;
import aguaxalapafx.utilidades.Constantes;
import aguaxalapafx.utilidades.Utils;
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
public class FXMLPipasController implements Initializable {

    private ObservableList<Pipa> pipas;
    @FXML
    private TableView<Pipa> tvPipas;
    @FXML
    private TableColumn colNombreColonia;
    @FXML
    private TableColumn colCalle;
    @FXML
    private TableColumn colNombreCliente;
    @FXML
    private TableColumn colCorreo;
    @FXML
    private TableColumn colFecha;
    @FXML
    private TableColumn colHora;
    @FXML
    private TextField tfBuscarPipa;
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
        colNombreColonia.setCellValueFactory(new PropertyValueFactory("nombreInmueble"));
        colCalle.setCellValueFactory(new PropertyValueFactory("calleInmueble"));
        colNombreCliente.setCellValueFactory(new PropertyValueFactory("nombreCliente"));
        colCorreo.setCellValueFactory(new PropertyValueFactory("correoCliente"));
        colFecha.setCellValueFactory(new PropertyValueFactory("fecha"));
        colHora.setCellValueFactory(new PropertyValueFactory("hora"));
    }
    
    private void cargarDatosPacientes(){
        pipas = FXCollections.observableArrayList();
        HashMap<String, Object> respuesta = PipaDAO.obtenerPipas();
        boolean isError = (boolean) respuesta.get(Constantes.KEY_ERROR);
        if(!isError){
            ArrayList<Pipa> pipasBD = (ArrayList<Pipa>) respuesta.get("pipas");
            pipas.addAll(pipasBD);
            tvPipas.setItems(pipas);
        }else{
            Utils.mostrarAlertaSimple("Error","" + respuesta.get(Constantes.KEY_MENSAJE), Alert.AlertType.ERROR);
        }
        configurarBusquedaPaciente();
    }

    private void configurarBusquedaPaciente(){
        if(pipas.size()>0){
            FilteredList<Pipa> filtroPaciente = new FilteredList<>(pipas,p -> true);
            tfBuscarPipa.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    filtroPaciente.setPredicate(p -> {
                    
                    if(newValue == null || newValue.isEmpty()){
                        return true;
                    }
                    String palabraFiltro = newValue.toLowerCase();
                    if (p.getCalle().toLowerCase().contains(palabraFiltro)){
                        return true;
                    }
                    if (p.getNombreColonia().toLowerCase().contains(palabraFiltro)){
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
            SortedList<Pipa> listaOrdenada = new SortedList<>(filtroPaciente);
            listaOrdenada.comparatorProperty().bind(tvPipas.comparatorProperty());
            tvPipas.setItems(listaOrdenada);
        }
    }    
    
    @FXML
    private void btnClicAgendarPipa(ActionEvent event) {
        try{
            Stage escenarioPrincipal = (Stage) tfBuscarPipa.getScene().getWindow();
            Parent root = FXMLLoader.load(aguaxalapafx.Aguaxalapafx.class.getResource("vista/FXMLAgendarPipas.fxml"));
            Scene escenaPrincipal = new Scene(root);
            escenarioPrincipal.setTitle("Home");
            escenarioPrincipal.setScene(escenaPrincipal);
            escenarioPrincipal.show();
            } catch (IOException ex) {
            Logger.getLogger(FXMLAgendarPipasController.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }

    @FXML
    private void imgClicMenuPrincipal(MouseEvent event) {
        Utils.irMenuPrincipal(lbTitulo);
    }
    
    
}
