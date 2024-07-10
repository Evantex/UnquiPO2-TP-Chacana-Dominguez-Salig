package tp.po2.sem.app;

public class Vigente implements EstadoEstacionamiento
{

	@Override
	public void posibleInicioEstacionamiento(ModoDesplazamiento modo, App aplicacion) throws Exception {}
	
	@Override
	public void posibleFinEstacionamiento(ModoDesplazamiento modo, App aplicacion) throws Exception
	{
		if( aplicacion.validarMismoPuntoGeografico() ) 
		{
			modo.finEstacionamiento(aplicacion);
		}
	}

}
