/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package inmobiliariajfx.controlador;

import inmobiliariajfx.modelo.daos.CitaDAO;
import inmobiliariajfx.modelo.daos.ClienteDAO;
import inmobiliariajfx.modelo.daos.InmuebleDAO;
import inmobiliariajfx.pojos.Cita;
import inmobiliariajfx.pojos.Cliente;
import inmobiliariajfx.pojos.Inmueble;
import inmobiliariajfx.utilidades.Constantes;
import inmobiliariajfx.utilidades.Utils;
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
public class FXMLAgendarCitasController implements Initializable {
    private ObservableList<Cliente> clientes;
    private ObservableList<Inmueble> inmuebles;
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
    private TableView<Inmueble> tvInmuebles;
    @FXML
    private TableColumn colNombreInmueble;
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
    private TextField tfBuscarInmueble;
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
        
        configurarTablaInmuebles();
        cargarDatosInmuebles();
        
        configurarBusquedaCliente();
        configurarBusquedaInmueble();
        
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
    
    private void configurarTablaInmuebles(){
        colNombreInmueble.setCellValueFactory(new PropertyValueFactory("nombreInmueble"));
        colPrecioVenta.setCellValueFactory(new PropertyValueFactory("precioVenta"));
        colCalle.setCellValueFactory(new PropertyValueFactory("calle"));
        colColonia.setCellValueFactory(new PropertyValueFactory("colonia"));
        colCodigoPostal.setCellValueFactory(new PropertyValueFactory("codigoPostal"));
        colMetros.setCellValueFactory(new PropertyValueFactory("tamanioM2"));
    }
    
    private void cargarDatosInmuebles(){
        inmuebles = FXCollections.observableArrayList();
        HashMap<String, Object> respuesta = InmuebleDAO.obtenerInmuebles();
        boolean isError = (boolean) respuesta.get(Constantes.KEY_ERROR);
        if(!isError){
           ArrayList<Inmueble> inmueblesBD =(ArrayList<Inmueble>) respuesta.get("inmuebles");
           inmuebles.addAll(inmueblesBD);
           tvInmuebles.setItems(inmuebles);
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
        
    public void configurarBusquedaInmueble(){
        if(inmuebles.size()>0){
            //La p significa el predicado que va aplicar si es true
            FilteredList<Inmueble> filtroPaciente = new FilteredList<>(inmuebles, p -> true);
            tfBuscarInmueble.textProperty().addListener(new ChangeListener<String>(){
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    filtroPaciente.setPredicate(p -> {
                        //caso cuando la cadena es vacia
                        if(newValue == null || newValue.isEmpty()){
                            return true;
                        }
                        //evaluacion de predicado para nombre
                        String palabraFiltro = newValue.toLowerCase();
                        if(p.getNombreInmueble().toLowerCase().contains(palabraFiltro)){
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
            SortedList<Inmueble> listaOrdenada = new SortedList<>(filtroPaciente);
            listaOrdenada.comparatorProperty().bind(tvInmuebles.comparatorProperty());
            tvInmuebles.setItems(listaOrdenada);
        }
    }
      
    private void cerrarVentana(){
        ((Stage)dpSeleccionarFecha.getScene().getWindow()).close();
    }
    
    @FXML
    private void btnClicAgendar(ActionEvent event) {
        
        Cliente clienteSeleccionado = tvClientes.getSelectionModel().getSelectedItem();
        Inmueble inmuebleSeleccionado = tvInmuebles.getSelectionModel().getSelectedItem();
        System.out.println(clienteSeleccionado);
        System.out.println(inmuebleSeleccionado);
        
        if(clienteSeleccionado !=null && inmuebleSeleccionado !=null){
        Cita cita = new Cita();
        
            cita.setFecha(dpSeleccionarFecha.getValue().toString());
            cita.setHora(cbSeleccionarHora.getSelectionModel().getSelectedItem());
            cita.setIdCliente(clienteSeleccionado.getIdCliente());
            cita.setIdInmueble(inmuebleSeleccionado.getIdImueble());
            
        HashMap<String, Object> respuesta = CitaDAO.guardarCita(cita);
        if(!(boolean)respuesta.get(Constantes.KEY_ERROR)){
            Utils.mostrarAlertaSimple("Cita guardada", (String) respuesta.get(Constantes.KEY_MENSAJE), Alert.AlertType.INFORMATION);
            cerrarVentana();
        }else{
            Utils.mostrarAlertaSimple("Error al guardar paciente", (String) respuesta.get(Constantes.KEY_MENSAJE), Alert.AlertType.ERROR);
        }
    }else{
            Utils.mostrarAlertaSimple("Datos Incompletos", "Para guardar una cita Seleccione un cliente y un inmueble"
                    , Alert.AlertType.WARNING);
        }
    }

    @FXML
    private void imgClicMenuPrincipal(MouseEvent event) {
        Utils.irMenuPrincipal(lbTitulo);
    }
    
    
}
