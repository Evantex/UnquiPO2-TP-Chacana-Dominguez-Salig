package tp.po2.sem.app;
import tp.po2.sem.sistemaEstacionamiento.*;

public class App implements MovementSensor
{
	private Usuario usuarioAsociado;
	private boolean deteccionDesplazamiento;
	private SistemaEstacionamiento SEM;
	private boolean vaCaminando;
	private double saldoDisponible;
	private AsistenciaUsuario asistente;
	private ModoEstacionamiento modo;
	
	
	
	
	
	@Override
	public void driving()
	{
		if( usuarioAsociado.estaEnZonaDeEstacionamiento() && this.vaCaminando )
		{
			this.vaCaminando = false;
			
		}
			
	}
	
	
	
	
	
	@Override
	public void walking() {}
	
	
	

	
	
	

}