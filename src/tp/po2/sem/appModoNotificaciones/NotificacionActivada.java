package tp.po2.sem.appModoNotificaciones;

import tp.po2.sem.app.Celular;

public class NotificacionActivada implements ModoNotificaciones
{

	@Override
	public void notificar(Celular celularAsociado, String msg) {
		
		celularAsociado.recibirMensaje(msg);
		
	}
	

}
