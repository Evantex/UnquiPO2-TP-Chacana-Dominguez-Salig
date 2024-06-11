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
	

	
	public abstract boolean estaVigente();

	
	public void finalizarEstacionamiento() 
	{ 
		this.finEstacionamiento = LocalDateTime.now();
	}
	
	public abstract String getIdentificadorEstacionamiento();
	
	public LocalDateTime getInicioEstacionamiento()
	{
		return this.inicioEstacionamiento;
	}
	
	public LocalDateTime getFinEstacionamiento()
	{
		return this.finEstacionamiento;
	}

}
