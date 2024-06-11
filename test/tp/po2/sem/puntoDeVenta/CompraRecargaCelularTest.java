package tp.po2.sem.puntoDeVenta;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;

public class CompraRecargaCelularTest {

    @Test
    public void testConstructor() {
        // Mock del punto de venta
        PuntoDeVenta puntoDeVenta = mock(PuntoDeVenta.class);
        String numeroDeCelular = "1234567890";
        double montoSaldo = 100.0;

        // Crear instancia de CompraRecargaCelular con parámetros
        CompraRecargaCelular compra = new CompraRecargaCelular(puntoDeVenta, numeroDeCelular, montoSaldo);

        // Verificar que los valores se hayan establecido correctamente
        assertEquals(puntoDeVenta, compra.getPuntoDeVenta());
        assertEquals(numeroDeCelular, compra.getNumeroDecelular());
        assertEquals(montoSaldo, compra.getMontoSaldo(), 0.001);
    }

    @Test
    public void testSettersAndGetters() {
        // Mock del punto de venta
        PuntoDeVenta puntoDeVenta = mock(PuntoDeVenta.class);
        PuntoDeVenta nuevoPuntoDeVenta = mock(PuntoDeVenta.class);
        String numeroDeCelular = "132";
        String nuevoNumeroDeCelular = "4444";
        double montoSaldo = 100.0;
        double nuevoMontoSaldo = 200.0;

        // Crear instancia de CompraRecargaCelular con parámetros
        CompraRecargaCelular compra = new CompraRecargaCelular(puntoDeVenta, numeroDeCelular, montoSaldo);

        // Verificar los getters
        assertEquals(puntoDeVenta, compra.getPuntoDeVenta());
        assertEquals(numeroDeCelular, compra.getNumeroDecelular());
        assertEquals(montoSaldo, compra.getMontoSaldo(), 0.001);

        // Usar setters para cambiar los valores
        compra.setPuntoDeVenta(nuevoPuntoDeVenta);
        compra.setNumeroDecelular(nuevoNumeroDeCelular);
        compra.setMontoSaldo(nuevoMontoSaldo);

        // Verificar los nuevos valores con los getters
        assertEquals(nuevoPuntoDeVenta, compra.getPuntoDeVenta());
        assertEquals(nuevoNumeroDeCelular, compra.getNumeroDecelular());
        assertEquals(nuevoMontoSaldo, compra.getMontoSaldo(), 0.001);
    }
}
