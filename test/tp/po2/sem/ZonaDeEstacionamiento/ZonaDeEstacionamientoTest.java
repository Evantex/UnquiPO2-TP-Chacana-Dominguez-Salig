
package tp.po2.sem.ZonaDeEstacionamiento;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tp.po2.sem.estacionamiento.Estacionamiento;
import tp.po2.sem.inspector.Inspector;
import tp.po2.sem.puntoDeVenta.PuntoDeVenta;

class ZonaDeEstacionamientoTest {

	ZonaDeEstacionamiento zonaEstacionamientoSUT;
	PuntoDeVenta puntoDeVentaMock;
	Inspector inspectorMock;
	List<PuntoDeVenta> listaDePuntosMock;

	@BeforeEach
	public void setUp() {
		puntoDeVentaMock = mock(PuntoDeVenta.class);
		inspectorMock = mock(Inspector.class);
		listaDePuntosMock = spy(new ArrayList<PuntoDeVenta>());
		zonaEstacionamientoSUT = new ZonaDeEstacionamiento("identificador", inspectorMock, listaDePuntosMock);

	}

	@Test
	void testCuandoUnaZonaDeEstacionamientoNuevoAgregarPuntoDeVentaLaCantidadEs1() {
		zonaEstacionamientoSUT.agregarPuntoDeVenta(puntoDeVentaMock);

		assertEquals(zonaEstacionamientoSUT.cantidadDePuntosDeVenta(), 1);
	}

}
