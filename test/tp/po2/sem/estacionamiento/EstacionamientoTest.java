package tp.po2.sem.estacionamiento;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tp.po2.sem.app.App;

class EstacionamientoTest {

    private EstacionamientoApp estacionamientoApp;
    private App appMock;

    @BeforeEach
    public void setUp() {
        appMock = mock(App.class);
        estacionamientoApp = new EstacionamientoApp(appMock, "1234567890", "ABC123");
    }
	
	@Test
	public void testEstaVigente() {
	    assertTrue(estacionamientoApp.estaVigente());
	    estacionamientoApp.finalizarEstacionamiento();
	    assertFalse(estacionamientoApp.estaVigente());
	}

	@Test
	public void testFinalizarEstacionamiento() {
	    estacionamientoApp.finalizarEstacionamiento();
	    assertNotNull(estacionamientoApp.getFinEstacionamiento());
	    assertFalse(estacionamientoApp.estaVigente());
	}

	@Test
	public void testGetInicioEstacionamiento() {
	    assertNotNull(estacionamientoApp.getInicioEstacionamiento());
	}

	@Test
	public void testGetDuracionEnHoras() throws Exception {
	    estacionamientoApp.finalizarEstacionamiento();
	    assertNotNull(estacionamientoApp.getDuracionEnHoras());
	}

	@Test
	public void testGetPatente() {
	    assertEquals("ABC123", estacionamientoApp.getPatente());
	}

	@Test
	public void testGetCostoEstacionamiento() throws Exception {
	    estacionamientoApp.finalizarEstacionamiento();
	    estacionamientoApp.setCostoEstacionamiento(100.0);
	    assertEquals(100.0, estacionamientoApp.getCostoEstacionamiento());
	}

	@Test
	public void testSetCostoEstacionamiento() throws Exception {
		estacionamientoApp.finalizarEstacionamiento();
	    estacionamientoApp.setCostoEstacionamiento(100.0);
	    assertEquals(100.0, estacionamientoApp.getCostoEstacionamiento());
	}

	@Test
	public void testGetFinEstacionamiento() {
	    estacionamientoApp.finalizarEstacionamiento();
	    assertNotNull(estacionamientoApp.getFinEstacionamiento());
	}
}