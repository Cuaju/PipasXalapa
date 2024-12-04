/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aguaxalapafx.pojos;

/**
 *
 * @author Cuauhtemoc Calderon
 */
public class Pipas {
    private int idPipa;
    private int capacidad;
    private double precio;
    private String provedor;

    public Pipas() {
    }

    public Pipas(int idPipa, int capacidad, double precio, String provedor) {
        this.idPipa = idPipa;
        this.capacidad = capacidad;
        this.precio = precio;
        this.provedor = provedor;
    }

    public int getIdPipa() {
        return idPipa;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public double getPrecio() {
        return precio;
    }

    public String getProvedor() {
        return provedor;
    }

    public void setIdPipa(int idPipa) {
        this.idPipa = idPipa;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setProvedor(String provedor) {
        this.provedor = provedor;
    }
}
