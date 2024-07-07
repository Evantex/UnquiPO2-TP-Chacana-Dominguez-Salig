package tp.po2.sem.appModoNotificaciones;

import org.junit.jupiter.api.Test;

import tp.po2.sem.app.Celular;

import static org.mockito.Mockito.*;

public class NotificacionDesactivadaTest {

    @Test
    public void testNotificar() {
        Celular celularMock = mock(Celular.class);
        
        NotificacionDesactivada modo = new NotificacionDesactivada();
        
        modo.notificar(celularMock, "Mensaje de prueba");
        
        verify(celularMock, never()).recibirMensaje(anyString());
    }
}
