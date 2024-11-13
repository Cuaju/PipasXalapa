/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aguaxalapafx.pojos;

/**
 *
 * @author Cuauhtemoc Calderon
 */
public class RespuestaLogin {
        
    private Boolean error;
    private String menasje;
    private Secretaria secretaria;

    public RespuestaLogin() {
    }

    public RespuestaLogin(Boolean error, String menasje, Secretaria secretaria) {
        this.error = error;
        this.menasje = menasje;
        this.secretaria = secretaria;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public void setMenasje(String menasje) {
        this.menasje = menasje;
    }

    public void setSecretaria(Secretaria secretaria) {
        this.secretaria = secretaria;
    }

    public Boolean getError() {
        return error;
    }

    public String getMenasje() {
        return menasje;
    }

    public Secretaria getSecretaria() {
        return secretaria;
    }
    
}
