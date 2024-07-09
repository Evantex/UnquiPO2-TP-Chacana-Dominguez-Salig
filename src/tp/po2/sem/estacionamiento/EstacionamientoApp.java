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

	
	/*
	public EstacionamientoApp(App app, LocalTime horaInicioEstacionamiento, LocalTime posibleHoraFinal,
			double posiblePrecio) 
	{
		this.aplicacion = app;
		this.inicioEstacionamiento = horaInicioEstacionamiento;
		this.finEstacionamiento = posibleHoraFinal;
		this.duracionEnHoras = Duration.between(horaInicioEstacionamiento, posibleHoraFinal);
		this.costoEstacionamiento = posiblePrecio;
		this.patenteVehiculo = app.getPatente();
		this.nroCelularApp = app.getNroCelularAsociado();
		this.vigenciaEstacionamiento = new EstacionamientoVigente();
	}
	*/
	
	public EstacionamientoApp(App app, String celular, String dominioVehiculo)
	{
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
		this.finEstacionamiento = LocalTime.now();
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

	public String getNroCelular() 
	{
		return this.nroCelularApp;
	}


	@Override
	public boolean estaVigente() 
	{
		try 
		{
			this.verificarSiFinalizo();
			return false;
		} 
		catch (Exception e) 
		{
			return true;
		}
	}
	
	private void verificarSiFinalizo() throws Exception 
	{
		if (this.finEstacionamiento == null)
		{
			throw new Exception("Aún no ha finalizado el estacionamiento.");
		}
	}

}
