package tp.po2.sem.app;

public class Automatico implements ModoEstacionamiento
{

	@Override
	public void iniciarEstacionamiento(App aplicacion, String patente) 
	{
		aplicacion.iniciarEstacionamiento(patente);
	}

	@Override
	public void finalizarEstacionamiento(App aplicacion)
	{
		aplicacion.finalizarEstacionamiento();
	}

}
