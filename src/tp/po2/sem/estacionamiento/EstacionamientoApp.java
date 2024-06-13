package tp.po2.sem.estacionamiento;

import java.awt.Point;
import java.time.Duration;
import java.time.LocalDateTime;
import tp.po2.sem.app.*;

public class EstacionamientoApp extends Estacionamiento
{
	private App aplicacion;
	private String nroCelularApp;

	
	public EstacionamientoApp(App app, String celular, String dominioVehiculo)
	{
		this.aplicacion = app;
		this.inicioEstacionamiento = LocalDateTime.now();
		// this.finEstacionamiento = app.
		this.patenteVehiculo = dominioVehiculo;
		this.nroCelularApp = celular;
	}

	
	@Override
	public LocalDateTime getHoraMaximaFinEstacionamiento()
	{
		LocalDateTime horaMáximaPermitidaSaldo = this.inicioEstacionamiento.plusHours( aplicacion.getHorasMaximasPermitidasEstacionamiento() );
		LocalDateTime horaMáximaPermitidaDelDía = LocalDateTime.of( this.inicioEstacionamiento.getYear(), 	this.inicioEstacionamiento.getMonth(), 
						this.inicioEstacionamiento.getDayOfMonth(), 	20, 00);
		return horaMáximaPermitidaSaldo.isBefore(horaMáximaPermitidaDelDía) ? horaMáximaPermitidaSaldo : horaMáximaPermitidaDelDía;
	}
	

	@Override
	public boolean estaVigente()
	{
		return false;
	}

	@Override
	public String getIdentificadorEstacionamiento()
	{
		return this.nroCelularApp;
	}

	@Override
	public int getDuracionEnHoras() throws Exception
	{
		this.verificarSiFinalizo();
		return super.getDuracionEnHoras();
	}
	
	
	@Override
	public void finalizarEstacionamiento() 
	{ 
		this.finEstacionamiento = LocalDateTime.now();
		Duration duracion = Duration.between(this.inicioEstacionamiento, this.finEstacionamiento);
		this.duracionEnHoras = duracion;
	}
	
	
	private void verificarSiFinalizo() throws Exception 
	{
	    if ( this.finEstacionamiento == null )
	    {
	        throw new Exception("Aún no ha finalizado el estacionamiento.");
	    }
	}
}
