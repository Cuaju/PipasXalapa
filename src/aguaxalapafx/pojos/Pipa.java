/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aguaxalapafx.pojos;

/**
 *
 * @author Cuauhtemoc Calderon
 */
public class Pipa {
    private int idPipa;
    private String fecha;
    private String hora;
    private int idCliente;
    private int idColonia;
    private String nombreCliente;
    private String correoCliente;
    private String nombreColonia;
    private String calle;

    public Pipa(int idPipa, String fecha, String hora, int idCliente, int idColonia, String nombreCliente, String correoCliente, String nombreColonia, String calle) {
        this.idPipa = idPipa;
        this.fecha = fecha;
        this.hora = hora;
        this.idCliente = idCliente;
        this.idColonia = idColonia;
        this.nombreCliente = nombreCliente;
        this.correoCliente = correoCliente;
        this.nombreColonia = nombreColonia;
        this.calle = calle;
    }

    public Pipa() {
    }

    public int getIdPipa() {
        return idPipa;
    }

    public void setIdPipa(int idPipa) {
        this.idPipa = idPipa;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdColonia() {
        return idColonia;
    }

    public void setIdColonia(int idColonia) {
        this.idColonia = idColonia;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getCorreoCliente() {
        return correoCliente;
    }

    public void setCorreoCliente(String correoCliente) {
        this.correoCliente = correoCliente;
    }

    public String getNombreColonia() {
        return nombreColonia;
    }

    public void setNombreColonia(String nombreColonia) {
        this.nombreColonia = nombreColonia;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }   
}