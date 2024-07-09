package tp.po2.sem.estacionamiento;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tp.po2.sem.app.App;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EstacionamientoAppTest {

    private EstacionamientoApp estacionamientoApp;
    private App appMock;

    @BeforeEach
    public void setUp() {
        appMock = mock(App.class);
        estacionamientoApp = new EstacionamientoApp(appMock, "1234567890", "ABC123");
    }

    @Test
    public void testConstructor() {
        assertNotNull(estacionamientoApp.getInicioEstacionamiento());
        assertEquals("ABC123", estacionamientoApp.getPatente());
        assertEquals("1234567890", estacionamientoApp.getNroCelularApp());
        assertTrue(estacionamientoApp.estaVigente());
    }

    @Test
    public void testGetCostoEstacionamiento_SinFinalizar() {
        Exception exception = assertThrows(Exception.class, () -> {
            estacionamientoApp.getCostoEstacionamiento();
        });

        String expectedMessage = "Aún no ha finalizado el estacionamiento.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testGetCostoEstacionamiento_Finalizado() throws Exception {
        estacionamientoApp.setCostoEstacionamiento(100.0);
        estacionamientoApp.finalizarEstacionamiento();

        assertEquals(100.0, estacionamientoApp.getCostoEstacionamiento());
    }

    @Test
    public void testFinalizarEstacionamiento() throws Exception {
        estacionamientoApp.finalizarEstacionamiento();

        assertFalse(estacionamientoApp.estaVigente());
        assertNotNull(estacionamientoApp.getFinEstacionamiento());
        assertNotNull(estacionamientoApp.getDuracionEnHoras());
    }

    @Test
    public void testGetIdentificadorEstacionamiento() {
        assertEquals("1234567890", estacionamientoApp.getIdentificadorEstacionamiento());
    }

    @Test
    public void testEsEstacionamientoApp() {
        assertTrue(estacionamientoApp.esEstacionamientoApp());
        
    }
    
    @Test
    public void testEsEstacionamientoCompraPuntual() {
    	assertFalse(estacionamientoApp.esEstacionamientoCompraPuntual());
    }
    
    @Test
    public void testGetNroCelularApp() {
        assertEquals("1234567890", estacionamientoApp.getNroCelularApp());
    }

    @Test
    public void testGetAplicacion() {
        assertEquals(appMock, estacionamientoApp.getAplicacion());
    }

    @Test
    public void testSetAplicacion() {
        App newAppMock = mock(App.class);
        estacionamientoApp.setAplicacion(newAppMock);

        assertEquals(newAppMock, estacionamientoApp.getAplicacion());
    }

    @Test
    public void testSetNroCelularApp() {
        estacionamientoApp.setNroCelularApp("0987654321");

        assertEquals("0987654321", estacionamientoApp.getNroCelularApp());
    }
}
