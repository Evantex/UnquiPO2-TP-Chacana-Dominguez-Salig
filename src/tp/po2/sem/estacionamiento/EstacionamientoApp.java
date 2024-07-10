package tp.po2.sem.estacionamiento;

import java.awt.Point;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

import tp.po2.sem.app.*;
import tp.po2.sem.sistemaEstacionamiento.SistemaEstacionamiento;

public class EstacionamientoApp extends Estacionamiento 
{
	private App aplicacion;
	private String nroCelularApp;

	
	public EstacionamientoApp() {};
	
	public EstacionamientoApp(App app, String celular, String dominioVehiculo)
	{
		super();
		this.aplicacion = app;
		this.inicioEstacionamiento = LocalTime.now();
		this.patenteVehiculo = dominioVehiculo;
		this.nroCelularApp = celular;
	}
	

	@Override
	public double getCostoEstacionamiento() throws Exception
	{
		verificarSiFinalizo();
		return super.getCostoEstacionamiento();
	}


	@Override
	public String getIdentificadorEstacionamiento() 
	{
		return this.nroCelularApp;
	}

	public App getAplicacion() {
		return aplicacion;
	}

	public void setAplicacion(App aplicacion) {
		this.aplicacion = aplicacion;
	}

	public String getNroCelularApp() {
		return nroCelularApp;
	}

	public void setNroCelularApp(String nroCelularApp) {
		this.nroCelularApp = nroCelularApp;
	}


	@Override
	public void finalizarEstacionamiento() 
	{
		super.finalizarEstacionamiento();
		Duration duracion = Duration.between(this.inicioEstacionamiento, this.finEstacionamiento);
		this.duracionEnHoras = duracion;
		
	}


	public boolean esEstacionamientoCompraPuntual()
	{
		return false;
	}

	public boolean esEstacionamientoApp()
{
		return true;
	}

	
	private void verificarSiFinalizo() throws Exception 
	{
		if (this.finEstacionamiento == null)
		{
			throw new Exception("Aún no ha finalizado el estacionamiento.");
		}
	}

}
