package tp.po2.sem.inspector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tp.po2.sem.sistemaEstacionamiento.SistemaEstacionamiento;

public class InspectorTest {

    private Inspector inspector;
    private SistemaEstacionamiento sem;

    @BeforeEach
    public void setUp() {
       
        sem = mock(SistemaEstacionamiento.class);
        inspector = new Inspector("Jorge", sem);
    }

    @Test
    public void testAUnInspectorSeLeAsignaUnaZonaDeEstacionamiento() {
        assertEquals(sem, inspector.getSistemadeEstacionamientoMedido());
    }
}



