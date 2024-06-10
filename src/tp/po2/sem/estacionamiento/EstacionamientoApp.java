package tp.po2.sem.estacionamiento;

import java.awt.Point;
import java.time.LocalDateTime;
import tp.po2.sem.app.*;

public class EstacionamientoApp extends Estacionamiento
{
	
	private String nroCelularApp;

	
	public EstacionamientoApp(App app, String celular, String dominioVehiculo)
	{
		this.inicioEstacionamiento = LocalDateTime.now();
		this.patenteVehiculo = dominioVehiculo;
		this.nroCelularApp = celular;
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



	
	
}
