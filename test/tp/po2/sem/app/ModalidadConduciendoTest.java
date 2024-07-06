package tp.po2.sem.app;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ModalidadConduciendoTest {

    @Mock
    private App aplicacion;

    @Mock
    ModoApp modoEstacionamiento;

    @InjectMocks
    private ModalidadConduciendo modalidadConduciendo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCaminandoConEstacionamientoVigenteNoHaceNada() throws Exception {
        when(aplicacion.tieneEstacionamientoVigente()).thenReturn(true);

        modalidadConduciendo.caminando(aplicacion);

        verify(aplicacion, never()).estaDentroDeZonaEstacionamiento();
        verify(aplicacion, never()).notificarUsuario(anyString());
        verify(modoEstacionamiento, never()).iniciarEstacionamiento(any());
        verify(aplicacion, never()).setModoDeDesplazamiento(any(ModalidadCaminando.class));
        verify(aplicacion, never()).setUbicacionEstacionamiento(any());
    }

    @Test
    void testCaminandoFueraDeZonaEstacionamientoNoHaceNada() throws Exception {
        when(aplicacion.tieneEstacionamientoVigente()).thenReturn(false);
        when(aplicacion.estaDentroDeZonaEstacionamiento()).thenReturn(false);

        modalidadConduciendo.caminando(aplicacion);

        verify(aplicacion, never()).notificarUsuario(anyString());
        verify(modoEstacionamiento, never()).iniciarEstacionamiento(any());
        verify(aplicacion, never()).setModoDeDesplazamiento(any(ModalidadCaminando.class));
        verify(aplicacion, never()).setUbicacionEstacionamiento(any());
    }

    @Test
    void testCaminandoSinEstacionamientoYDentroDeZonaEstacionamiento() throws Exception {
        when(aplicacion.tieneEstacionamientoVigente()).thenReturn(false);
        when(aplicacion.estaDentroDeZonaEstacionamiento()).thenReturn(true);
        when(aplicacion.getModoEstacionamiento()).thenReturn(modoEstacionamiento);

        modalidadConduciendo.caminando(aplicacion);

        verify(aplicacion).notificarUsuario("Alerta inicio estacionamiento");
        verify(modoEstacionamiento).iniciarEstacionamiento(aplicacion);
        verify(aplicacion).setModoDeDesplazamiento(any(ModalidadCaminando.class));
        verify(aplicacion).setUbicacionEstacionamiento(aplicacion.getUbicacionActual());
    }

    @Test
    void testUpdate() {
        modalidadConduciendo.update(aplicacion);

        verify(aplicacion).setModoDeDesplazamiento(any(ModalidadCaminando.class));
        verify(aplicacion).setUbicacionEstacionamiento(aplicacion.getUbicacionActual());
    }

    @Test
    void testConduciendoNoHaceNada() {
        modalidadConduciendo.conduciendo(aplicacion);

        verifyNoInteractions(aplicacion);
    }
}
