package tp.po2.sem.sistemaEstacionamiento;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

public class NotificadorTest {

    private Notificador notificador;
    private Observer observador1;
    private Observer observador2;

    @BeforeEach
    public void setUp() {
        
        notificador = new Notificador();
        observador1 = mock(Observer.class);
        observador2 = mock(Observer.class);
    }
    
    @Test
    public void testGetObservadores() {
        
        notificador.agregarObservador(observador1);
        notificador.agregarObservador(observador2);

        List<Observer> observadores = notificador.getObservadores();
        assertEquals(2, observadores.size());
        assertTrue(observadores.contains(observador1));
        assertTrue(observadores.contains(observador2));
    }
    
    @Test
    public void testAgregarObservador() {
        
        notificador.agregarObservador(observador1);
        notificador.agregarObservador(observador2);

        List<Observer> observadores = notificador.getObservadores();
        assertTrue(observadores.contains(observador1));
        assertTrue(observadores.contains(observador2));
    }

    @Test
    public void testEliminarObservador() {
       
        notificador.agregarObservador(observador1);
        notificador.agregarObservador(observador2);
        notificador.eliminarObservador(observador1);

        List<Observer> observadores = notificador.getObservadores();
        assertFalse(observadores.contains(observador1));
        assertTrue(observadores.contains(observador2));
    }

    @Test
    public void testNotificarObservadores() {
        
        notificador.agregarObservador(observador1);
        notificador.agregarObservador(observador2);
        EventoEstacionamiento evento = mock(EventoEstacionamiento.class);

        notificador.notificarObservadores(evento);

        verify(observador1).actualizar(evento);
        verify(observador2).actualizar(evento);
    }
}


