
package tp.po2.sem.ZonaDeEstacionamiento;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

import java.util.LinkedHashSet;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tp.po2.sem.inspector.Inspector;
import tp.po2.sem.puntoDeVenta.PuntoDeVenta;

class ZonaDeEstacionamientoTest {

	ZonaDeEstacionamiento zonaEstacionamientoSUT;
	PuntoDeVenta puntoDeVentaMock;
	Inspector inspectorMock;
	Set<PuntoDeVenta> setDePuntosMock;

	@BeforeEach
	public void setUp() {
		puntoDeVentaMock = mock(PuntoDeVenta.class);
		inspectorMock = mock(Inspector.class);
		setDePuntosMock = spy(new LinkedHashSet<PuntoDeVenta>());
		zonaEstacionamientoSUT = new ZonaDeEstacionamiento("identificador", inspectorMock, setDePuntosMock);

	}

	@Test
	public void testConstructor() {

		ZonaDeEstacionamiento zona = new ZonaDeEstacionamiento("Zona1", inspectorMock, setDePuntosMock);

		assertEquals("Zona1", zona.getIdentificardorDeZona());
		assertEquals(inspectorMock, zona.getInspectorAsignado());
		assertEquals(setDePuntosMock, zona.getPuntosDeVenta());
	}

	public void testSettersAndGetters() {
		// instancia de la clase ZonaDeEstacionamiento
		ZonaDeEstacionamiento zonaPrueba = new ZonaDeEstacionamiento("Zona1", null, null);

		// Se establece nuevos valores usando los métodos setters
		zonaPrueba.setIdentificardorDeZona("NuevaZona");

		zonaPrueba.setInspectorAsignado(inspectorMock);

		zonaPrueba.setPuntosDeVenta(setDePuntosMock);

		// Verificacion de que los valores han sido establecidos correctamente
		assertEquals("NuevaZona", zonaPrueba.getIdentificardorDeZona());
		assertEquals(inspectorMock, zonaPrueba.getInspectorAsignado());
		assertEquals(setDePuntosMock, zonaPrueba.getPuntosDeVenta());
	}

	@Test
	void testCuandoUnaZonaDeEstacionamientoNuevoAgregarPuntoDeVentaLaCantidadEs1() {
		zonaEstacionamientoSUT.agregarPuntoDeVenta(puntoDeVentaMock);

		assertEquals(zonaEstacionamientoSUT.cantidadDePuntosDeVenta(), 1);
	}

	@Test
	void testCuandoUnaZonaDeEstacionamientoNuevaRemueveUnPuntoDeVentaLaCantidadEs0() {
		zonaEstacionamientoSUT.agregarPuntoDeVenta(puntoDeVentaMock);

		zonaEstacionamientoSUT.removerPuntoDeVenta(puntoDeVentaMock);

		assertEquals(zonaEstacionamientoSUT.cantidadDePuntosDeVenta(), 0);
	}

}
