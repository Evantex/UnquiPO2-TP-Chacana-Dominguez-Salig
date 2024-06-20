package tp.po2.sem.puntoDeVenta;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.time.Duration;

public class CompraTest {
	
	private CompraPuntual compraSinParametros;
	private CompraPuntual compraConParametros;
	private PuntoDeVenta puntoDeVentaMock;
	
	@BeforeEach 
	public void setUp() {
		puntoDeVentaMock = mock(PuntoDeVenta.class);
		compraSinParametros = new CompraPuntual();
		compraConParametros = new CompraPuntual(puntoDeVentaMock, Duration.ofHours(2));
		
	}
	
    @Test
    public void testUnaCompraPuntualSeInicializaConUnNumeroDeControlYUnaFechaYHora() {
        
        assertNotNull(compraSinParametros.getNumeroDeControl());
        assertNotNull(compraSinParametros.getFechaHoraCompra());
    }

    @Test
    public void testUnaCompraPuntualSePuedeInicializarConUnPuntoDeVenta() {
       
    	PuntoDeVenta puntoDeVentaMock = mock(PuntoDeVenta.class);
        Compra compra = new CompraPuntual(puntoDeVentaMock, Duration.ofHours(2));
        assertEquals(puntoDeVentaMock, compra.getPuntoDeVenta());
        
    }

    @Test
    public void testGenerarNumeroDeControl() {
        Compra compra1 = new CompraPuntual();
        Compra compra2 = new CompraPuntual();
        assertNotEquals(compra1.getNumeroDeControl(), compra2.getNumeroDeControl());
    }

    @Test
    public void testGetPuntoDeVenta() {
        
        assertEquals(puntoDeVentaMock, compraConParametros.getPuntoDeVenta());
    }
    
    @Test
    public void testSetPuntoDeVenta() {
    	compraSinParametros.setPuntoDeVenta(puntoDeVentaMock);
    	assertEquals(puntoDeVentaMock, compraSinParametros.getPuntoDeVenta());
    }
    
    @Test 
    public void testGetFechaYHora() {
        
        assertNotNull(compraSinParametros.getFechaHoraCompra());
    }
    
}
