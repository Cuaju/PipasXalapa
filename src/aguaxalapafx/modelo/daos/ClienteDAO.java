/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aguaxalapafx.modelo.daos;

import aguaxalapafx.modelo.ConexionBD;
import aguaxalapafx.pojos.Cliente;
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
public class ClienteDAO {

    public static HashMap<String, Object> obtenerClientes(){
        HashMap<String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put(Constantes.KEY_ERROR, true);
        
        try (Connection conexionBD = ConexionBD.obtenerConexion()) {
            if (conexionBD != null) {
                String consulta = "SELECT idCliente, nombre, apellidoPaterno, apellidoMaterno, correo FROM cliente;";
                try (PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                     ResultSet resultado = prepararSentencia.executeQuery()) {
                     
                    List<Cliente> clientes = new ArrayList<>();
                    while (resultado.next()) {
                        Cliente cliente = new Cliente();
                        cliente.setIdCliente(resultado.getInt("idCliente"));
                        cliente.setNombre(resultado.getString("nombre"));
                        cliente.setApellidoPaterno(resultado.getString("apellidoPaterno"));
                        cliente.setApellidoMaterno(resultado.getString("apellidoMaterno"));
                        cliente.setCorreo(resultado.getString("correo"));
                        clientes.add(cliente);
                    }
                    respuesta.put(Constantes.KEY_ERROR, false);
                    respuesta.put("clientes", clientes);
                } catch (SQLException e) {
                    respuesta.put(Constantes.KEY_MENSAJE, e.getMessage());
                }
            } else {
                respuesta.put(Constantes.KEY_MENSAJE, Constantes.MSJ_ERROR_CONEXION);
            }
        } catch (SQLException e) {
            respuesta.put(Constantes.KEY_MENSAJE, e.getMessage());
        }
        return respuesta;
    }
}


