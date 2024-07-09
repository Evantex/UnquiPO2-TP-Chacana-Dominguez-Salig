package tp.po2.sem.app;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tp.po2.sem.app.*;

public class VigenteTest {

    private Vigente estadoVigente;
    private App aplicacion;
    private ModoDesplazamiento modoDesplazamiento;

    @BeforeEach
    public void setUp() {
        estadoVigente = new Vigente();
        aplicacion = mock(App.class);
        modoDesplazamiento = mock(ModoDesplazamiento.class);
    }

    @Test
    public void testPosibleFinEstacionamiento() throws Exception {
        // Simula que la ubicación actual y la ubicación de estacionamiento son iguales
        when(aplicacion.validarMismoPuntoGeografico()).thenReturn(true);

        // Llama al método que se quiere probar
        estadoVigente.posibleFinEstacionamiento(modoDesplazamiento, aplicacion);

        // Verifica que se llamó al método finEstacionamiento en modoDesplazamiento
        verify(modoDesplazamiento, times(1)).finEstacionamiento(aplicacion);
    }

    @Test
    public void testPosibleFinEstacionamientoUbicacionesDiferentes() throws Exception {
        // Simula que la ubicación actual y la ubicación de estacionamiento son diferentes
        when(aplicacion.validarMismoPuntoGeografico()).thenReturn(false);

        // Llama al método que se quiere probar
        estadoVigente.posibleFinEstacionamiento(modoDesplazamiento, aplicacion);

        // Verifica que NO se llamó al método finEstacionamiento en modoDesplazamiento
        verify(modoDesplazamiento, times(0)).finEstacionamiento(aplicacion);
    }
}
