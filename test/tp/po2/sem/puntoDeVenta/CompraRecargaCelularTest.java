package tp.po2.sem.puntoDeVenta;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;

public class CompraRecargaCelularTest {
	
	private CompraRecargaCelular compra;
	private PuntoDeVenta puntoDeVentaMock;
	private String numeroDeCelular;
	private double montoSaldo;
	
	@BeforeEach
	public void setUp() {
		numeroDeCelular = "123456789";
        montoSaldo = 100.0;
		puntoDeVentaMock = mock(PuntoDeVenta.class);
		compra = new CompraRecargaCelular(puntoDeVentaMock, numeroDeCelular, montoSaldo); 
	}
	
    @Test
    public void testConstructorConParametros() {
    	
        assertEquals(puntoDeVentaMock, compra.getPuntoDeVenta());
        assertEquals(numeroDeCelular, compra.getNumeroDecelular());
        assertEquals(montoSaldo, compra.getMontoSaldo(), 0.001);
    }
    
    @Test
    public void getPuntoDeVenta() {
    	assertEquals(puntoDeVentaMock, compra.getPuntoDeVenta());
    }
    
    @Test
    public void testSetPuntoDeVenta() {

        PuntoDeVenta otroPuntoDeVenta = mock(PuntoDeVenta.class);
        compra.setPuntoDeVenta(otroPuntoDeVenta);
        assertEquals(otroPuntoDeVenta, compra.getPuntoDeVenta());
    }
    
    @Test
    public void getMontoSaldo() {
    	assertEquals(montoSaldo, compra.getMontoSaldo());
    }
    
    @Test
    public void testSetMontoSaldo() {
   
        double nuevoMonto = 200.0;
        compra.setMontoSaldo(nuevoMonto);
        assertEquals(nuevoMonto, compra.getMontoSaldo(), 0.001);
    }
    
    @Test
    public void testGetNumeroDeCelular() {
    	
    	assertEquals(numeroDeCelular, compra.getNumeroDecelular());
    }
    
    @Test
    public void testSetNumeroDeCelular() {
        String nuevoNumero = "987654321";
        compra.setNumeroDecelular(nuevoNumero);
        assertEquals(nuevoNumero, compra.getNumeroDecelular());
    }

    @Test
    public void testEsCompraRecargaCelular() {
       
        assertFalse(compra.esCompraPuntual());
        assertTrue(compra.esCompraRecargaCelular());
    }
}
