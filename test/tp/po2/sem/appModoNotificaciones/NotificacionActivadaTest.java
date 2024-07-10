 package tp.po2.sem.appModoNotificaciones;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tp.po2.sem.app.App;
import tp.po2.sem.app.Celular;

import static org.mockito.Mockito.*;

public class NotificacionActivadaTest {

	private NotificacionActivada notificacionActivada;
	private App mockApp;
	private Celular mockCelular;

	@BeforeEach
	public void setUp() {
		notificacionActivada = new NotificacionActivada();
		mockApp = mock(App.class);
		mockCelular = mock(Celular.class);

		when(mockApp.getCelularAsociado()).thenReturn(mockCelular);
	}

	@Test
	public void testNotificar() {
		String mensaje = "Mensaje de prueba";

		notificacionActivada.notificar(mockApp, mensaje);

		verify(mockCelular).recibirMensaje(mensaje);
	}

	/*
	 * @Test public void testNotificar() { Celular celularMock =
	 * mock(Celular.class); NotificacionActivada modo = new NotificacionActivada();
	 * 
	 * modo.notificar(celularMock, "Mensaje de prueba");
	 * 
	 * verify(celularMock, times(1)).recibirMensaje("Mensaje de prueba"); }
	 */
}
