/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aguaxalapafx.pojos;

/**
 *
 * @author Cuauhtemoc Calderon
 */
public class Colonias {
    private int idColonia;
    private String nombre;
    private String fechaInicioAgua;
    private String fechaFinAgua;

    public Colonias() {
    }

    public Colonias(int idColonia, String nombre, String fechaInicioAgua, String fechaFinAgua) {
        this.idColonia = idColonia;
        this.nombre = nombre;
        this.fechaInicioAgua = fechaInicioAgua;
        this.fechaFinAgua = fechaFinAgua;
    }

    public int getIdColonia() {
        return idColonia;
    }

    public String getNombre() {
        return nombre;
    }

    public String getFechaInicioAgua() {
        return fechaInicioAgua;
    }

    public String getFechaFinAgua() {
        return fechaFinAgua;
    }

    public void setIdColonia(int idColonia) {
        this.idColonia = idColonia;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFechaInicioAgua(String fechaInicioAgua) {
        this.fechaInicioAgua = fechaInicioAgua;
    }

    public void setFechaFinAgua(String fechaFinAgua) {
        this.fechaFinAgua = fechaFinAgua;
    }
    
    
}
