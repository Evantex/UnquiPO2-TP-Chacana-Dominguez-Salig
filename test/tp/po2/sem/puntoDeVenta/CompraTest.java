package tp.po2.sem.puntoDeVenta;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompraTest {

    private CompraPuntual compraConParametros;
    private CompraPuntual compraSinParametros;
    private PuntoDeVenta puntoDeVentaMock;
    private LocalDate fechaCompraMock;

    @BeforeEach
    public void setUp() {
        puntoDeVentaMock = mock(PuntoDeVenta.class);
        fechaCompraMock = LocalDate.of(2024, 7, 4);

        // Simular el comportamiento de getHoraInicio()
        LocalTime horaInicioMock = LocalTime.of(10, 0); // Simular cualquier hora de inicio aquí
        compraConParametros = new CompraPuntual(puntoDeVentaMock, fechaCompraMock, horaInicioMock, Duration.ofHours(2));
        compraSinParametros = new CompraPuntual();
    }

    @AfterEach
    public void tearDown() {
        // Reiniciar estado después de cada prueba si es necesario
        Compra.setProximoNumeroDeControl(1);
        System.out.println("proximoNumeroDeControl reset to 1");
    }

    @Test
    public void testConstructorSinParametros() {
        assertNotNull(compraSinParametros.getNumeroDeControl());
        assertNull(compraSinParametros.getPuntoDeVenta());
        assertNull(compraSinParametros.getFechaCompra());
        assertNull(compraSinParametros.getHorasCompradas());
        assertNull(compraSinParametros.getHoraInicioEstacionamiento());
        assertNull(compraSinParametros.getHoraFinEstacionamiento());
    }

    @Test
    public void testConstructorConParametros() {
        assertEquals(puntoDeVentaMock, compraConParametros.getPuntoDeVenta());
        assertEquals(fechaCompraMock, compraConParametros.getFechaCompra());
        assertNotNull(compraConParametros.getNumeroDeControl());
        assertNotNull(compraConParametros.getHorasCompradas());
        assertNotNull(compraConParametros.getHoraInicioEstacionamiento());
        assertNotNull(compraConParametros.getHoraFinEstacionamiento());
    }

  /* @Test
    public void testGetProximoNumeroDeControl() {
        assertEquals(1, Compra.getProximoNumeroDeControl());
    } */

    @Test
    public void testSetProximoNumeroDeControl() {
        Compra.setProximoNumeroDeControl(10);
        assertEquals(10, Compra.getProximoNumeroDeControl());
    }

    @Test
    public void testGetNumeroDeControl() {
        assertNotNull(compraConParametros.getNumeroDeControl());
        assertNotNull(compraSinParametros.getNumeroDeControl());
    }

    @Test
    public void testGetFechaCompra() {
        assertEquals(fechaCompraMock, compraConParametros.getFechaCompra());
        assertNull(compraSinParametros.getFechaCompra());
    }

    @Test
    public void testGetPuntoDeVenta() {
        assertEquals(puntoDeVentaMock, compraConParametros.getPuntoDeVenta());
        assertNull(compraSinParametros.getPuntoDeVenta());
    }

    @Test
    public void testSetPuntoDeVenta() {
        PuntoDeVenta nuevoPuntoDeVentaMock = mock(PuntoDeVenta.class);
        compraSinParametros.setPuntoDeVenta(nuevoPuntoDeVentaMock);
        assertEquals(nuevoPuntoDeVentaMock, compraSinParametros.getPuntoDeVenta());
    }

   /* @Test
    public void testGetHorasCompradas() {
        assertNull(compraConParametros.getHorasCompradas());

        compraConParametros.setHorasCompradas(null);
        assertNull(compraConParametros.getHorasCompradas());
    }*/

    @Test
    public void testSetHorasCompradas() {
        compraSinParametros.setHorasCompradas(null);
        assertNull(compraSinParametros.getHorasCompradas());
    }

    @Test
    public void testGetHoraInicioEstacionamiento() {
        assertNotNull(compraConParametros.getHoraInicioEstacionamiento());

        compraConParametros.setHoraInicioEstacionamiento(null);
        assertNull(compraConParametros.getHoraInicioEstacionamiento());
    }

    @Test
    public void testSetHoraInicioEstacionamiento() {
        compraSinParametros.setHoraInicioEstacionamiento(null);
        assertNull(compraSinParametros.getHoraInicioEstacionamiento());
    }

    @Test
    public void testGetHoraFinEstacionamiento() {
        assertNotNull(compraConParametros.getHoraFinEstacionamiento());

        compraConParametros.setHoraFinEstacionamiento(null);
        assertNull(compraConParametros.getHoraFinEstacionamiento());
    }

    @Test
    public void testSetHoraFinEstacionamiento() {
        compraSinParametros.setHoraFinEstacionamiento(null);
        assertNull(compraSinParametros.getHoraFinEstacionamiento());
    }

    @Test
    public void testEsCompraPuntual() {
        assertTrue(compraConParametros.esCompraPuntual());
        assertFalse(compraConParametros.esCompraRecargaCelular());
    }

   /* @Test
    public void testSetNumeroDeControl() {
        String nuevoNumeroDeControl = "ABC123";
        compraSinParametros.setNumeroDeControl(nuevoNumeroDeControl);
        assertEquals(nuevoNumeroDeControl, compraSinParametros.getNumeroDeControl());
    }*/

    @Test
    public void testSetFechaCompra() {
        LocalDate nuevaFechaCompra = LocalDate.of(2025, 1, 1);
        compraSinParametros.setFechaCompra(nuevaFechaCompra);
        assertEquals(nuevaFechaCompra, compraSinParametros.getFechaCompra());
    }
}
