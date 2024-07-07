package tp.po2.sem.appGPS;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;

import tp.po2.sem.app.App;

public class UbicacionDesactivadaTest {

    @Test
    public void testActivar() {
        App appMock = mock(App.class);
        UbicacionDesactivada estado = new UbicacionDesactivada();

        estado.activar(appMock);

        // Verificar que el estado se cambió a UbicacionActivada
        verify(appMock, times(1)).setEstadoGps(any(UbicacionActivada.class));
    }

    @Test
    public void testDesactivar() {
        App appMock = mock(App.class);
        UbicacionDesactivada estado = new UbicacionDesactivada();

        estado.desactivar(appMock);

        // No se espera que desactivar cambie el estado
        verify(appMock, never()).setEstadoGps(any(EstadoGPS.class));
    }

    @Test
    public void testSeEncuentraActivada() {
        UbicacionDesactivada estado = new UbicacionDesactivada();

        assertFalse(estado.seEncuentraActivada());
    }
}
