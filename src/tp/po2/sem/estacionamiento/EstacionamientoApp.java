package tp.po2.sem.estacionamiento;

import java.time.LocalDateTime;
import tp.po2.sem.app.*;

public class EstacionamientoApp extends Estacionamiento
{
	
	private App appAsociada;
	
	public EstacionamientoApp( LocalDateTime inicioEstacionamiento, String dominioVehiculo, App app)
	{
		this.inicioEstacionamiento = inicioEstacionamiento;
		this.patenteVehículo = dominioVehiculo;
		this.appAsociada = app;
	}

	@Override
	public String identificadorEstacionamiento() 
	{
		return this.nroCelular;
	}
}
