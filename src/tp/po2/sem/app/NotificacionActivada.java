package tp.po2.sem.app;

public class NotificacionActivada implements ModoNotificaciones
{

	@Override
	public void notificar(CelularDeUsuario celularAsociado, String msg) {
		
		celularAsociado.recibirMensaje(msg);
		
	}
	

}
