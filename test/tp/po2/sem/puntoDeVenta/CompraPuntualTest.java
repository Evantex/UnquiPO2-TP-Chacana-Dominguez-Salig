package tp.po2.sem.puntoDeVenta;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;


public class CompraPuntualTest {
	
	private CompraPuntual compraPuntualConParametros;
	private CompraPuntual compraPuntualSinParametros;
	private PuntoDeVenta puntoDeVentaMock;
	private Duration horasCompradasMock;
	private LocalDate fechaInicioCompraMock;
	private LocalDate fechaFinCompraMock;
	private LocalTime horaInicioEstacionamientoMock;
	
	@BeforeEach
	public void setUp() {
		horasCompradasMock = Duration.ofHours(2);
		puntoDeVentaMock = mock(PuntoDeVenta.class);
		fechaInicioCompraMock = mock(LocalDate.class);
		fechaFinCompraMock = mock(LocalDate.class);
		horaInicioEstacionamientoMock = mock(LocalTime.class);
		
		compraPuntualConParametros = new CompraPuntual(	puntoDeVentaMock,
														fechaInicioCompraMock, 
														horaInicioEstacionamientoMock, 
														horasCompradasMock); 
		compraPuntualSinParametros = new CompraPuntual();
	}
	
    @Test
    public void testConstructorSinParametros() {
        
        assertNotNull(compraPuntualSinParametros.getNumeroDeControl());
        assertNotNull(compraPuntualSinParametros.getFechaCompra());
        assertNull(compraPuntualSinParametros.getHorasCompradas());
    }

    @Test
    public void testConstructorConParametros() {
       
        assertEquals(puntoDeVentaMock, compraPuntualConParametros.getPuntoDeVenta());
        assertEquals(horasCompradasMock, compraPuntualConParametros.getHorasCompradas());
    }
    
    @Test
    public void testGetHorasCompradas() {
    	assertEquals(horasCompradasMock, compraPuntualConParametros.getHorasCompradas());
    }
    
    @Test
    public void testSetHorasCompradas() {
        
        Duration horasCompradas = Duration.ofHours(3);
        compraPuntualSinParametros.setHorasCompradas(horasCompradas);
        assertEquals(horasCompradas, compraPuntualSinParametros.getHorasCompradas());
    }

    @Test
    public void testEsCompraPuntual() {
        
        assertTrue(compraPuntualSinParametros.esCompraPuntual());
        assertFalse(compraPuntualSinParametros.esCompraRecargaCelular());
    }
    

    
    
    
    
    
    
}