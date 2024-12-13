/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aguaxalapafx.modelo.daos;

import aguaxalapafx.modelo.ConexionBD;
import aguaxalapafx.pojos.RespuestaLogin;
import aguaxalapafx.pojos.Administrador;
import aguaxalapafx.utilidades.Constantes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Cuauhtemoc Calderon
 */
public class AutenticacionDAO {
        
    public static RespuestaLogin iniciarSesion(String user, String pwd){
        //TODO    
        RespuestaLogin respuesta = new RespuestaLogin();
        Connection conexionBD = ConexionBD.obtenerConexion();
        respuesta.setError(true);
        if(conexionBD != null){
            try{
               String consulta = "SELECT idSecretaria, user, pwd, nombre, apellidoPaterno, apellidoMaterno"
                       + " FROM secretaria WHERE user = ? AND pwd = ?";
               PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
               prepararSentencia.setString(1, user);
               prepararSentencia.setString(2, pwd);
               ResultSet resultadoSentencia = prepararSentencia.executeQuery();
               if(resultadoSentencia.next()){
                   respuesta.setError(false);
                   respuesta.setMenasje("Usuariio Identificado correctamente");
                   
                   Administrador secretaria = new Administrador();
                   secretaria.setIdSecretaria(resultadoSentencia.getInt("idSecretaria"));
                   secretaria.setNombre(resultadoSentencia.getString("nombre"));
                   secretaria.setApellidoPaterno(resultadoSentencia.getString("apellidoPaterno"));
                   secretaria.setApellidoMaterno(resultadoSentencia.getString("apellidoMaterno"));
                   respuesta.setSecretaria(secretaria);
               }else{
                   //respuesta.setError(true);
                   respuesta.setMenasje("Numero de personal y/o contraseña incorrectos, favor de verificalos.");
               }
               conexionBD.close();
            }catch (SQLException e){
               //respuesta.setError(true);
               respuesta.setMenasje(e.getMessage());
            }
        }else{
            //respuesta.setError(true);
            respuesta.setMenasje(Constantes.MSJ_ERROR_CONEXION);
        }
       return respuesta;
    }
}
