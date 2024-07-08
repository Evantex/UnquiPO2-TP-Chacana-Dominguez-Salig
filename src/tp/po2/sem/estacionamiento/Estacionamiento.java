package tp.po2.sem.estacionamiento;

import java.awt.Point;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.concurrent.Semaphore;

import tp.po2.sem.sistemaEstacionamiento.SistemaEstacionamiento;

public abstract class Estacionamiento
{
	protected LocalDateTime inicioEstacionamiento;
	protected LocalDateTime  finEstacionamiento = null;
	protected String patenteVehiculo;
	protected Duration duracionEnHoras;
	protected double costoEstacionamiento;

	// public abstract String getIdentificadorEstacionamiento();

	public abstract boolean estaVigente();

	public void finalizarEstacionamiento() 
	{ 
		this.finEstacionamiento = LocalDateTime.now();
	}

	public LocalDateTime getInicioEstacionamiento() 
	{
		return this.inicioEstacionamiento;
	}

	public int getDuracionEnHoras() throws Exception 
	{
		return this.duracionEnHoras.toHoursPart();
	}

	public String getPatente()
	{
		return this.patenteVehiculo;
	}
	
	public double getCostoEstacionamiento() throws Exception 
	{
		return this.costoEstacionamiento;
	}
	
	public void setCostoEstacionamiento(double costo) 
	{
		this.costoEstacionamiento = costo;
	}
	
	public abstract boolean esEstacionamientoCompraPuntual();
	public abstract boolean esEstacionamientoApp();

	public LocalDateTime getFinEstacionamiento() 
	{	
		return this.finEstacionamiento;
	}

	public abstract String getIdentificadorEstacionamiento();

}
