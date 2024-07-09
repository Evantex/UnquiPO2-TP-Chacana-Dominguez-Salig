package tp.po2.sem.app;

public class Automatico implements ModoApp 
{
	@Override
	public void iniciarEstacionamiento(App aplicacion) throws Exception
	{
		aplicacion.iniciarEstacionamiento();
	}

	@Override
	public void finalizarEstacionamiento(App aplicacion) throws Exception
	{
		aplicacion.finalizarEstacionamiento();
	}

	@Override
	public void notificacionModoApp(App aplicacion, String msg) 
	{
		aplicacion.notificarUsuario(msg);
	}
	
	
}
