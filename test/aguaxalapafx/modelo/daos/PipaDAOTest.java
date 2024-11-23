package aguaxalapafx.modelo.daos;

import aguaxalapafx.pojos.Pipa;
import aguaxalapafx.utilidades.Constantes;
import java.util.HashMap;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

public class PipaDAOTest {

    public PipaDAOTest() {
    }

    @Test
    public void testGuardarPipa() {
        System.out.println("guardarPipa");

        // Crear una instancia de Pipa con datos de prueba
        Pipa pipa = new Pipa();
        pipa.setFecha("2023-11-13");
        pipa.setHora("10:00");
        pipa.setIdCliente(1); // Cambia a un ID de cliente válido en tu base de datos de prueba
        pipa.setIdColonia(1); // Cambia a un ID de colonia válido en tu base de datos de prueba

        // Llamada al método guardarPipa para obtener el resultado
        HashMap<String, Object> result = PipaDAO.guardarPipa(pipa);

        // Verificamos que el resultado no sea null
        assertNotNull(result);

        // Verificamos que la clave KEY_ERROR esté en el resultado
        assertTrue(result.containsKey(Constantes.KEY_ERROR));

        // Verificamos que la clave KEY_MENSAJE esté en el resultado
        assertTrue(result.containsKey(Constantes.KEY_MENSAJE));

        // Si la inserción fue exitosa, esperamos que KEY_ERROR sea false y un mensaje específico
        if (!(Boolean) result.get(Constantes.KEY_ERROR)) {
            assertEquals("Informacion de la pipa guardada correctamente", result.get(Constantes.KEY_MENSAJE));
        } else {
            // Si la inserción falló, revisamos que KEY_ERROR sea true y que el mensaje no sea null
            assertTrue((Boolean) result.get(Constantes.KEY_ERROR));
            assertNotNull(result.get(Constantes.KEY_MENSAJE));
        }
    }

    @Test
    public void testObtenerPipas() {
        System.out.println("obtenerPipas");

        // Llamada al método obtenerPipas para obtener el resultado
        HashMap<String, Object> result = PipaDAO.obtenerPipas();

        // Verificamos que el resultado no sea null
        assertNotNull(result);

        // Verificamos que la clave KEY_ERROR esté en el resultado y sea false
        assertTrue(result.containsKey(Constantes.KEY_ERROR));
        assertFalse((Boolean) result.get(Constantes.KEY_ERROR));

        // Verificamos que la clave "pipas" esté en el resultado y sea una lista
        assertTrue(result.containsKey("pipas"));
        assertTrue(result.get("pipas") instanceof List);
    }
}
