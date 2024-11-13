/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aguaxalapafx.pojos;

/**
 *
 * @author Cuauhtemoc Calderon
 */
public class Secretaria {
    private Integer idSecretaria;
    private String user;
    private String pwd;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;

    public Secretaria() {
    }

    public Secretaria(Integer idSecretaria, String user, String pwd, String nombre, String apellidoPaterno, String apellidoMaterno) {
        this.idSecretaria = idSecretaria;
        this.user = user;
        this.pwd = pwd;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
    }

    public Integer getIdSecretaria() {
        return idSecretaria;
    }

    public void setIdSecretaria(Integer idSecretaria) {
        this.idSecretaria = idSecretaria;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    
    
    
}
