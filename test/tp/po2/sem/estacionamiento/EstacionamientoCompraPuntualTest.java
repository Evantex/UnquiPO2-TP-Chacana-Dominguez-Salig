package tp.po2.sem.estacionamiento;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tp.po2.sem.puntoDeVenta.CompraPuntual;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.Duration;
import java.time.LocalTime;

public class EstacionamientoCompraPuntualTest {

    private EstacionamientoCompraPuntual estacionamientoCompraPuntual;
    private CompraPuntual compraMock;

    @BeforeEach
    public void setUp() {
        compraMock = mock(CompraPuntual.class);
        when(compraMock.getHoraInicio()).thenReturn(LocalTime.of(9, 0));
        when(compraMock.getHoraFin()).thenReturn(LocalTime.of(11, 0));
        when(compraMock.getHorasCompradas()).thenReturn(Duration.ofHours(2));

        estacionamientoCompraPuntual = new EstacionamientoCompraPuntual("ABC123", compraMock);
    }

    @Test
    public void testConstructor() throws Exception {
        assertEquals("ABC123", estacionamientoCompraPuntual.getPatente());
        assertEquals(compraMock, estacionamientoCompraPuntual.getCompraAsociada());
        assertEquals(LocalTime.of(9, 0), estacionamientoCompraPuntual.getInicioEstacionamiento());
        assertEquals(LocalTime.of(11, 0), estacionamientoCompraPuntual.getFinEstacionamiento());
        assertEquals(Duration.ofHours(2), estacionamientoCompraPuntual.getDuracionEnHoras());
        assertTrue(estacionamientoCompraPuntual.estaVigente());
    }

    @Test
    public void testGetIdentificadorEstacionamiento() {
        assertEquals("ABC123", estacionamientoCompraPuntual.getIdentificadorEstacionamiento());
    }

    @Test
    public void testEsEstacionamientoCompraPuntual() {
        assertTrue(estacionamientoCompraPuntual.esEstacionamientoCompraPuntual());
    }
    
    @Test
    public void testEsEstacionamientoApp() {
    	 assertFalse(estacionamientoCompraPuntual.esEstacionamientoApp());
    }
    
    @Test
    public void testFinalizarEstacionamiento() {
        estacionamientoCompraPuntual.finalizarEstacionamiento();

        assertFalse(estacionamientoCompraPuntual.estaVigente());
        assertNotNull(estacionamientoCompraPuntual.getFinEstacionamiento());
    }
}
