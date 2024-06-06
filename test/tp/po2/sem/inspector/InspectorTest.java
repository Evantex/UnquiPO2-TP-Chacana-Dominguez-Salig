package tp.po2.sem.inspector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tp.po2.sem.ZonaDeEstacionamiento.ZonaDeEstacionamiento;
import tp.po2.sem.sistemaEstacionamiento.SistemaEstacionamiento;

public class InspectorTest {

    private Inspector inspector;
    private SistemaEstacionamiento sem;
    private String patente;
    private ZonaDeEstacionamiento zona;
    
    @BeforeEach
    public void setUp() {
       
        sem = mock(SistemaEstacionamiento.class);
        zona = mock(ZonaDeEstacionamiento.class);
        inspector = new Inspector("Jorge", sem, zona);
        patente = "BBB333";
    }
    
    @Test
    public void testUnInspectorTieneUnSistemaDeEstacionamiento() {
    	assertEquals(sem, inspector.getSistemadeEstacionamientoMedido());
    }
    
    @Test
    public void testUnInspectorTieneUnaZonaDeEstacionamientoAsignada() {
        assertEquals(zona, inspector.getZonaAsignada());
    }
    
    @Test
    public void testUnInspectorConoceSuNombre() {
    	assertEquals("Jorge", inspector.getNombreInspector());
    }
    
    @Test
    public void testUnInspectorConsultaElEstadoDeEstacionamientoDeUnVehiculoYResultaVigente() {
    	
        when(sem.poseeEstacionamientoVigente(patente)).thenReturn(true);

        boolean resultado = inspector.verificarPatente(patente);

        verify(sem).poseeEstacionamientoVigente(patente);

        assertEquals(true, resultado);
    }
    /*
    @Test
    public void testCuandoUnInspectorConsultaElEstadoDeEstacionamientoDeUnVehiculoYResultaInvalidoRealizaUnaInfraccion() {
    	
    	when(sem.poseeEstacionamientoVigente(patente)).thenReturn(false);
    	
    	inspector.registrarInfraccion(patente);
    	
*/
    	
    
}



