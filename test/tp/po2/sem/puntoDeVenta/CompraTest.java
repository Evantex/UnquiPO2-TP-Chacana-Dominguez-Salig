package tp.po2.sem.puntoDeVenta;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.time.LocalDateTime;

import org.junit.Test;

import tp.po2.sem.puntoDeVenta.Compra;
import tp.po2.sem.puntoDeVenta.PuntoDeVenta;

public class CompraTest {
	
	   @Test
	    public void testGettersAndSetters() {
	        // Mock del punto de venta
	        PuntoDeVenta puntoDeVenta = mock(PuntoDeVenta.class);

	        // Crear instancia de Compra
	        Compra compra = new Compra() {};

	        // Setter y Getter para PuntoDeVenta
	        compra.setPuntoDeVenta(puntoDeVenta);
	        assertEquals(puntoDeVenta, compra.getPuntoDeVenta());

	        // Setter y Getter para FechaHoraCompra
	        // No se puede cambiar directamente la fecha y hora de compra, ya que se establece automáticamente al crear la instancia
	        // Por lo tanto, no hay un test directo para el setter de FechaHoraCompra
	    }

    @Test
    public void testConstructorSinParametros() {
        // Mock del punto de venta
        PuntoDeVenta puntoDeVenta = mock(PuntoDeVenta.class);

        // Crear instancia de Compra sin parámetros
        Compra compra = new Compra() {};

        // Verificar que el número de control no sea nulo
        assertEquals(false, compra.getNumeroDeControl().isEmpty());

        // Verificar que la fecha y hora de la compra sea válida
        LocalDateTime fechaHoraActual = LocalDateTime.now();
        LocalDateTime fechaHoraCompra = compra.getFechaHoraCompra();
        assertEquals(fechaHoraActual.getDayOfMonth(), fechaHoraCompra.getDayOfMonth());
        assertEquals(fechaHoraActual.getMonth(), fechaHoraCompra.getMonth());
        assertEquals(fechaHoraActual.getYear(), fechaHoraCompra.getYear());
    }

    @Test
    public void testConstructorConParametros() {
        // Mock del punto de venta
        PuntoDeVenta puntoDeVenta = mock(PuntoDeVenta.class);

        // Crear instancia de Compra con parámetros
        Compra compra = new Compra(puntoDeVenta) {};

        // Verificar que el número de control no sea nulo
        assertEquals(false, compra.getNumeroDeControl().isEmpty());

        // Verificar que la fecha y hora de la compra sea válida
        LocalDateTime fechaHoraActual = LocalDateTime.now();
        LocalDateTime fechaHoraCompra = compra.getFechaHoraCompra();
        assertEquals(fechaHoraActual.getDayOfMonth(), fechaHoraCompra.getDayOfMonth());
        assertEquals(fechaHoraActual.getMonth(), fechaHoraCompra.getMonth());
        assertEquals(fechaHoraActual.getYear(), fechaHoraCompra.getYear());

        // Verificar que el punto de venta sea el esperado
        assertEquals(puntoDeVenta, compra.getPuntoDeVenta());
    }
}
