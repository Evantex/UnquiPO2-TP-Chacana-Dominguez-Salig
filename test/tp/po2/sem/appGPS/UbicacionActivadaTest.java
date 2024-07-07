package tp.po2.sem.appGPS;

import org.junit.jupiter.api.Test;

import tp.po2.sem.app.App;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UbicacionActivadaTest {

    @Test
    public void testActivar() {
        App appMock = mock(App.class);
        UbicacionActivada estado = new UbicacionActivada();

        estado.activar(appMock);

        // No se espera que activar cambie el estado
        verify(appMock, never()).setEstadoGps(any(EstadoGPS.class));
    }

    @Test
    public void testDesactivar() {
        App appMock = mock(App.class);
        UbicacionActivada estado = new UbicacionActivada();

        estado.desactivar(appMock);

        // Verificar que el estado se cambió a UbicacionDesactivada
        verify(appMock, times(1)).setEstadoGps(any(UbicacionDesactivada.class));
    }

    @Test
    public void testSeEncuentraActivada() {
        UbicacionActivada estado = new UbicacionActivada();

        assertTrue(estado.seEncuentraActivada());
    }
}

