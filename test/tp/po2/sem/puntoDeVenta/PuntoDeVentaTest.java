package tp.po2.sem.puntoDeVenta;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tp.po2.sem.ZonaDeEstacionamiento.ZonaDeEstacionamiento;
import tp.po2.sem.sistemaEstacionamiento.SistemaEstacionamiento;

public class PuntoDeVentaTest {

	private PuntoDeVenta puntoDeVenta;
	private SistemaEstacionamiento sistemaEstacionamientoMock;
	private ZonaDeEstacionamiento zonaDeEstacionamientoMock;
	private LocalDate fechaCompraMock;

	@BeforeEach
	public void setUp() {
		sistemaEstacionamientoMock = mock(SistemaEstacionamiento.class);
		zonaDeEstacionamientoMock = mock(ZonaDeEstacionamiento.class);
		puntoDeVenta = new PuntoDeVenta("PV001", sistemaEstacionamientoMock, zonaDeEstacionamientoMock);
		fechaCompraMock = LocalDate.of(2024, 7, 4);
	}

	@Test
	public void testRegistrarEstacionamientoCompraPuntual() throws Exception {
		// Configurar el comportamiento de sistemaEstacionamientoMock.puedeEstacionar()
		String patente = "ABC123";
		LocalTime horaInicio = LocalTime.now();
		Duration cantidadDeHoras = Duration.ofHours(2);

		// Configurado para cuando se llame a puedeEstacionar con los siguientes
		// argumentos,
		// no haga nada (do nothing).
		doNothing().when(sistemaEstacionamientoMock).puedeEstacionar(eq(patente), any(LocalTime.class),
				any(LocalTime.class));

		// Llamar al método que queremos probar
		puntoDeVenta.registrarEstacionamientoCompraPuntual(patente, fechaCompraMock, cantidadDeHoras);

		// Verificar que sistemaEstacionamientoMock.puedeEstacionar() fue llamado con
		// los argumentos esperados
		verify(sistemaEstacionamientoMock).puedeEstacionar(eq(patente), any(LocalTime.class), any(LocalTime.class));
		verify(sistemaEstacionamientoMock).solicitudDeEstacionamientoCompraPuntual(eq(patente),
				any(CompraPuntual.class));
	}

	@Test
	public void testCargarSaldoEnCelular() {
		// Arrange
		String numeroCelular = "123456789";
		double saldo = 50.0;

		// Act
		puntoDeVenta.cargarSaldoEnCelular(numeroCelular, saldo);

		// Assert
		verify(sistemaEstacionamientoMock).cargarCelular(eq(numeroCelular), eq(saldo));
		verify(sistemaEstacionamientoMock).registrarCompra(any(CompraRecargaCelular.class));
	}

	@Test
	public void testGetZona() {
		assertEquals(zonaDeEstacionamientoMock, puntoDeVenta.getZona());
	}

	@Test
	public void testSetZona() {
		ZonaDeEstacionamiento nuevaZona = mock(ZonaDeEstacionamiento.class);
		puntoDeVenta.setZona(nuevaZona);
		assertEquals(nuevaZona, puntoDeVenta.getZona());
	}

	@Test
	public void testGetIdentificadorPuntoDeVenta() {
		assertEquals("PV001", puntoDeVenta.getIdentificadorPuntoDeVenta());
	}

	@Test
	public void testSetIdentificadorPuntoDeVenta() {
		String nuevoIdentificador = "PV002";
		puntoDeVenta.setIdentificadorPuntoDeVenta(nuevoIdentificador);
		assertEquals(nuevoIdentificador, puntoDeVenta.getIdentificadorPuntoDeVenta());
	}

	@Test
	public void testGetSem() {
		assertEquals(sistemaEstacionamientoMock, puntoDeVenta.getSem());
	}

	@Test
	public void testSetSem() {
		SistemaEstacionamiento nuevoSistema = mock(SistemaEstacionamiento.class);
		puntoDeVenta.setSem(nuevoSistema);
		assertEquals(nuevoSistema, puntoDeVenta.getSem());
	}

}
