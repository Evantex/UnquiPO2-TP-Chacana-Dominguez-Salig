package tp.po2.sem.puntoDeVenta;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.mockito.ArgumentCaptor;
import org.mockito.Captor;

import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;

import org.junit.jupiter.api.BeforeEach;

import tp.po2.sem.ZonaDeEstacionamiento.ZonaDeEstacionamiento;
import tp.po2.sem.estacionamiento.Estacionamiento;
import tp.po2.sem.sistemaEstacionamiento.SistemaEstacionamiento;

class PuntoDeVentaTest {
	PuntoDeVenta puntoDeVentaSUT;
	SistemaEstacionamiento sistemaEstacionamientoMock;
	Estacionamiento estacionamientoMock;
	CompraPuntual compraDeEstacionamientoMock;
	ZonaDeEstacionamiento zonaMock;
	List<Estacionamiento> spyListaEstacionamientos;
	Set<Compra> spySetDeCompras;
	@Captor
	private ArgumentCaptor<CompraPuntual> compraPuntualCaptor;
	@Captor
	private ArgumentCaptor<CompraRecargaCelular> compraCelularCaptor;

	@BeforeEach
	public void setUp() {

		MockitoAnnotations.openMocks(this); // INICIALIZO LOS MOCK "CAPTOR"

		estacionamientoMock = mock(Estacionamiento.class);
		zonaMock = mock(ZonaDeEstacionamiento.class);
		compraDeEstacionamientoMock = mock(CompraPuntual.class);
		sistemaEstacionamientoMock = mock(SistemaEstacionamiento.class);
		spyListaEstacionamientos = spy(new ArrayList<Estacionamiento>());
		spySetDeCompras = spy(new LinkedHashSet<Compra>());
		puntoDeVentaSUT = new PuntoDeVenta("identificador", sistemaEstacionamientoMock, zonaMock);

		// Configurar horarios válidos
		when(sistemaEstacionamientoMock.getHoraLaboralInicio()).thenReturn(LocalTime.of(7, 0));
		when(sistemaEstacionamientoMock.getHoraLaboralFin()).thenReturn(LocalTime.of(20, 0));
	}

	// TEST DE GETTERS Y SETTERS

	@Test
	public void testGetZona() {
		ZonaDeEstacionamiento zonaEsperada = puntoDeVentaSUT.getZona();
		assertEquals(zonaMock, zonaEsperada);
	}

	@Test
	public void testSetZona() {
		ZonaDeEstacionamiento nuevaZona = mock(ZonaDeEstacionamiento.class);
		puntoDeVentaSUT.setZona(nuevaZona);
		assertEquals(nuevaZona, puntoDeVentaSUT.getZona());
	}

	@Test
	public void testGetIdentificadorPuntoDeVenta() {
		String identificador = puntoDeVentaSUT.getIdentificadorPuntoDeVenta();
		assertEquals("identificador", identificador);
	}

	@Test
	public void testSetIdentificadorPuntoDeVenta() {
		puntoDeVentaSUT.setIdentificadorPuntoDeVenta("PV2");
		assertEquals("PV2", puntoDeVentaSUT.getIdentificadorPuntoDeVenta());
	}

	@Test
	public void testGetSem() {
		SistemaEstacionamiento sem = puntoDeVentaSUT.getSem();
		assertEquals(sistemaEstacionamientoMock, sem);
	}

	@Test
	public void testSetSem() {
		SistemaEstacionamiento nuevoSem = mock(SistemaEstacionamiento.class);
		puntoDeVentaSUT.setSem(nuevoSem);
		assertEquals(nuevoSem, puntoDeVentaSUT.getSem());
	}

// TESTS LOGICA DE REGISTRAR COMPRAS

	@Test
	public void testRegistrarEstacionamientoCompraPuntual_Valido() throws Exception {
		Duration cantidadDeHoras = Duration.ofHours(2);
		when(sistemaEstacionamientoMock.esValidoRegistrarEstacionamiento(cantidadDeHoras)).thenReturn(true);

		puntoDeVentaSUT.registrarEstacionamientoCompraPuntual("ABC123", cantidadDeHoras);

		// Verificacion: se mandaron los mensajes al SEM
		verify(sistemaEstacionamientoMock).registrarEstacionamientoCompraPuntual(eq("ABC123"), eq(cantidadDeHoras),
				compraPuntualCaptor.capture());
		verify(sistemaEstacionamientoMock).registrarCompra(compraPuntualCaptor.capture());

		// Verificacion: se creó una instancia de CompraPuntual
		CompraPuntual compraPuntual = compraPuntualCaptor.getValue();
		assertNotNull(compraPuntual);
		assertEquals(cantidadDeHoras, compraPuntual.getHorasCompradas());
	}

	@Test
	public void testNoSePuedeRegistrarUnEstacionamientoEnUnHorarioNoValido() throws Exception {

		when(sistemaEstacionamientoMock.esHorarioLaboral()).thenReturn(false);

		Duration cantidadDeHoras = Duration.ofHours(2);

		Exception exception = assertThrows(Exception.class, () -> {
			puntoDeVentaSUT.registrarEstacionamientoCompraPuntual("Patente", cantidadDeHoras);
		});

		// Verificar el mensaje de la excepción
		assertEquals("No es un horario apto para procesar el estacionamiento", exception.getMessage());

		// Verificar que no se llamaron a los metodos esperados del mock
		verify(sistemaEstacionamientoMock, never()).registrarEstacionamientoCompraPuntual(anyString(),
				any(Duration.class), any(CompraPuntual.class));
		verify(sistemaEstacionamientoMock, never()).registrarCompra(any(CompraPuntual.class));
	}

	@Test
	public void testRegistrarUnaRecargaDeSaldoEnPuntoDeVenta() {
		// Datos de prueba
		String numeroCelular = "123456789";
		double saldo = 50.0;

		// Llamar al método bajo prueba
		puntoDeVentaSUT.cargarSaldoEnCelular(numeroCelular, saldo);

		// Verificar que se llamaron los métodos esperados en el mock
		verify(sistemaEstacionamientoMock).cargarCelular(numeroCelular, saldo);
		verify(sistemaEstacionamientoMock).registrarCompra(compraCelularCaptor.capture());

		// Verifico la instancia correcta de compra
		CompraRecargaCelular compraRecarga = compraCelularCaptor.getValue();
		assertNotNull(compraRecarga);
		assertEquals(saldo, compraRecarga.getMontoSaldo());
	}

	@Test
	public void registrarUnEstacionamientoAntesDeLaHoraInicio() {
		String patente = "123";
		LocalDate fechaCompra = LocalDate.now();
		LocalTime horaInicio = LocalTime.of(6, 0);
		LocalTime horaFin = LocalTime.of(10, 0);

		puntoDeVentaSUT.registrarEstacionamientoCompraPuntual(patente, fechaCompra, horaInicio, horaFin);
		
		
		
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
