package tp.po2.sem.app;

public class Automatico implements ModoApp 
{
	@Override
	public void iniciarEstacionamiento(App aplicacion) throws Exception
	{
		aplicacion.iniciarEstacionamiento();
		if( aplicacion.tieneEstacionamientoVigente() )
		{
			aplicacion.getModoNotificacion().notificar(aplicacion, "Se ha iniciado un estacionamiento de forma automática");
		}
	}

	@Override
	public void finalizarEstacionamiento(App aplicacion) throws Exception
	{
		aplicacion.finalizarEstacionamiento();
		aplicacion.getModoNotificacion().notificar(aplicacion, "Se ha finalizado un estacionamiento de forma automática");
	}
	
	
	
}
