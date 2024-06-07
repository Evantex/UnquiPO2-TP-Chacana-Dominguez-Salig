package tp.po2.sem.sistemaEstacionamiento;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import tp.po2.sem.estacionamiento.Estacionamiento;

public class SistemaEstacionamientoTest {

    SistemaEstacionamiento sistemaEstacionamiento;
    Estacionamiento estacionamientoMock;
    Set<Estacionamiento> spyListaEstacionamientos;

    @BeforeEach
    public void setUp() {
        // Creamos el mock de Estacionamiento
        estacionamientoMock = mock(Estacionamiento.class);

        // Creamos un spy de la lista de estacionamientos
        spyListaEstacionamientos = spy(new HashSet<Estacionamiento>());

        // Creamos el SistemaEstacionamiento con el spy de la lista de estacionamientos
        sistemaEstacionamiento = new SistemaEstacionamiento(spyListaEstacionamientos);
    }

    @Test
    public void testRegistrarUnEstacionamiento() {
        // Ejecutamos el método que queremos probar, pasando el mock de Estacionamiento
        sistemaEstacionamiento.registrarEstacionamiento(estacionamientoMock);

        // Con el spy verifico que se le mandó correctamente el mensaje add a la
        // colección dentro del mensaje getCantidadEstacionamientos().
        assertEquals(sistemaEstacionamiento.getCantidadEstacionamientos(), 1);

        // Además se verifica que el envío de mensajes se haga en orden esperado.
        InOrder orden = inOrder(spyListaEstacionamientos);
        orden.verify(spyListaEstacionamientos).add(estacionamientoMock);

        // Verifico con el spy que se le mandó el mensaje size a la colección para
        // obtener la cantidad de estacionamientos esperada.
        verify(spyListaEstacionamientos).size();
    }
}
