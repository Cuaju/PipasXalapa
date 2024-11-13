package aguaxalapafx.modelo.daos;

import aguaxalapafx.modelo.daos.AutenticacionDAO;
import aguaxalapafx.pojos.RespuestaLogin;
import org.junit.Test;
import static org.junit.Assert.*;

public class AutenticacionDAOTest {
    
    public AutenticacionDAOTest() {
    }

    @Test
    public void testIniciarSesion() {
        System.out.println("iniciarSesion");
        String user = "";
        String pwd = "";

        RespuestaLogin result = AutenticacionDAO.iniciarSesion(user, pwd);

        assertNotNull(result); // Verificamos que el resultado no sea null
        assertTrue(result.getError()); // Verificamos que haya un error
        assertEquals("Numero de personal y/o contraseña incorrectos, favor de verificalos.", result.getMenasje());
    }
}
