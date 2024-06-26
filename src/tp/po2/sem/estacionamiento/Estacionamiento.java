package tp.po2.sem.estacionamiento;
import java.awt.Point;
import java.time.Duration;
import java.time.LocalDateTime;


public abstract class Estacionamiento
{
	protected LocalDateTime inicioEstacionamiento;
	protected LocalDateTime finEstacionamiento = null; 
	protected String patenteVehiculo;
	protected String nombreZonaEstacionamiento;
	protected Duration duracionEnHoras;

	public abstract boolean estaVigente();
	public abstract String getIdentificadorEstacionamiento();
	public abstract LocalDateTime getHoraMaximaFinEstacionamiento();
	
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
	
	public double getCostoEstacionamiento()
	{
		return this.duracionEnHoras.toHours() * 40;
	}
	
	public int getDuracionEnHoras() throws Exception 
	{
		return this.duracionEnHoras.toHoursPart();
	}
	
	public void setFinEstacionamiento()
	{
		this.finEstacionamiento = LocalDateTime.now();
	}
	public String getPatente() {
		
		return this.patenteVehiculo;
	}
	public abstract boolean esEstacionamientoCompraPuntual();

	public abstract boolean esEstacionamientoApp();

}
