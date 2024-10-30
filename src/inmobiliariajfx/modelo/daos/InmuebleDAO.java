/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inmobiliariajfx.modelo.daos;

import inmobiliariajfx.modelo.ConexionBD;
import inmobiliariajfx.pojos.Inmueble;
import inmobiliariajfx.utilidades.Constantes;
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
public class InmuebleDAO {
    public static HashMap<String, Object> obtenerInmuebles(){
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
                List<Inmueble> inmuebles = new ArrayList();
                while(resultado.next()){
                    Inmueble inmueble = new Inmueble();
                    inmueble.setIdImueble(resultado.getInt("idInmueble"));
                    inmueble.setNombreInmueble(resultado.getString("nombreInmueble"));
                    inmueble.setPrecioVenta(resultado.getFloat("precioVenta"));
                    inmueble.setCalle(resultado.getString("calle"));
                    inmueble.setColonia(resultado.getString("colonia"));
                    inmueble.setCodigoPostal(resultado.getInt("codigoPostal"));
                    inmueble.setTamanioM2(resultado.getFloat("tamanioM2"));
              
                    inmuebles.add(inmueble);
                    System.out.println("3");
                }
                respuesta.put(Constantes.KEY_ERROR, false);
                respuesta.put("inmuebles", inmuebles);
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
    
    
    public static HashMap<String, Object> obtenerInmueble(){
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
                List<Inmueble> inmuebles = new ArrayList();
                while(resultado.next()){
                    Inmueble inmueble = new Inmueble();
                    inmueble.setNombreInmueble(resultado.getString("nombreInmueble"));
                    inmueble.setPrecioCompra(resultado.getFloat("precioCompra"));
                    inmueble.setPrecioVenta(resultado.getFloat("precioVenta"));
                    inmueble.setCalle(resultado.getString("calle"));
                    inmueble.setColonia(resultado.getString("colonia"));
                    inmueble.setCodigoPostal(resultado.getInt("codigoPostal"));
                    inmueble.setNumeroExterior(resultado.getInt("numeroExterior"));
                    inmueble.setNumeroInterior(resultado.getInt("numeroInterior"));
                    inmueble.setTamanioM2(resultado.getFloat("tamanioM2"));
                    inmueble.setNombrePropietario(resultado.getString("nombreCompletoPropietario"));
                    inmueble.setNombreVendedor(resultado.getString("nombreCompletoVendedor"));
                    inmuebles.add(inmueble);
                }
                respuesta.put(Constantes.KEY_ERROR, false);
                respuesta.put("inmuebles", inmuebles);
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
