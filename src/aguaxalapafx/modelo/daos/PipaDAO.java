/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aguaxalapafx.modelo.daos;

import aguaxalapafx.modelo.ConexionBD;
import aguaxalapafx.pojos.Pipa;
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
public class PipaDAO {
    
    public static HashMap<String, Object> guardarPipa(Pipa pipa){
        HashMap<String, Object> respuesta = new HashMap();
        respuesta.put("error", true);
        Connection conexionBD = ConexionBD.obtenerConexion();
        if(conexionBD != null){
            try {
                String sentencia = "INSERT INTO cita (fecha, hora, idCliente, idInmueble) "
                        + "VALUES (?,?,?,?)";
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(sentencia);
                prepararSentencia.setString(1, pipa.getFecha());
                prepararSentencia.setString(2, pipa.getHora());
                prepararSentencia.setInt(3, pipa.getIdCliente());
                prepararSentencia.setInt(4, pipa.getIdColonia());
                int filasAfectadas = prepararSentencia.executeUpdate();
                if(filasAfectadas > 0){
                    respuesta.put(Constantes.KEY_ERROR, false);
                    respuesta.put(Constantes.KEY_MENSAJE, "Informacion de la pipa guardada correctamente");
                }else{
                    respuesta.put(Constantes.KEY_MENSAJE, "Lo sentimos, huvo un error al guardar "
                            + "la informacion de la pipa por favor revisa la informacion");
                }
                conexionBD.close();
            } catch (SQLException ex) {
                respuesta.put(Constantes.KEY_MENSAJE, ex.getMessage());
            }
            
        }else{
            respuesta.put(Constantes.KEY_MENSAJE, Constantes.MSJ_ERROR_CONEXION);
        }
        return respuesta;
    }
    
        public static HashMap<String, Object> obtenerPipas(){
        HashMap<String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put(Constantes.KEY_ERROR, true);
        Connection conexionBD = ConexionBD.obtenerConexion();
        if(conexionBD != null){
            try{
                String consulta= "SELECT  i.nombreInmueble, i.calle, c.nombre, c.apellidoPaterno, c.apellidoMaterno,"
                        + " c.correo, p.fecha, p.hora "
                        + "FROM cita p "
                        + "INNER JOIN inmueble i ON p.idInmueble = i.idInmueble "
                        + "INNER JOIN cliente c ON p.idCliente = c.idCliente;";
                PreparedStatement prepararSentencia= conexionBD.prepareStatement(consulta);
                ResultSet resultado = prepararSentencia.executeQuery();
                List<Pipa> pipas = new ArrayList();
                while(resultado.next()){
                    Pipa pipa = new Pipa();
                    pipa.setNombreColonia(resultado.getString("nombreInmueble"));
                    pipa.setCalle(resultado.getString("calle"));
                    pipa.setNombreCliente(resultado.getString("nombre") +" "+ resultado.getString("apellidoPaterno") +" "+ resultado.getString("apellidoMaterno"));
                    pipa.setCorreoCliente(resultado.getString("correo"));
                    pipa.setFecha(resultado.getString("fecha"));
                    pipa.setHora(resultado.getString("hora"));
                    pipas.add(pipa);
                }
                respuesta.put(Constantes.KEY_ERROR,false);
                respuesta.put("pipas", pipas);
                conexionBD.close();
            }catch (SQLException e){
                respuesta.put(Constantes.KEY_MENSAJE, e.getMessage());
            }
        }else{
            respuesta.put(Constantes.KEY_MENSAJE, Constantes.MSJ_ERROR_CONEXION);
        }
        return respuesta;
    } 
}
