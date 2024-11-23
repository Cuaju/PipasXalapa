/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aguaxalapafx.pojos;

/**
 *
 * @author Cuauhtemoc Calderon
 */
public class Colonia {
private Integer idImueble;
    private String nombreColonia;
    private float precioCompra;
    private float precioVenta;
    private String calle;
    private String colonia;
    private int codigoPostal;
    private int numeroExterior;
    private int numeroInterior;
    private float tamanioM2;
    private Integer idPropietario;
    private String nombrePropietario;
    private Integer idVendedor;
    private String nombreVendedor;
    

    public Colonia() {
    }

    public Colonia(Integer idImueble, String nombreColonia, float precioCompra, float precioVenta, String calle, String colonia, int codigoPostal, int numeroExterior, int numeroInterior, float tamanioM2, Integer idPropietario, String nombrePropietario, Integer idVendedor, String nombreVendedor) {
        this.idImueble = idImueble;
        this.nombreColonia = nombreColonia;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.calle = calle;
        this.colonia = colonia;
        this.codigoPostal = codigoPostal;
        this.numeroExterior = numeroExterior;
        this.numeroInterior = numeroInterior;
        this.tamanioM2 = tamanioM2;
        this.idPropietario = idPropietario;
        this.nombrePropietario = nombrePropietario;
        this.idVendedor = idVendedor;
        this.nombreVendedor = nombreVendedor;
    }



    public Integer getIdImueble() {
        return idImueble;
    }

    public void setIdImueble(Integer idImueble) {
        this.idImueble = idImueble;
    }

    public String getNombreColonia() {
        return nombreColonia;
    }

    public void setNombreColonia(String nombreColonia) {
        this.nombreColonia = nombreColonia;
    }

    public float getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(float precioCompra) {
        this.precioCompra = precioCompra;
    }

    public float getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(float precioVenta) {
        this.precioVenta = precioVenta;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public int getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(int codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public int getNumeroExterior() {
        return numeroExterior;
    }

    public void setNumeroExterior(int numeroExterior) {
        this.numeroExterior = numeroExterior;
    }

    public int getNumeroInterior() {
        return numeroInterior;
    }

    public void setNumeroInterior(int numeroInterior) {
        this.numeroInterior = numeroInterior;
    }

    public float getTamanioM2() {
        return tamanioM2;
    }

    public void setTamanioM2(float tamanioM2) {
        this.tamanioM2 = tamanioM2;
    }

    public Integer getIdPropietario() {
        return idPropietario;
    }

    public void setIdPropietario(Integer idPropietario) {
        this.idPropietario = idPropietario;
    }

    public Integer getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(Integer idVendedor) {
        this.idVendedor = idVendedor;
    }

    public String getNombrePropietario() {
        return nombrePropietario;
    }

    public void setNombrePropietario(String nombrePropietario) {
        this.nombrePropietario = nombrePropietario;
    }

    public String getNombreVendedor() {
        return nombreVendedor;
    }

    public void setNombreVendedor(String nombreVendedor) {
        this.nombreVendedor = nombreVendedor;
    }
    
}
