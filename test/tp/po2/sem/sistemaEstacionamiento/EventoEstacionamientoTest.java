package tp.po2.sem.sistemaEstacionamiento;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tp.po2.sem.estacionamiento.Estacionamiento;

public class EventoEstacionamientoTest {

    private Estacionamiento estacionamiento;
    private EventoEstacionamiento eventoInicio;
    private EventoEstacionamiento eventoFin;

    @BeforeEach
    public void setUp() {
    	
        estacionamiento = mock (Estacionamiento.class);
        eventoInicio = new EventoEstacionamiento(EventoEstacionamiento.Tipo.INICIO, estacionamiento);
        eventoFin = new EventoEstacionamiento(EventoEstacionamiento.Tipo.FIN, estacionamiento);
    }

    @Test
    public void testGetTipoInicio() {
        
        assertEquals(EventoEstacionamiento.Tipo.INICIO, eventoInicio.getTipo());
    }

    @Test
    public void testGetTipoFin() {
        
        assertEquals(EventoEstacionamiento.Tipo.FIN, eventoFin.getTipo());
    }

    @Test
    public void testGetEstacionamiento() {
        
        assertEquals(estacionamiento, eventoInicio.getEstacionamiento());
        assertEquals(estacionamiento, eventoFin.getEstacionamiento());
    }

    @Test
    public void testSetEstacionamiento() {
        
        Estacionamiento nuevoEstacionamiento = mock (Estacionamiento.class); 
        eventoInicio.setEstacionamiento(nuevoEstacionamiento);
        assertEquals(nuevoEstacionamiento, eventoInicio.getEstacionamiento());

        eventoFin.setEstacionamiento(nuevoEstacionamiento);
        assertEquals(nuevoEstacionamiento, eventoFin.getEstacionamiento());
    }
}
