
package tp.po2.sem.ZonaDeEstacionamiento;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.time.Duration;
import java.util.LinkedHashSet;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import tp.po2.sem.inspector.Inspector;
import tp.po2.sem.puntoDeVenta.Compra;
import tp.po2.sem.puntoDeVenta.CompraPuntual;
import tp.po2.sem.puntoDeVenta.PuntoDeVenta;

class ZonaDeEstacionamientoTest {

	ZonaDeEstacionamiento zonaEstacionamientoSUT;
	PuntoDeVenta puntoDeVentaMock;
	Inspector inspectorMock;
	Set<PuntoDeVenta> spySetDePuntosMock;

	@BeforeEach
	public void setUp() {
		puntoDeVentaMock = mock(PuntoDeVenta.class);
		inspectorMock = mock(Inspector.class);
		spySetDePuntosMock = spy(new LinkedHashSet<PuntoDeVenta>());
		zonaEstacionamientoSUT = new ZonaDeEstacionamiento("identificador", inspectorMock, spySetDePuntosMock);

	}

	@Test
	public void testConstructor() {

		ZonaDeEstacionamiento zona = new ZonaDeEstacionamiento("Zona1", inspectorMock, spySetDePuntosMock);

		assertEquals("Zona1", zona.getIdentificardorDeZona());
		assertEquals(inspectorMock, zona.getInspectorAsignado());
		assertEquals(spySetDePuntosMock, zona.getPuntosDeVenta());
	}

	public void testSettersAndGetters() {
		// instancia de la clase ZonaDeEstacionamiento
		ZonaDeEstacionamiento zonaPrueba = new ZonaDeEstacionamiento("Zona1", null, null);

		// Se establece nuevos valores usando los métodos setters
		zonaPrueba.setIdentificardorDeZona("NuevaZona");

		zonaPrueba.setInspectorAsignado(inspectorMock);

		zonaPrueba.setPuntosDeVenta(spySetDePuntosMock);

		// Verificacion de que los valores han sido establecidos correctamente
		assertEquals("NuevaZona", zonaPrueba.getIdentificardorDeZona());
		assertEquals(inspectorMock, zonaPrueba.getInspectorAsignado());
		assertEquals(spySetDePuntosMock, zonaPrueba.getPuntosDeVenta());
	}

	@Test
	void testCuandoUnaZonaDeEstacionamientoNuevoAgregarPuntoDeVentaLaCantidadEs1() {
		zonaEstacionamientoSUT.agregarPuntoDeVenta(puntoDeVentaMock);

		assertEquals(zonaEstacionamientoSUT.cantidadDePuntosDeVenta(), 1);
	}

	@Test
	public void testCuandoUnaZonaDeEstacionamientoAgregaUnPuntoDeVentaSeAniadeUnPuntoDeVenta() {

		// Ejecutamos el método que queremos probar
		zonaEstacionamientoSUT.agregarPuntoDeVenta(puntoDeVentaMock);

		// Usamos ArgumentCaptor para capturar el argumento pasado al método add del
		// conjunto
		ArgumentCaptor<PuntoDeVenta> captor = ArgumentCaptor.forClass(PuntoDeVenta.class);

		// Verificamos que se haya llamado al método add del conjunto y capturamos el
		// argumento
		verify(spySetDePuntosMock).add(captor.capture());

		// Obtenemos el argumento capturado
		PuntoDeVenta capturedPuntoDeVenta = captor.getValue();

		// Verificamos que el argumento capturado es una instancia de CompraPuntual
		assertTrue(capturedPuntoDeVenta instanceof PuntoDeVenta);

	}

	@Test
	void testCuandoUnaZonaDeEstacionamientoNuevaRemueveUnPuntoDeVentaLaCantidadEs0() {
		zonaEstacionamientoSUT.agregarPuntoDeVenta(puntoDeVentaMock);

		zonaEstacionamientoSUT.removerPuntoDeVenta(puntoDeVentaMock);

		assertEquals(zonaEstacionamientoSUT.cantidadDePuntosDeVenta(), 0);
	}

}
