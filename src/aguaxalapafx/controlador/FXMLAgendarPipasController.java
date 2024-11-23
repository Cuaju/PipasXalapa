/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package aguaxalapafx.controlador;

import aguaxalapafx.modelo.daos.PipaDAO;
import aguaxalapafx.modelo.daos.ClienteDAO;
import aguaxalapafx.modelo.daos.ColoniaDAO;
import aguaxalapafx.pojos.Pipa;
import aguaxalapafx.pojos.Cliente;
import aguaxalapafx.pojos.Colonia;
import aguaxalapafx.utilidades.Constantes;
import aguaxalapafx.utilidades.Utils;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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
 * @author Cuauhtemoc Calderon
 */
public class FXMLAgendarPipasController implements Initializable {
    private ObservableList<Cliente> clientes;
    private ObservableList<Colonia> colonias;
    private ObservableList<String> horas;
    
    @FXML
    private DatePicker dpSeleccionarFecha;
    @FXML
    private ComboBox<String> cbSeleccionarHora;
    @FXML
    private TableView<Cliente> tvClientes;
    @FXML
    private TableColumn colNombreCliente;
    @FXML
    private TableColumn colApellidoPaterno;
    @FXML
    private TableColumn colApellidoMaterno;
    @FXML
    private TableColumn colCorreo;
    @FXML
    private TextField tfBuscarCliente;
    @FXML
    private TableView<Colonia> tvColonias;
    @FXML
    private TableColumn colNombreColonia;
    @FXML
    private TableColumn colPrecioVenta;
    @FXML
    private TableColumn colCalle;
    @FXML
    private TableColumn colColonia;
    @FXML
    private TableColumn colCodigoPostal;
    @FXML
    private TableColumn colMetros;
    @FXML
    private TextField tfBuscarColonia;
    @FXML
    private Label lbTitulo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setCombo();
        configurarTablaClientes();
        cargarDatosClientes();
        
        configurarTablaColonias();
        cargarDatosColonias();
        
        configurarBusquedaCliente();
        configurarBusquedaColonia();
        
    }    
    
    private void setCombo(){
        horas = FXCollections.observableArrayList();
        for (int hora = 8; hora < 20; hora++) {
            String horaTexto = String.format("%02d:00", hora); // Formato HH:00
            horas.add(horaTexto);
        }
        cbSeleccionarHora.setItems(horas);
    }

        private void configurarTablaClientes(){
        colNombreCliente.setCellValueFactory(new PropertyValueFactory("nombre"));
        colApellidoPaterno.setCellValueFactory(new PropertyValueFactory("apellidoPaterno"));
        colApellidoMaterno.setCellValueFactory(new PropertyValueFactory("apellidoMaterno"));
        colCorreo.setCellValueFactory(new PropertyValueFactory("correo"));
    }
    
    private void cargarDatosClientes(){
        clientes = FXCollections.observableArrayList();
        HashMap<String, Object> respuesta = ClienteDAO.obtenerClientes();
        boolean isError = (boolean) respuesta.get(Constantes.KEY_ERROR);
        if(!isError){
           ArrayList<Cliente> clientesBD =(ArrayList<Cliente>) respuesta.get("clientes");
           clientes.addAll(clientesBD);
           tvClientes.setItems(clientes);
        }else{
            Utils.mostrarAlertaSimple("Error", ""+ respuesta.get(Constantes.KEY_MENSAJE), Alert.AlertType.WARNING);
        }
    }
    
    private void configurarTablaColonias(){
        colNombreColonia.setCellValueFactory(new PropertyValueFactory("nombreInmueble"));
        colPrecioVenta.setCellValueFactory(new PropertyValueFactory("precioVenta"));
        colCalle.setCellValueFactory(new PropertyValueFactory("calle"));
        colColonia.setCellValueFactory(new PropertyValueFactory("colonia"));
        colCodigoPostal.setCellValueFactory(new PropertyValueFactory("codigoPostal"));
        colMetros.setCellValueFactory(new PropertyValueFactory("tamanioM2"));
    }
    
    private void cargarDatosColonias(){
        colonias = FXCollections.observableArrayList();
        HashMap<String, Object> respuesta = ColoniaDAO.obtenerColonias();
        boolean isError = (boolean) respuesta.get(Constantes.KEY_ERROR);
        if(!isError){
           ArrayList<Colonia> coloniasBD =(ArrayList<Colonia>) respuesta.get("colonias");
           colonias.addAll(coloniasBD);
           tvColonias.setItems(colonias);
        }else{
            Utils.mostrarAlertaSimple("Error", ""+ respuesta.get(Constantes.KEY_MENSAJE), Alert.AlertType.WARNING);
        }
    }
    
        public void configurarBusquedaCliente(){
        if(clientes.size()>0){
            //La p significa el predicado que va aplicar si es true
            FilteredList<Cliente> filtroPaciente = new FilteredList<>(clientes, p -> true);
            tfBuscarCliente.textProperty().addListener(new ChangeListener<String>(){
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    filtroPaciente.setPredicate(p -> {
                        //caso cuando la cadena es vacia
                        if(newValue == null || newValue.isEmpty()){
                            return true;
                        }
                        //evaluacion de predicado para nombre
                        String palabraFiltro = newValue.toLowerCase();
                        if(p.getNombre().toLowerCase().contains(palabraFiltro)){
                            return true;
                        }
                        //Evaluacion de precicado para apellido paterno
                        if(p.getApellidoPaterno().toLowerCase().contains(palabraFiltro)){
                            return true;
                        }
                        //Evaluacion de precicado para apellido materno
                        if(p.getApellidoMaterno().toLowerCase().contains(palabraFiltro)){
                            return true;
                        }
                        
                        //Default
                        return false;
                    });
                }
                
            });
            SortedList<Cliente> listaOrdenada = new SortedList<>(filtroPaciente);
            listaOrdenada.comparatorProperty().bind(tvClientes.comparatorProperty());
            tvClientes.setItems(listaOrdenada);
        }
    }
        
    public void configurarBusquedaColonia(){
        if(colonias.size()>0){
            //La p significa el predicado que va aplicar si es true
            FilteredList<Colonia> filtroPaciente = new FilteredList<>(colonias, p -> true);
            tfBuscarColonia.textProperty().addListener(new ChangeListener<String>(){
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    filtroPaciente.setPredicate(p -> {
                        //caso cuando la cadena es vacia
                        if(newValue == null || newValue.isEmpty()){
                            return true;
                        }
                        //evaluacion de predicado para nombre
                        String palabraFiltro = newValue.toLowerCase();
                        if(p.getNombreColonia().toLowerCase().contains(palabraFiltro)){
                            return true;
                        }
                        //Evaluacion de precicado para apellido paterno
                        if(p.getCalle().toLowerCase().contains(palabraFiltro)){
                            return true;
                        }
                        //Evaluacion de precicado para apellido materno
                        if(p.getColonia().toLowerCase().contains(palabraFiltro)){
                            return true;
                        }
                        //Default
                        return false;
                    });
                }
                
            });
            SortedList<Colonia> listaOrdenada = new SortedList<>(filtroPaciente);
            listaOrdenada.comparatorProperty().bind(tvColonias.comparatorProperty());
            tvColonias.setItems(listaOrdenada);
        }
    }
      
    private void cerrarVentana(){
        ((Stage)dpSeleccionarFecha.getScene().getWindow()).close();
    }
    
    @FXML
    private void btnClicAgendar(ActionEvent event) {
        
        Cliente clienteSeleccionado = tvClientes.getSelectionModel().getSelectedItem();
        Colonia coloniaSeleccionada = tvColonias.getSelectionModel().getSelectedItem();
        System.out.println(clienteSeleccionado);
        System.out.println(coloniaSeleccionada);
        
        if(clienteSeleccionado !=null && coloniaSeleccionada !=null){
        Pipa pipa = new Pipa();
        
            pipa.setFecha(dpSeleccionarFecha.getValue().toString());
            pipa.setHora(cbSeleccionarHora.getSelectionModel().getSelectedItem());
            pipa.setIdCliente(clienteSeleccionado.getIdCliente());
            pipa.setIdColonia(coloniaSeleccionada.getIdImueble());
            
        HashMap<String, Object> respuesta = PipaDAO.guardarPipa(pipa);
        if(!(boolean)respuesta.get(Constantes.KEY_ERROR)){
            Utils.mostrarAlertaSimple("Pipa guardada", (String) respuesta.get(Constantes.KEY_MENSAJE), Alert.AlertType.INFORMATION);
            cerrarVentana();
        }else{
            Utils.mostrarAlertaSimple("Error al guardar paciente", (String) respuesta.get(Constantes.KEY_MENSAJE), Alert.AlertType.ERROR);
        }
    }else{
            Utils.mostrarAlertaSimple("Datos Incompletos", "Para guardar una pipa Seleccione un cliente y una colonia"
                    , Alert.AlertType.WARNING);
        }
    }

    @FXML
    private void imgClicMenuPrincipal(MouseEvent event) {
        Utils.irMenuPrincipal(lbTitulo);
    }
    
    
}
