package tp.po2.sem.puntoDeVenta;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import tp.po2.sem.puntoDeVenta.CompraRecargaCelular;
import tp.po2.sem.puntoDeVenta.PuntoDeVenta;

public class CompraRecargaCelularTest {

	@Test
	public void testConstructor() {
		// Mock del punto de venta
		PuntoDeVenta puntoDeVenta = mock(PuntoDeVenta.class);
		String numeroDeCelular = "1234567890";
		int montoSaldo = 100;

		// Crear instancia de CompraRecargaCelular con parámetros
		CompraRecargaCelular compra = new CompraRecargaCelular(puntoDeVenta, numeroDeCelular, montoSaldo);

		// Verificar que los valores se hayan establecido correctamente
		assertEquals(puntoDeVenta, compra.getPuntoDeVenta());
		assertEquals(numeroDeCelular, compra.getNumeroDecelular());
		assertEquals(montoSaldo, compra.getMontoSaldo());
	}

	@Test
	public void testSettersAndGetters() {
		// Mock del punto de venta
		PuntoDeVenta puntoDeVenta = mock(PuntoDeVenta.class);
		PuntoDeVenta nuevoPuntoDeVenta = mock(PuntoDeVenta.class);
		String numeroDeCelular = "132";
		String nuevoNumeroDeCelular = "4444";
		int montoSaldo = 100;
		int nuevoMontoSaldo = 200;

		// Crear instancia de CompraRecargaCelular con parámetros
		CompraRecargaCelular compra = new CompraRecargaCelular(puntoDeVenta, numeroDeCelular, montoSaldo);

		// Verificar los getters
		assertEquals(puntoDeVenta, compra.getPuntoDeVenta());
		assertEquals(numeroDeCelular, compra.getNumeroDecelular());
		assertEquals(montoSaldo, compra.getMontoSaldo());

		// Usar setters para cambiar los valores
		compra.setPuntoDeVenta(nuevoPuntoDeVenta);
		compra.setNumeroDecelular(nuevoNumeroDeCelular);
		compra.setMontoSaldo(nuevoMontoSaldo);

		// Verificar los nuevos valores con los getters
		assertEquals(nuevoPuntoDeVenta, compra.getPuntoDeVenta());
		assertEquals(nuevoNumeroDeCelular, compra.getNumeroDecelular());
		assertEquals(nuevoMontoSaldo, compra.getMontoSaldo());
	}
}
