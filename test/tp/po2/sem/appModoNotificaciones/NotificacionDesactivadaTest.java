package tp.po2.sem.appModoNotificaciones;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tp.po2.sem.app.App;

import static org.mockito.Mockito.*;

public class NotificacionDesactivadaTest {

    private NotificacionDesactivada notificacionDesactivada;
    private App mockApp;

    @BeforeEach
    public void setUp() {
        notificacionDesactivada = new NotificacionDesactivada();
        mockApp = mock(App.class);
    }

    @Test
    public void testNotificar() {
        String mensaje = "Mensaje de prueba";
        notificacionDesactivada.notificar(mockApp, mensaje);
        verifyNoInteractions(mockApp);
    }
}

