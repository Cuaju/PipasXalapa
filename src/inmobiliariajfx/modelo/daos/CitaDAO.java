/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inmobiliariajfx.modelo.daos;

import inmobiliariajfx.modelo.ConexionBD;
import inmobiliariajfx.pojos.Cita;
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
public class CitaDAO {
    
    public static HashMap<String, Object> guardarCita(Cita cita){
        HashMap<String, Object> respuesta = new HashMap();
        respuesta.put("error", true);
        Connection conexionBD = ConexionBD.obtenerConexion();
        if(conexionBD != null){
            try {
                String sentencia = "INSERT INTO cita (fecha, hora, idCliente, idInmueble) "
                        + "VALUES (?,?,?,?)";
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(sentencia);
                prepararSentencia.setString(1, cita.getFecha());
                prepararSentencia.setString(2, cita.getHora());
                prepararSentencia.setInt(3, cita.getIdCliente());
                prepararSentencia.setInt(4, cita.getIdInmueble());
                int filasAfectadas = prepararSentencia.executeUpdate();
                if(filasAfectadas > 0){
                    respuesta.put(Constantes.KEY_ERROR, false);
                    respuesta.put(Constantes.KEY_MENSAJE, "Informacion de la cita guardada correctamente");
                }else{
                    respuesta.put(Constantes.KEY_MENSAJE, "Lo sentimos, huvo un error al guardar "
                            + "la informacion de la cita por favor revisa la informacion");
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
    
        public static HashMap<String, Object> obtenerCitas(){
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
                List<Cita> citas = new ArrayList();
                while(resultado.next()){
                    Cita cita = new Cita();
                    cita.setNombreInmueble(resultado.getString("nombreInmueble"));
                    cita.setCalleInmueble(resultado.getString("calle"));
                    cita.setNombreCliente(resultado.getString("nombre") +" "+ resultado.getString("apellidoPaterno") +" "+ resultado.getString("apellidoMaterno"));
                    cita.setCorreoCliente(resultado.getString("correo"));
                    cita.setFecha(resultado.getString("fecha"));
                    cita.setHora(resultado.getString("hora"));
                    citas.add(cita);
                }
                respuesta.put(Constantes.KEY_ERROR,false);
                respuesta.put("citas", citas);
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
