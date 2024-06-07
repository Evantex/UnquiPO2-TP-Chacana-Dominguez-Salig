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
    public void testGetSistemaEstacionamiento() {
    	assertEquals(sem, inspector.getSistemadeEstacionamientoMedido());
    }
    
    @Test
    public void testGetZonaDeEstacionamientoAsignada() {
        assertEquals(zona, inspector.getZonaAsignada());
    }
    
    @Test
    public void testGetNombreDelInspector() {
    	assertEquals("Jorge", inspector.getNombreInspector());
    }
    
    @Test
    public void testSetSistemaEstacionamiento() {
        SistemaEstacionamiento nuevoSem = mock(SistemaEstacionamiento.class);
        inspector.setSistemadeEstacionamientoMedido(nuevoSem);
        assertEquals(nuevoSem, inspector.getSistemadeEstacionamientoMedido());
    }

    @Test
    public void testSetZonaDeEstacionamientoAsignada() {
        ZonaDeEstacionamiento nuevaZona = mock(ZonaDeEstacionamiento.class);
        inspector.setZonaAsignada(nuevaZona);
        assertEquals(nuevaZona, inspector.getZonaAsignada());
    }
    
    @Test
    public void testSetNombreInspector() {
    	String nuevoNombre = "Carlos";
    	inspector.setNombreInspector(nuevoNombre);
    	assertEquals(nuevoNombre, inspector.getNombreInspector());
    }
    
    @Test
    public void testUnInspectorConsultaElEstadoDeEstacionamientoDeUnVehiculoYResultaVigente() {
    	
        when(sem.poseeEstacionamientoVigente(patente)).thenReturn(true);

        boolean resultado = inspector.verificarPatente(patente);

        verify(sem).poseeEstacionamientoVigente(patente);

        assertEquals(true, resultado);
    }
    
    @Test
    public void testUnInspectorConsultaElEstadoDeEstacionamientoDeUnVehiculoYNoEstaVigente() {
    	
        when(sem.poseeEstacionamientoVigente(patente)).thenReturn(false);
        
        boolean resultado = inspector.verificarPatente(patente);
        
        verify(sem).poseeEstacionamientoVigente(patente);
        
        assertEquals(false, resultado);
    }
    
    @Test
    public void testUnInspectorNotificaUnaInfraccionAlSistemaCentralSiUnVehiculoNoTieneEstacionamientoVigente() {
        // Configurar el mock para que devuelva false cuando se consulta si tiene estacionamiento vigente
        when(sem.poseeEstacionamientoVigente(patente)).thenReturn(false);

        // Llamar al método que se está probando
        inspector.verificarPatente(patente);

        // Verificar que se haya llamado al método notificarInfraccion con los argumentos correctos
        verify(sem).registrarInfraccion(patente);
    }
}


