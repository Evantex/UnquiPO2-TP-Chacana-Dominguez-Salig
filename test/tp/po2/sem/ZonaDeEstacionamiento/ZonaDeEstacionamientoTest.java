
package tp.po2.sem.ZonaDeEstacionamiento;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

import java.util.ArrayList;
import java.util.LinkedHashSet;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tp.po2.sem.estacionamiento.Estacionamiento;
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
	void testCuandoUnaZonaDeEstacionamientoNuevoAgregarPuntoDeVentaLaCantidadEs1() {
		zonaEstacionamientoSUT.agregarPuntoDeVenta(puntoDeVentaMock);

		assertEquals(zonaEstacionamientoSUT.cantidadDePuntosDeVenta(), 1);
	}

}
