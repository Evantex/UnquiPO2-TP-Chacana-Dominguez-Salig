package tp.po2.sem.app;

import static org.junit.jupiter.api.Assertions.*;
import java.awt.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CelularTest {

    private Celular celular;

    @BeforeEach
    public void setUp() {
        
        celular = new Celular("1234567890", 100.0);
    }

    @Test
    public void testGetSaldo() {
        
        assertEquals(100.0, celular.getSaldo());
    }

    @Test
    public void testSetSaldo() {
        
        celular.setSaldo(200.0);
        assertEquals(200.0, celular.getSaldo());
    }

    @Test
    public void testGetNroCelular() {
        
        assertEquals("1234567890", celular.getNroCelular());
    }

    @Test
    public void testSetNroCelular() {
        
        celular.setNroCelular("0987654321");
        assertEquals("0987654321", celular.getNroCelular());
    }

    @Test
    public void testGetUbicacion() {
        
        Point ubicacion = new Point(10, 20);
        celular.setUbicacion(ubicacion);
        assertEquals(ubicacion, celular.getUbicacion());
    }

    @Test
    public void testSetUbicacion() {
        
        Point ubicacion = new Point(30, 40);
        celular.setUbicacion(ubicacion);
        assertEquals(ubicacion, celular.getUbicacion());
    }

    @Test
    public void testRecibirMensaje() {
        // Prueba para el método recibirMensaje
        // Actualmente, este método no hace nada, por lo que la prueba solo verifica que no se lance ninguna excepción
        assertDoesNotThrow(() -> celular.recibirMensaje("Mensaje de prueba"));
    }

    @Test
    public void testEstaDentroDeZonaEstacionamiento() {
        // Prueba para el método estaDentroDeZonaEstacionamiento
        // Actualmente, este método siempre devuelve true, por lo que la prueba verifica ese comportamiento
        assertTrue(celular.estaDentroDeZonaEstacionamiento());
    }

    @Test
    public void testRecibirRecargaDeSaldo() {
        
        celular.recibirRecargaDeSaldo(50.0);
        assertEquals(150.0, celular.getSaldo());
    }

    @Test
    public void testDisminuirSaldo() {
        
        celular.disminuirSaldo(30.0);
        assertEquals(70.0, celular.getSaldo());
    }
}

