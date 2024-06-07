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

import tp.po2.sem.estacionamiento.Estacionamiento;
import tp.po2.sem.sistemaEstacionamiento.SistemaEstacionamiento;

class PuntoDeVentaTest {
	PuntoDeVenta puntoDeVentaSUT;
	SistemaEstacionamiento sistemaEstacionamientoMock;
	Estacionamiento estacionamientoMock;
	CompraPuntual compraDeEstacionamientoMock;
	List<Estacionamiento> spyListaEstacionamientos;
	Set<Compra> spySetDeCompras;

	@BeforeEach
	public void setUp() {
		// Creamos el mock de Estacionamiento
		estacionamientoMock = mock(Estacionamiento.class);

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
		puntoDeVentaSUT = new PuntoDeVenta("identificador", sistemaEstacionamientoMock, spySetDeCompras);
	}

	@Test
	public void testCuandoUnPuntoDeVentaRegistrarUnEstacionamientoTiene1CompraEnSuSetDeCompras() {
		// Crear una duración de 2 horas y 30 minutos
		Duration duracion = Duration.ofHours(2).plusMinutes(30);
		// Ejecutamos el método que queremos probar
		puntoDeVentaSUT.registrarEstacionamiento("Patente", duracion);

		// Usamos ArgumentCaptor para capturar el argumento pasado al método add del
		// conjunto
		ArgumentCaptor<Compra> captor = ArgumentCaptor.forClass(Compra.class);

		// Verificamos que se haya llamado al método add del conjunto y capturamos el
		// argumento
		verify(spySetDeCompras).add(captor.capture());

		// Obtenemos el argumento capturado
		Compra capturedCompra = captor.getValue();

		// Verificamos que el argumento capturado es una instancia de CompraPuntual
		assertTrue(capturedCompra instanceof CompraPuntual);

		// Verificamos que el número de compras en el conjunto es 1
		assertEquals(puntoDeVentaSUT.getCantidadCompras(), 1);

		// Verificamos que se llamó al método size del conjunto
		verify(spySetDeCompras).size();
	}
}