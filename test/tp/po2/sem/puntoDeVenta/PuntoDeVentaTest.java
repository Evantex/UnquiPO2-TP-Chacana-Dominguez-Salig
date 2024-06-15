package tp.po2.sem.puntoDeVenta;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.mockito.ArgumentCaptor;
import org.mockito.MockedStatic;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

	@BeforeEach
	public void setUp() {

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

	@Test
	public void testRegistrarEstacionamientoCompraPuntual_Valido() {
		// Configurar el comportamiento esperado del mock
		Duration cantidadDeHoras = Duration.ofHours(2);
		when(sistemaEstacionamientoMock.esValidoRegistrarEstacionamiento(cantidadDeHoras)).thenReturn(true);

		// Llamar al método bajo prueba
		puntoDeVentaSUT.registrarEstacionamientoCompraPuntual("ABC123", cantidadDeHoras);

		// Verificar que se llamaron los métodos esperados en el mock
		verify(sistemaEstacionamientoMock).registrarEstacionamientoCompraPuntual(eq("ABC123"), eq(cantidadDeHoras),
				any(CompraPuntual.class));

		verify(sistemaEstacionamientoMock).registrarCompra(any(CompraPuntual.class));
	}

	@Test
	public void testRegistrarEstacionamientoCompraPuntual_NoValido() {
		// Configurar el comportamiento esperado del mock
		Duration cantidadDeHoras = Duration.ofHours(1);
		when(sistemaEstacionamientoMock.esValidoRegistrarEstacionamiento(cantidadDeHoras)).thenReturn(false);

		// Llamar al método bajo prueba
		puntoDeVentaSUT.registrarEstacionamientoCompraPuntual("XYZ456", cantidadDeHoras);

		// Verificar que no se llamaron los métodos de registro en el mock
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
        verify(sistemaEstacionamientoMock).registrarCompra(any(CompraRecargaCelular.class));
		}
	}
	/*
	 * @Test public void
	 * testCuandoUnPuntoDeVentaRegistrarUnEstacionamientoTiene1CompraEnSuSetDeCompras
	 * () {
	 * 
	 * // Crear una duración de 2 horas y 30 minutos Duration duracion =
	 * Duration.ofHours(2).plusMinutes(30);
	 * 
	 * when(zonaMock.getIdentificardorDeZona()).thenReturn("Zona1");
	 * 
	 * // Mockear LocalTime.now() para que devuelva una hora dentro del horario
	 * laboral LocalTime mockedHoraActual = LocalTime.of(10, 0); try
	 * (MockedStatic<LocalTime> mockedLocalTime = mockStatic(LocalTime.class)) {
	 * mockedLocalTime.when(LocalTime::now).thenReturn(mockedHoraActual);
	 * 
	 * //Excercise puntoDeVentaSUT.registrarEstacionamiento("Patente", duracion);
	 * 
	 * }
	 * 
	 * // Verificamos que se haya agregado una compra assertEquals(1,
	 * puntoDeVentaSUT.getCantidadCompras()); }
	 * 
	 * 
	 * @Test public void
	 * testCuandoUnPuntoDeVentaRegistraUnEstacionamientoDeCompraPuntualSeAniadeUnaCompraPuntualAlPuntoDeVenta
	 * () {
	 * 
	 * Duration duracion = Duration.ofHours(2).plusMinutes(30); // Ejecutamos el
	 * método que queremos probar
	 * puntoDeVentaSUT.registrarEstacionamiento("Patente", duracion);
	 * 
	 * // Usamos ArgumentCaptor para capturar el argumento pasado al método add del
	 * // conjunto ArgumentCaptor<CompraPuntual> captor =
	 * ArgumentCaptor.forClass(CompraPuntual.class);
	 * 
	 * // Verificamos que se haya llamado al método add del conjunto y capturamos el
	 * // argumento verify(spySetDeCompras).add(captor.capture());
	 * 
	 * // Obtenemos el argumento capturado Compra capturedCompra =
	 * captor.getValue();
	 * 
	 * // Verificamos que el argumento capturado es una instancia de CompraPuntual
	 * assertTrue(capturedCompra instanceof CompraPuntual);
	 * 
	 * }
	 * 
	 * @Test public void
	 * testCuandoUnPuntoDeVentaRegistrarUnaRecargaTiene1CompraEnSuSetDeCompras() {
	 * 
	 * // Ejecutamos el método que queremos probar
	 * puntoDeVentaSUT.cargarSaldoEnCelular("1140414243", 100);
	 * 
	 * assertEquals(puntoDeVentaSUT.getCantidadCompras(), 1); }
	 * 
	 * @Test public void
	 * testCuandoUnPuntoDeVentaRegistraUnaRecargaSeAniadeUnaCompraRecargaAlPuntoDeVenta
	 * () {
	 * 
	 * // Ejecutamos el método que queremos probar
	 * puntoDeVentaSUT.cargarSaldoEnCelular("1140414243", 100);
	 * 
	 * // Usamos ArgumentCaptor para capturar el argumento pasado al método add del
	 * // conjunto ArgumentCaptor<CompraRecargaCelular> captor =
	 * ArgumentCaptor.forClass(CompraRecargaCelular.class);
	 * 
	 * // Verificamos que se haya llamado al método add del conjunto y capturamos el
	 * // argumento verify(spySetDeCompras).add(captor.capture());
	 * 
	 * // Obtenemos el argumento capturado Compra capturedCompra =
	 * captor.getValue();
	 * 
	 * // Verificamos que el argumento capturado es una instancia de CompraPuntual
	 * assertTrue(capturedCompra instanceof CompraRecargaCelular);
	 * 
	 * }
	 * 
	 * @Test public void
	 * testCuandoUnPuntoDeVentaRegistrarUnEstacionamientoYUnaCompraRecargaCelularTiene2CompraEnSuSetDeCompras
	 * () { // Crear una duración de 2 horas y 30 minutos Duration duracion =
	 * Duration.ofHours(2).plusMinutes(30); // Ejecutamos el método que queremos
	 * probar puntoDeVentaSUT.registrarEstacionamiento("Patente", duracion); //
	 * Ejecutamos el método que queremos probar
	 * puntoDeVentaSUT.cargarSaldoEnCelular("1140414243", 100);
	 * 
	 * assertEquals(puntoDeVentaSUT.getCantidadCompras(), 2); }
	 * 
	 * @Test public void
	 * testCuandoUnPuntoDeVentaRegistraUnaRecargaYUnaCompraPuntialSeAniadeUnaCompraASuSetompras
	 * () {
	 * 
	 * // Ejecutamos el método que queremos probar
	 * puntoDeVentaSUT.cargarSaldoEnCelular("1140414243", 100);
	 * 
	 * // Usamos ArgumentCaptor para capturar el argumento pasado al método add del
	 * // conjunto ArgumentCaptor<CompraRecargaCelular> captorCelular =
	 * ArgumentCaptor.forClass(CompraRecargaCelular.class);
	 * 
	 * // Verificamos que se haya llamado al método add del conjunto y capturamos el
	 * // argumento verify(spySetDeCompras).add(captorCelular.capture());
	 * 
	 * // Obtenemos el argumento capturado Compra capturedCompraRecargaCelular =
	 * captorCelular.getValue();
	 * 
	 * // Verificamos que el argumento capturado es una instancia de CompraPuntual
	 * assertTrue(capturedCompraRecargaCelular instanceof Compra);
	 * 
	 * Duration duracion = Duration.ofHours(2).plusMinutes(30); // Ejecutamos el
	 * método que queremos probar
	 * puntoDeVentaSUT.registrarEstacionamiento("Patente", duracion);
	 * 
	 * // Usamos ArgumentCaptor para capturar el argumento pasado al método add del
	 * // conjunto ArgumentCaptor<CompraPuntual> captorCompraPuntual =
	 * ArgumentCaptor.forClass(CompraPuntual.class);
	 * 
	 * // Verificamos que se haya llamado al método add del conjunto y capturamos el
	 * // argumento verify(spySetDeCompras).add(captorCompraPuntual.capture());
	 * 
	 * // Obtenemos el argumento capturado Compra capturedCompraPuntaul =
	 * captorCompraPuntual.getValue();
	 * 
	 * // Verificamos que el argumento capturado es una instancia de Compra
	 * assertTrue(capturedCompraPuntaul instanceof Compra);
	 * 
	 * }
	 */

