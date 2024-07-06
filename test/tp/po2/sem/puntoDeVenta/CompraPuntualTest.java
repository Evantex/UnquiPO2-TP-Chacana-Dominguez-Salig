package tp.po2.sem.puntoDeVenta;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompraPuntualTest {

	private CompraPuntual compraPuntualConParametros;
	private CompraPuntual compraPuntualSinParametros;
	private PuntoDeVenta puntoDeVentaMock;
	private Duration horasCompradasMock;
	private LocalDate fechaInicioCompraMock;
	private LocalTime horaInicioEstacionamientoMock;
	private LocalTime horaFinEstacionamientoMock;

	@BeforeEach
	public void setUp() {
		horasCompradasMock = Duration.ofHours(2);
		puntoDeVentaMock = mock(PuntoDeVenta.class);
		fechaInicioCompraMock = LocalDate.of(2024, 7, 4);
		horaInicioEstacionamientoMock = LocalTime.of(9, 0);
		horaFinEstacionamientoMock = horaInicioEstacionamientoMock.plus(horasCompradasMock);

		compraPuntualConParametros = new CompraPuntual(puntoDeVentaMock, fechaInicioCompraMock,
				horaInicioEstacionamientoMock, horasCompradasMock);
		compraPuntualSinParametros = new CompraPuntual();
	}

	@Test
	public void testConstructorSinParametros() {
		assertNull(compraPuntualSinParametros.getHorasCompradas());
		assertNull(compraPuntualSinParametros.getHoraInicioEstacionamiento());
		assertNull(compraPuntualSinParametros.getHoraFinEstacionamiento());
		assertNull(compraPuntualSinParametros.getFechaCompra());
	}

	@Test
	public void testConstructorConParametros() {
		assertEquals(puntoDeVentaMock, compraPuntualConParametros.getPuntoDeVenta());
		assertEquals(horasCompradasMock, compraPuntualConParametros.getHorasCompradas());
		assertEquals(fechaInicioCompraMock, compraPuntualConParametros.getFechaCompra());
		assertEquals(horaInicioEstacionamientoMock, compraPuntualConParametros.getHoraInicioEstacionamiento());
		assertEquals(horaFinEstacionamientoMock, compraPuntualConParametros.getHoraFinEstacionamiento());
	}

	@Test
	public void testGetHorasCompradas() {
		assertEquals(horasCompradasMock, compraPuntualConParametros.getHorasCompradas());
	}

	@Test
	public void testSetHorasCompradas() {
		Duration horasCompradas = Duration.ofHours(3);
		compraPuntualSinParametros.setHorasCompradas(horasCompradas);
		assertEquals(horasCompradas, compraPuntualSinParametros.getHorasCompradas());
	}

	@Test
	public void testGetHoraInicioEstacionamiento() {
		assertEquals(horaInicioEstacionamientoMock, compraPuntualConParametros.getHoraInicioEstacionamiento());
	}

	@Test
	public void testSetHoraInicioEstacionamiento() {
		LocalTime newHoraInicio = LocalTime.of(10, 0);
		compraPuntualSinParametros.setHoraInicioEstacionamiento(newHoraInicio);
		assertEquals(newHoraInicio, compraPuntualSinParametros.getHoraInicioEstacionamiento());
	}

	@Test
	public void testGetHoraFinEstacionamiento() {
		assertEquals(horaFinEstacionamientoMock, compraPuntualConParametros.getHoraFinEstacionamiento());
	}

	@Test
	public void testSetHoraFinEstacionamiento() {
		LocalTime newHoraFin = LocalTime.of(11, 0);
		compraPuntualSinParametros.setHoraFinEstacionamiento(newHoraFin);
		assertEquals(newHoraFin, compraPuntualSinParametros.getHoraFinEstacionamiento());
	}

	@Test
	public void testEsCompraPuntual() {
		assertTrue(compraPuntualSinParametros.esCompraPuntual());
		assertFalse(compraPuntualSinParametros.esCompraRecargaCelular());
	}

	@Test
	public void testGetFechaCompra() {
		assertEquals(fechaInicioCompraMock, compraPuntualConParametros.getFechaCompra());
	}

	@Test
	public void testGetHoraFin() {
		assertEquals(horaFinEstacionamientoMock, compraPuntualConParametros.getHoraFin());
	}
	
}
