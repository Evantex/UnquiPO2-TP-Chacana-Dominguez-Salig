package tp.po2.sem.app;

public class NoVigente implements EstadoEstacionamiento
{

	@Override
	public void posibleInicioEstacionamiento(ModoDesplazamiento modo, App aplicacion) throws Exception
	{
		if( aplicacion.estaDentroDeZonaEstacionamiento() )
		{
			modo.inicioEstacionamiento(aplicacion);
		}
	}

	@Override
	public void posibleFinEstacionamiento(ModoDesplazamiento modo, App aplicacion) {}


	

}