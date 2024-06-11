package tp.po2.sem.app;

public class Automatico implements ModoEstacionamiento
{

	@Override
	public void iniciarEstacionamiento(App aplicacion, String patente) 
	{
		aplicacion.iniciarEstacionamiento(patente);
		if( aplicacion.tieneEstacionamientoVigente() )
		{
			aplicacion.notificarUsuario("Inicio de estacionamiento realizado"
					+ "de forma automática");
		}
	}

	@Override
	public void finalizarEstacionamiento(App aplicacion)
	{
		aplicacion.finalizarEstacionamiento();
	}

}
