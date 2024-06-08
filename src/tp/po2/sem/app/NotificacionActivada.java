package tp.po2.sem.app;

public class NotificacionActivada implements ModoNotificaciones
{

	@Override
	public void notificarInicioEstacionamiento(App aplicacion)
	{
		aplicacion.notificarUsuario("Posible inicio de estacionamiento");
	}

	@Override
	public void notificarFinEstacionamiento(App aplicacion) 
	{
		aplicacion.notificarUsuario("Posible fin de estacionamiento");
	}
	

}
