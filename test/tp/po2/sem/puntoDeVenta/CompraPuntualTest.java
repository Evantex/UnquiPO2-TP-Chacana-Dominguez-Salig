package tp.po2.sem.puntoDeVenta;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.time.Duration;

import org.junit.Test;

public class CompraPuntualTest {

	@Test
	public void testGettersAndSetters() {
		// Mock del punto de venta
		PuntoDeVenta puntoDeVenta = mock(PuntoDeVenta.class);

		// Crear instancia de CompraPuntual
		CompraPuntual compra = new CompraPuntual();

		// Setter y Getter para PuntoDeVenta
		compra.setPuntoDeVenta(puntoDeVenta);
		assertEquals(puntoDeVenta, compra.getPuntoDeVenta());

		// Setter y Getter para HorasCompradas
		Duration horasCompradas = Duration.ofHours(2);
		compra.setHorasCompradas(horasCompradas);
		assertEquals(horasCompradas, compra.getHorasCompradas());
	}
}