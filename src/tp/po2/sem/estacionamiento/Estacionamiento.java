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
	protected Duration duracionEnHoras;
	protected EstadoEstacionamiento vigenciaEstacionamiento;

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

	public double getCostoEstacionamiento() {
		// Calcular las horas de duración en double y multiplicar por 40
		long horas = duracionEnHoras.toHours();
		return horas * (SistemaEstacionamiento.getPrecioporhora()); // clase tarifa para menejar montos si hay aumento?
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
