
package tp.po2.sem.ZonaDeEstacionamiento;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.util.LinkedHashSet;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import tp.po2.sem.inspector.Inspector;

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
	
	@Test
	public void testGetIdentificardorDeZona() {
		assertEquals("identificador", zonaEstacionamientoSUT.getIdentificardorDeZona());
	}
	
	@Test
	public void testSetIdentificardorDeZona() {
		
		String nuevoIdentificador = "identificardorDeZona";
		zonaEstacionamientoSUT.setIdentificardorDeZona(nuevoIdentificador);
		assertEquals("identificardorDeZona", zonaEstacionamientoSUT.getIdentificardorDeZona());
		
	}
	
	@Test
	public void testGetInspectorAsignado() {
		assertEquals(inspectorMock, zonaEstacionamientoSUT.getInspectorAsignado());
	}
	
	@Test
	public void testSetInspectorAsignado() {
		Inspector nuevoInspector = mock(Inspector.class);
		zonaEstacionamientoSUT.setInspectorAsignado(nuevoInspector);
		assertEquals(nuevoInspector, zonaEstacionamientoSUT.getInspectorAsignado());
	}
	
	@Test
	public void testGetPuntosDeVenta() {
		assertEquals(spySetDePuntosMock, zonaEstacionamientoSUT.getPuntosDeVenta());
	}

	public void setPuntosDeVenta(Set<PuntoDeVenta> puntosDeVenta) {
		LinkedHashSet<PuntoDeVenta> nuevosPuntosDeVenta = spy(new LinkedHashSet<PuntoDeVenta>());
		zonaEstacionamientoSUT.setPuntosDeVenta(nuevosPuntosDeVenta);
		assertEquals(nuevosPuntosDeVenta, zonaEstacionamientoSUT.getPuntosDeVenta());
	}

	@Test
	public void testCuandoUnaZonaDeEstacionamientoAgregaUnPuntoDeVentaSeAniadeUnPuntoDeVenta() {
		
		PuntoDeVenta nuevoPuntoDeVenta = mock(PuntoDeVenta.class);
		// Ejecutamos el método que queremos probar
		zonaEstacionamientoSUT.agregarPuntoDeVenta(nuevoPuntoDeVenta);

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
	public void testCuandoUnaZonaDeEstacionamientoAgregaUnPuntoDeVentaLaCantidadDePuntosDeVentaEsDe1() {
		
		PuntoDeVenta nuevoPuntoDeVenta = mock(PuntoDeVenta.class);
		
		zonaEstacionamientoSUT.agregarPuntoDeVenta(nuevoPuntoDeVenta);
		
		int cantidadDePuntosDeVenta = zonaEstacionamientoSUT.cantidadDePuntosDeVenta();
		
		assertEquals(cantidadDePuntosDeVenta, 1);
	}

	@Test
	void testCuandoUnaZonaDeEstacionamientoNuevaRemueveUnPuntoDeVentaLaCantidadEs0() {
		zonaEstacionamientoSUT.agregarPuntoDeVenta(puntoDeVentaMock);

		zonaEstacionamientoSUT.removerPuntoDeVenta(puntoDeVentaMock);
		
		int cantidadDePuntosDeVenta = zonaEstacionamientoSUT.cantidadDePuntosDeVenta();
		
		assertEquals(cantidadDePuntosDeVenta, 0);
	}

}
