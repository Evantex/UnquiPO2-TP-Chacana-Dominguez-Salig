package tp.po2.sem.app;

import static org.mockito.Mockito.*;

import java.awt.Point;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tp.po2.sem.appModoNotificaciones.NotificacionActivada;
import tp.po2.sem.appModoNotificaciones.NotificacionDesactivada;

public class ModalidadConduciendoTest {

    private ModalidadConduciendo modo;
    private App aplicacion;
    private NotificacionActivada modoNotificacionActivada;
    private NotificacionDesactivada modoNotificacionDesactivada;
    private Manual modoEstacionamientoManual;
    private Automatico modoEstacionamientoAutomatico;
    private Celular celularDeUsuario;

    @BeforeEach
    void setUp() throws Exception {
        modo = new ModalidadConduciendo();
        aplicacion = mock(App.class);

        when(aplicacion.tieneEstacionamientoVigente()).thenReturn(false);
        when(aplicacion.estaDentroDeZonaEstacionamiento()).thenReturn(true);
        when(aplicacion.getUbicacionActual()).thenReturn(new Point(1, 2));

        modoNotificacionDesactivada = mock(NotificacionDesactivada.class);
        modoNotificacionActivada = mock(NotificacionActivada.class);
        modoEstacionamientoManual = mock(Manual.class);
        modoEstacionamientoAutomatico = mock(Automatico.class);
        celularDeUsuario = mock (Celular.class);
    }

    @Test
    void testCaminandoConNotificacionDesactivadaNoEnvíaMensajes() throws Exception {
    	
        when(aplicacion.getModoNotificacion()).thenReturn(modoNotificacionDesactivada);
        when(aplicacion.getModoEstacionamiento()).thenReturn(modoEstacionamientoManual);
        when(aplicacion.getCelularAsociado()).thenReturn(celularDeUsuario);
        
        modo.caminando(aplicacion);

        verify(celularDeUsuario, never()).recibirMensaje(anyString());
    }

    @Test
    void testCaminandoConNotificacionActivadaEnvíaMensajes() throws Exception {
        when(aplicacion.getModoNotificacion()).thenReturn(modoNotificacionActivada);
        when(aplicacion.getModoEstacionamiento()).thenReturn(modoEstacionamientoAutomatico);
        when(aplicacion.getCelularAsociado()).thenReturn(celularDeUsuario);
        
        modo.caminando(aplicacion);
        
        verify(aplicacion, times(1)).notificarUsuario("Alerta inicio estacionamiento");
    }

    @Test
    void testCaminandoEnModoManualNoIniciaEstacionamiento() throws Exception {
        when(aplicacion.getModoNotificacion()).thenReturn(modoNotificacionActivada);
        when(aplicacion.getModoEstacionamiento()).thenReturn(modoEstacionamientoManual);

        modo.caminando(aplicacion);

        verify(aplicacion, never()).iniciarEstacionamiento();
    }

    @Test
    void testCaminandoEnModoAutomaticoIniciaEstacionamiento() throws Exception {
        when(aplicacion.getModoNotificacion()).thenReturn(modoNotificacionActivada);
        when(aplicacion.getModoEstacionamiento()).thenReturn(modoEstacionamientoAutomatico);

        modo.caminando(aplicacion);

        verify(modoEstacionamientoAutomatico, times(1)).iniciarEstacionamiento(aplicacion);
        verify(aplicacion, times(1)).setModoDeDesplazamiento(any(ModalidadCaminando.class));
        verify(aplicacion, times(1)).setUbicacionEstacionamiento(aplicacion.getUbicacionActual());
    }

    @Test
    void testUpdate() {
        modo.update(aplicacion);

        verify(aplicacion, times(1)).setModoDeDesplazamiento(any(ModalidadCaminando.class));
        verify(aplicacion, times(1)).setUbicacionEstacionamiento(aplicacion.getUbicacionActual());
    }

    @Test
    void testConduciendoNoHaceNada() {
        modo.conduciendo(aplicacion);

        verifyNoInteractions(aplicacion);
    }
}
