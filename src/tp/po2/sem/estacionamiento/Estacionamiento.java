package tp.po2.sem.estacionamiento;
import java.awt.Point;
import java.time.Duration;
import java.time.LocalDateTime;


public abstract class Estacionamiento
{
	protected LocalDateTime inicioEstacionamiento;
	protected LocalDateTime finEstacionamiento;
	protected String patenteVehiculo;
	protected boolean estaVigente = true;
	protected String nombreZonaEstacionamiento;
	protected Duration duracionEnHoras;

	public abstract boolean estaVigente();
	public abstract String getIdentificadorEstacionamiento();
	public abstract int getDuracionEnHoras();
	
	public void finalizarEstacionamiento() 
	{ 
		this.finEstacionamiento = LocalDateTime.now();
	}
	
	public LocalDateTime getInicioEstacionamiento()
	{
		return this.inicioEstacionamiento;
	}
	
	public LocalDateTime getFinEstacionamiento()
	{
		return this.finEstacionamiento;
	}
}
