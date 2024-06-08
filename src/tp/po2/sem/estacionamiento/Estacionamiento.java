package tp.po2.sem.estacionamiento;

import java.time.Duration;
import java.time.LocalDateTime;


public abstract class Estacionamiento {
	protected LocalDateTime inicioEstacionamiento;
	protected LocalDateTime finEstacionamiento;
	protected String patenteVehiculo;
	protected boolean estaVigente = true;
	protected String zonaEstacionamiento;

	public Estacionamiento() {}
	
	public Estacionamiento(Duration duracion, String patenteVehiculo) {
		this.inicioEstacionamiento = LocalDateTime.now();
		this.finEstacionamiento = inicioEstacionamiento.plus(duracion);
		this.patenteVehiculo = patenteVehiculo;
		this.estaVigente = finEstacionamiento.isAfter(LocalDateTime.now());
	}

	public boolean estaVigente()
	{
		return estaVigente;
	}

	public void finalizarEstacionamiento() { // el sem si quiere puede finalizarlo cuando quiera
		this.estaVigente = false;
		this.finEstacionamiento = LocalDateTime.now();
	}

//	private String datox;

//	public Estacionamiento(String datox) {
//		super();
//		this.datox = datox;
//	}

}
