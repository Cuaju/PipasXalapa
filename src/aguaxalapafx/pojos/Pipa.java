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
    private int idCita;
    private String fecha;
    private String hora;
    private int idCliente;
    private int idInmueble;
    private String nombreCliente;
    private String correoCliente;
    private String nombreInmueble;
    private String calleInmueble;

    public Pipa(int idCita, String fecha, String hora, int idCliente, int idInmueble, String nombreCliente, String correoCliente, String nombreInmueble, String calleInmueble) {
        this.idCita = idCita;
        this.fecha = fecha;
        this.hora = hora;
        this.idCliente = idCliente;
        this.idInmueble = idInmueble;
        this.nombreCliente = nombreCliente;
        this.correoCliente = correoCliente;
        this.nombreInmueble = nombreInmueble;
        this.calleInmueble = calleInmueble;
    }

    public Pipa() {
    }

    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
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

    public int getIdInmueble() {
        return idInmueble;
    }

    public void setIdInmueble(int idInmueble) {
        this.idInmueble = idInmueble;
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

    public String getNombreInmueble() {
        return nombreInmueble;
    }

    public void setNombreInmueble(String nombreInmueble) {
        this.nombreInmueble = nombreInmueble;
    }

    public String getCalleInmueble() {
        return calleInmueble;
    }

    public void setCalleInmueble(String calleInmueble) {
        this.calleInmueble = calleInmueble;
    }
    
   
    
}
