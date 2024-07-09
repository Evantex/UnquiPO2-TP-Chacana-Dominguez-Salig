package tp.po2.sem.appModoNotificaciones;

import tp.po2.sem.app.App;

public class NotificacionActivada implements ModoNotificaciones
{

	@Override
	public void notificar(App aplicacion, String msg)
	{
		aplicacion.getCelularAsociado().recibirMensaje(msg);
	}

}
