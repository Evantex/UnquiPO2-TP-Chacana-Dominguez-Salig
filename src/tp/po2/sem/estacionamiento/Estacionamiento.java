package tp.po2.sem.estacionamiento;

import java.awt.Point;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.concurrent.Semaphore;

import tp.po2.sem.sistemaEstacionamiento.SistemaEstacionamiento;

public abstract class Estacionamiento {
	protected LocalTime inicioEstacionamiento;
	protected String patenteVehiculo;
	protected String nombreZonaEstacionamiento;
	protected EstadoEstacionamiento vigenciaEstacionamiento;
	protected Duration duracionEnHoras;

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

	public abstract boolean esEstacionamientoCompraPuntual();

	public abstract boolean esEstacionamientoApp();

}
