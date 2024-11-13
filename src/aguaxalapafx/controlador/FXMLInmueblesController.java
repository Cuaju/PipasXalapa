/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package aguaxalapafx.controlador;

import aguaxalapafx.modelo.daos.InmuebleDAO;
import aguaxalapafx.pojos.Inmueble;
import aguaxalapafx.utilidades.Constantes;
import aguaxalapafx.utilidades.Utils;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;

/**
 * FXML Controller class
 *
 * @author Cuauhtemoc Calderon
 */
public class FXMLInmueblesController implements Initializable {
    private ObservableList<Inmueble> inmuebles;
    @FXML
    private TextField tfBusquedaInmueble;
    @FXML
    private TableView<Inmueble> tvInmuebles;
    @FXML
    private TableColumn colNombre;
    @FXML
    private TableColumn colPrecioCompra;
    @FXML
    private TableColumn colPrecioVenta;
    @FXML
    private TableColumn colCalle;
    @FXML
    private TableColumn colColonia;
    @FXML
    private TableColumn colCodigoPostal;
    @FXML
    private TableColumn colNoExterior;
    @FXML
    private TableColumn colNoInterior;
    @FXML
    private TableColumn colTamanio;
    @FXML
    private TableColumn colPropietario;
    @FXML
    private TableColumn colVendedor;
    @FXML
    private Label lbTitulo;

    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) {
        configurarTabla();
        cargarDatosInmuebles();
        
        configurarBusquedaInmueble();
    }    
    


    private void configurarTabla(){
        colNombre.setCellValueFactory(new PropertyValueFactory ("nombreInmueble"));
        colPrecioCompra.setCellValueFactory(new PropertyValueFactory ("precioCompra"));
        colPrecioVenta.setCellValueFactory(new PropertyValueFactory ("precioVenta"));
        colCalle.setCellValueFactory(new PropertyValueFactory ("calle"));
        colColonia.setCellValueFactory(new PropertyValueFactory ("colonia"));
        colCodigoPostal.setCellValueFactory(new PropertyValueFactory ("codigoPostal"));
        colNoExterior.setCellValueFactory(new PropertyValueFactory ("numeroExterior"));
        colNoInterior.setCellValueFactory(new PropertyValueFactory ("numeroInterior"));
        colTamanio.setCellValueFactory(new PropertyValueFactory ("tamanioM2"));
        colPropietario.setCellValueFactory(new PropertyValueFactory ("nombrePropietario"));
        colVendedor.setCellValueFactory(new PropertyValueFactory ("nombreVendedor"));
    }
    
    private void cargarDatosInmuebles(){
        inmuebles = FXCollections.observableArrayList();
        HashMap<String, Object> respuesta = InmuebleDAO.obtenerInmueble();
        boolean isError = (boolean) respuesta.get(Constantes.KEY_ERROR);
        if(!isError){
            ArrayList<Inmueble> inmueblesBD = (ArrayList<Inmueble>) respuesta.get("inmuebles");
            inmuebles.addAll(inmueblesBD);
            tvInmuebles.setItems(inmuebles);
        }else{
            Utils.mostrarAlertaSimple("Error", "" + respuesta.get(Constantes.KEY_MENSAJE), Alert.AlertType.ERROR);
        }
    }
    
        public void configurarBusquedaInmueble(){
        if(inmuebles.size()>0){
            //La p significa el predicado que va aplicar si es true
            FilteredList<Inmueble> filtroPaciente = new FilteredList<>(inmuebles, p -> true);
            tfBusquedaInmueble.textProperty().addListener(new ChangeListener<String>(){
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
    @FXML
    private void btnClicImprimirLista(ActionEvent event) {
        String encabezados = "Nombre,Precio compra,Precio venta,Calle,Colonia,Codigo Postal,NumExterior,NumInterior,Metros Cuadrados, Propietario, Vendedor";
        DirectoryChooser directorio = new DirectoryChooser();
        directorio.setTitle("Selecciona una carpeta");
        File directorioDestiino = directorio.showDialog(tfBusquedaInmueble.getScene().getWindow());
        if(directorioDestiino != null){
            try{
                String ruta = directorioDestiino.getAbsolutePath()+"/inmuebles.csv";
                File archivoExportado = new File(ruta);
                Writer archivoEscritura = new BufferedWriter(new FileWriter(archivoExportado));
                archivoEscritura.write(encabezados);
                //for each se usa para cada pacientes, y se guarda en paciente, el cual solo existe en el for
                for(Inmueble inmueble : inmuebles){
                    String fila = String.format("\n%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s", ""+inmueble.getNombreInmueble(), ""+inmueble.getPrecioCompra(),""+inmueble.getPrecioVenta(),
                            ""+inmueble.getCalle(),""+inmueble.getColonia(),""+inmueble.getCodigoPostal(), ""+inmueble.getNumeroExterior(),""+inmueble.getNumeroInterior(),
                                ""+inmueble.getTamanioM2(),""+inmueble.getNombrePropietario(),""+inmueble.getNombreVendedor());
                    archivoEscritura.write(fila);
                }
                archivoEscritura.close();
                Utils.mostrarAlertaSimple("Archivo Exportado", "El archivo fue exportado correctamente", Alert.AlertType.INFORMATION);
            }catch(IOException e){
                Utils.mostrarAlertaSimple("Error al Exportar", e.getMessage(), Alert.AlertType.ERROR);
            }
        }
    }

    @FXML
    private void imgClicMenuPrincipal(MouseEvent event) {
        Utils.irMenuPrincipal(lbTitulo);
    }
    
}
