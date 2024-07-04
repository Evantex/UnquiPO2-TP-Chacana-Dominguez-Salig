package tp.po2.sem.estacionamiento;

import java.awt.Point;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.concurrent.Semaphore;

import tp.po2.sem.sistemaEstacionamiento.SistemaEstacionamiento;

public abstract class Estacionamiento {
	protected LocalTime inicioEstacionamiento;
	protected LocalTime finEstacionamiento = null;
	protected String patenteVehiculo;
	protected String nombreZonaEstacionamiento;
	protected EstadoEstacionamiento vigenciaEstacionamiento;
	protected Duration duracionEnHoras;
	protected double costoEstacionamiento;

	public abstract String getIdentificadorEstacionamiento();

	public boolean estaVigente() {
		return vigenciaEstacionamiento.estaVigente();
	}

	public void finalizarEstacionamiento() {
		this.vigenciaEstacionamiento = new EstacionamientoNoVigente();
	}

	public LocalTime getInicioEstacionamiento() {
		return this.inicioEstacionamiento;
	}

	public Duration getDuracionEnHoras() throws Exception {
		return this.duracionEnHoras;
	}

	public String getPatente() {

		return this.patenteVehiculo;
	}
	
	public double getCostoEstacionamiento() throws Exception {
		return this.costoEstacionamiento;
	}
	
	public void setCostoEstacionamiento(double costo) {
		this.costoEstacionamiento = costo;
	}
	
	public abstract boolean esEstacionamientoCompraPuntual();

	public abstract boolean esEstacionamientoApp();

	public LocalTime getFinEstacionamiento() {
		
		return this.finEstacionamiento;
	}

}
