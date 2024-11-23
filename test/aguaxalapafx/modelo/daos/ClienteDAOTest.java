package aguaxalapafx.modelo.daos;

import aguaxalapafx.utilidades.Constantes;
import java.util.HashMap;
import org.junit.Test;
import static org.junit.Assert.*;

public class ClienteDAOTest {
    
    public ClienteDAOTest() {
    }

    @Test
    public void testObtenerClientes() {
        System.out.println("obtenerClientes");
        
        // Llamada al método obtenerClientes para obtener el resultado
        HashMap<String, Object> result = ClienteDAO.obtenerClientes();

        // Verificamos que el resultado no sea null
        assertNotNull(result);

        // Verificamos que la clave KEY_ERROR esté en el resultado y sea false
        assertTrue(result.containsKey(Constantes.KEY_ERROR));
        assertFalse((Boolean) result.get(Constantes.KEY_ERROR));

        // Verificamos que la clave "clientes" esté en el resultado y sea una lista
        assertTrue(result.containsKey("clientes"));
        assertTrue(result.get("clientes") instanceof java.util.List);
        
        // Opcionalmente, puedes verificar que la lista de clientes no esté vacía
        // si esperas tener datos en la base de datos de prueba:
        // List<?> clientes = (List<?>) result.get("clientes");
        // assertFalse(clientes.isEmpty());
    }
}
