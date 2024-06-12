package tp.po2.sem.puntoDeVenta;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.mockito.ArgumentCaptor;
import static org.mockito.Mockito.verify;

import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.mock;
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
		// Creamos el mock de Estacionamiento
		estacionamientoMock = mock(Estacionamiento.class);
		// Creamos el mock de Estacionamiento
		zonaMock = mock(ZonaDeEstacionamiento.class);

		// Creamos el mock de CompraPuntual
		compraDeEstacionamientoMock = mock(CompraPuntual.class);

		// Creamos el mock de SistemaEstacionamiento
		sistemaEstacionamientoMock = mock(SistemaEstacionamiento.class);

		// Creamos un spy de la lista de estacionamientos
		spyListaEstacionamientos = spy(new ArrayList<Estacionamiento>());

		// Creamos un spy del conjunto de compras
		spySetDeCompras = spy(new LinkedHashSet<Compra>());

		// Creamos la instancia de PuntoDeVenta con el SistemaEstacionamiento mockeado y
		// el conjunto de compras espiado
		puntoDeVentaSUT = new PuntoDeVenta("identificador", sistemaEstacionamientoMock, spySetDeCompras, zonaMock);
	}

	@Test
	public void testCuandoUnPuntoDeVentaRegistrarUnEstacionamientoTiene1CompraEnSuSetDeCompras() {
		// Crear una duración de 2 horas y 30 minutos
		Duration duracion = Duration.ofHours(2).plusMinutes(30);
		// Ejecutamos el método que queremos probar
		puntoDeVentaSUT.registrarEstacionamiento("Patente", duracion);

		assertEquals(puntoDeVentaSUT.getCantidadCompras(), 1);
	}

	@Test
	public void testCuandoUnPuntoDeVentaRegistraUnEstacionamientoDeCompraPuntualSeAniadeUnaCompraPuntualAlPuntoDeVenta() {

		Duration duracion = Duration.ofHours(2).plusMinutes(30);
		// Ejecutamos el método que queremos probar
		puntoDeVentaSUT.registrarEstacionamiento("Patente", duracion);

		// Usamos ArgumentCaptor para capturar el argumento pasado al método add del
		// conjunto
		ArgumentCaptor<CompraPuntual> captor = ArgumentCaptor.forClass(CompraPuntual.class);

		// Verificamos que se haya llamado al método add del conjunto y capturamos el
		// argumento
		verify(spySetDeCompras).add(captor.capture());

		// Obtenemos el argumento capturado
		Compra capturedCompra = captor.getValue();

		// Verificamos que el argumento capturado es una instancia de CompraPuntual
		assertTrue(capturedCompra instanceof CompraPuntual);

	}

	@Test
	public void testCuandoUnPuntoDeVentaRegistrarUnaRecargaTiene1CompraEnSuSetDeCompras() {

		// Ejecutamos el método que queremos probar
		puntoDeVentaSUT.cargarSaldoEnCelular("1140414243", 100);

		assertEquals(puntoDeVentaSUT.getCantidadCompras(), 1);
	}

	@Test
	public void testCuandoUnPuntoDeVentaRegistraUnaRecargaSeAniadeUnaCompraRecargaAlPuntoDeVenta() {

		// Ejecutamos el método que queremos probar
		puntoDeVentaSUT.cargarSaldoEnCelular("1140414243", 100);

		// Usamos ArgumentCaptor para capturar el argumento pasado al método add del
		// conjunto
		ArgumentCaptor<CompraRecargaCelular> captor = ArgumentCaptor.forClass(CompraRecargaCelular.class);

		// Verificamos que se haya llamado al método add del conjunto y capturamos el
		// argumento
		verify(spySetDeCompras).add(captor.capture());

		// Obtenemos el argumento capturado
		Compra capturedCompra = captor.getValue();

		// Verificamos que el argumento capturado es una instancia de CompraPuntual
		assertTrue(capturedCompra instanceof CompraRecargaCelular);

	}

	@Test
	public void testCuandoUnPuntoDeVentaRegistrarUnEstacionamientoYUnaCompraRecargaCelularTiene2CompraEnSuSetDeCompras() {
		// Crear una duración de 2 horas y 30 minutos
		Duration duracion = Duration.ofHours(2).plusMinutes(30);
		// Ejecutamos el método que queremos probar
		puntoDeVentaSUT.registrarEstacionamiento("Patente", duracion);
		// Ejecutamos el método que queremos probar
		puntoDeVentaSUT.cargarSaldoEnCelular("1140414243", 100);

		assertEquals(puntoDeVentaSUT.getCantidadCompras(), 2);
	}

	@Test
	public void testCuandoUnPuntoDeVentaRegistraUnaRecargaYUnaCompraPuntialSeAniadeUnaCompraASuSetompras() {

		// Ejecutamos el método que queremos probar
		puntoDeVentaSUT.cargarSaldoEnCelular("1140414243", 100);

		// Usamos ArgumentCaptor para capturar el argumento pasado al método add del
		// conjunto
		ArgumentCaptor<CompraRecargaCelular> captorCelular = ArgumentCaptor.forClass(CompraRecargaCelular.class);

		// Verificamos que se haya llamado al método add del conjunto y capturamos el
		// argumento
		verify(spySetDeCompras).add(captorCelular.capture());

		// Obtenemos el argumento capturado
		Compra capturedCompraRecargaCelular = captorCelular.getValue();

		// Verificamos que el argumento capturado es una instancia de CompraPuntual
		assertTrue(capturedCompraRecargaCelular instanceof Compra);

		Duration duracion = Duration.ofHours(2).plusMinutes(30);
		// Ejecutamos el método que queremos probar
		puntoDeVentaSUT.registrarEstacionamiento("Patente", duracion);

		// Usamos ArgumentCaptor para capturar el argumento pasado al método add del
		// conjunto
		ArgumentCaptor<CompraPuntual> captorCompraPuntual = ArgumentCaptor.forClass(CompraPuntual.class);

		// Verificamos que se haya llamado al método add del conjunto y capturamos el
		// argumento
		verify(spySetDeCompras).add(captorCompraPuntual.capture());

		// Obtenemos el argumento capturado
		Compra capturedCompraPuntaul = captorCompraPuntual.getValue();

		// Verificamos que el argumento capturado es una instancia de Compra
		assertTrue(capturedCompraPuntaul instanceof Compra);

	}

}