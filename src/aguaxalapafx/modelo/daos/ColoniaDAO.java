/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aguaxalapafx.modelo.daos;

import aguaxalapafx.modelo.ConexionBD;
import aguaxalapafx.pojos.Colonia;
import aguaxalapafx.utilidades.Constantes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 *
 * @author Cuauhtemoc Calderon
 */
public class ColoniaDAO {
    public static HashMap<String, Object> obtenerColonias(){
        HashMap<String, Object> respuesta = new LinkedHashMap<>();
                System.out.println("1");
        respuesta.put(Constantes.KEY_ERROR, true);
        Connection conexionBD = ConexionBD.obtenerConexion();
                System.out.println("2");
        if(conexionBD != null){
            try{
                String consulta = "SELECT idInmueble, nombreInmueble, precioVenta, calle, colonia, codigoPostal, tamanioM2 FROM inmueble;";
                PreparedStatement prepararSentencia= conexionBD.prepareStatement(consulta);
                ResultSet resultado = prepararSentencia.executeQuery();
                //TODO
                List<Colonia> colonias = new ArrayList();
                while(resultado.next()){
                    Colonia colonia = new Colonia();
                    colonia.setIdImueble(resultado.getInt("idInmueble"));
                    colonia.setNombreColonia(resultado.getString("nombreInmueble"));
                    colonia.setPrecioVenta(resultado.getFloat("precioVenta"));
                    colonia.setCalle(resultado.getString("calle"));
                    colonia.setColonia(resultado.getString("colonia"));
                    colonia.setCodigoPostal(resultado.getInt("codigoPostal"));
                    colonia.setTamanioM2(resultado.getFloat("tamanioM2"));
              
                    colonias.add(colonia);
                    System.out.println("3");
                }
                respuesta.put(Constantes.KEY_ERROR, false);
                respuesta.put("colonias", colonias);
                conexionBD.close();
                System.out.println("4");
            }catch (SQLException e){
                respuesta.put(Constantes.KEY_MENSAJE, e.getMessage());
            }
        }else{
            respuesta.put(Constantes.KEY_MENSAJE, Constantes.MSJ_ERROR_CONEXION);
        }
        return respuesta;
    }
    
    
    public static HashMap<String, Object> obtenerColonia(){
        HashMap<String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put(Constantes.KEY_ERROR, true);
        Connection conexionBD = ConexionBD.obtenerConexion();
        if(conexionBD != null){
            try{
                String consulta = "SELECT " +
                " i.nombreInmueble," +
                " i.precioCompra," +
                " i.precioVenta," +
                " i.calle," +
                " i.colonia," +
                " i.codigoPostal," +
                " i.numeroExterior," +
                " i.numeroInterior," +
                " i.tamanioM2," +
                " CONCAT(p.nombrePropietario, ' ', p.apellidoPaterno, ' ', p.apellidoMaterno) AS nombreCompletoPropietario," +
                " CONCAT(v.nombreVendedor, ' ', v.apellidoPaterno, ' ', v.apellidoMaterno) AS nombreCompletoVendedor" +
                " FROM " +
                " inmueble i" +
                " JOIN " +
                " propietario p ON i.idPropietario = p.idPropietario" +
                " JOIN " +
                " vendedor v ON i.idVendedor = v.idVendedor;";
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                ResultSet resultado = prepararSentencia.executeQuery();
                List<Colonia> colonias = new ArrayList();
                while(resultado.next()){
                    Colonia colonia = new Colonia();
                    colonia.setNombreColonia(resultado.getString("nombreInmueble"));
                    colonia.setPrecioCompra(resultado.getFloat("precioCompra"));
                    colonia.setPrecioVenta(resultado.getFloat("precioVenta"));
                    colonia.setCalle(resultado.getString("calle"));
                    colonia.setColonia(resultado.getString("colonia"));
                    colonia.setCodigoPostal(resultado.getInt("codigoPostal"));
                    colonia.setNumeroExterior(resultado.getInt("numeroExterior"));
                    colonia.setNumeroInterior(resultado.getInt("numeroInterior"));
                    colonia.setTamanioM2(resultado.getFloat("tamanioM2"));
                    colonia.setNombrePropietario(resultado.getString("nombreCompletoPropietario"));
                    colonia.setNombreVendedor(resultado.getString("nombreCompletoVendedor"));
                    colonias.add(colonia);
                }
                respuesta.put(Constantes.KEY_ERROR, false);
                respuesta.put("colonias", colonias);
                conexionBD.close();
            }catch(SQLException e){
                respuesta.put(Constantes.KEY_MENSAJE, e.getMessage());
            }
        }else{
            respuesta.put(Constantes.KEY_MENSAJE, Constantes.MSJ_ERROR_CONEXION);
        }
        return respuesta;
    }
}
