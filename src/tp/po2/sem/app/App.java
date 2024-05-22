package tp.po2.sem.app;

public class App implements MovementSensor
{
	private Usuario usuarioAsociado;
	private boolean deteccionDesplazamiento;
	
	
	
	@Override
	public void driving()
	{
		if( usuarioAsociado.estaEnZonaDeEstacionamiento() )
			
	}
	
	@Override
	public void walking() {}
	
	
	
	
	
	

}
