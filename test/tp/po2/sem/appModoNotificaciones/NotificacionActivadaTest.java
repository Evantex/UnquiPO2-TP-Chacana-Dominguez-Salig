package tp.po2.sem.appModoNotificaciones;

import org.junit.jupiter.api.Test;

import tp.po2.sem.app.Celular;

import static org.mockito.Mockito.*;

public class NotificacionActivadaTest {

    @Test
    public void testNotificar() {
        Celular celularMock = mock(Celular.class);
        NotificacionActivada modo = new NotificacionActivada();
        
        modo.notificar(celularMock, "Mensaje de prueba");
        
        verify(celularMock, times(1)).recibirMensaje("Mensaje de prueba");
    }
}
