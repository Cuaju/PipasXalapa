package aguaxalapafx.modelo.daos;

import aguaxalapafx.utilidades.Constantes;
import java.util.HashMap;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

public class ColoniaDAOTest {
    
    public ColoniaDAOTest() {
    }

    @Test
    public void testObtenerColonias() {
        System.out.println("obtenerColonias");
        
        // Llamada al método obtenerColonias para obtener el resultado
        HashMap<String, Object> result = ColoniaDAO.obtenerColonias();

        // Verificamos que el resultado no sea null
        assertNotNull(result);

        // Verificamos que la clave KEY_ERROR esté en el resultado y sea false
        assertTrue(result.containsKey(Constantes.KEY_ERROR));
        assertFalse((Boolean) result.get(Constantes.KEY_ERROR));

        // Verificamos que la clave "colonias" esté en el resultado y sea una lista
        assertTrue(result.containsKey("colonias"));
        assertTrue(result.get("colonias") instanceof List);
        
        // Opcionalmente, puedes verificar que la lista de colonias no esté vacía
        // si esperas tener datos en la base de datos de prueba:
        // List<?> colonias = (List<?>) result.get("colonias");
        // assertFalse(colonias.isEmpty());
    }

    @Test
    public void testObtenerColonia() {
        System.out.println("obtenerColonia");
        
        // Llamada al método obtenerColonia para obtener el resultado
        HashMap<String, Object> result = ColoniaDAO.obtenerColonia();

        // Verificamos que el resultado no sea null
        assertNotNull(result);

        // Verificamos que la clave KEY_ERROR esté en el resultado y sea false
        assertTrue(result.containsKey(Constantes.KEY_ERROR));
        assertFalse((Boolean) result.get(Constantes.KEY_ERROR));

        // Verificamos que la clave "colonias" esté en el resultado y sea una lista
        assertTrue(result.containsKey("colonias"));
        assertTrue(result.get("colonias") instanceof List);
    }
}
