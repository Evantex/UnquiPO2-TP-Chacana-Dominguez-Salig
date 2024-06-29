package tp.po2.sem.estacionamiento;

import java.awt.Point;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

public abstract class Estacionamiento {
	protected LocalTime inicioEstacionamiento;
	protected LocalTime finEstacionamiento = null;
	protected String patenteVehiculo;
	protected String nombreZonaEstacionamiento;
	protected int duracionEnHoras;
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

	public LocalTime getFinEstacionamiento() {
		return this.finEstacionamiento;
	}

	public int getCostoEstacionamiento() {
		return this.duracionEnHoras * 40;
	}

	public int getDuracionEnHoras() throws Exception  {
		return this.duracionEnHoras;
	}

	public void setFinEstacionamiento(LocalTime horas) {
		this.finEstacionamiento = horas;
	}

	public String getPatente() {

		return this.patenteVehiculo;
	}

	public abstract boolean esEstacionamientoCompraPuntual();

	public abstract boolean esEstacionamientoApp();

}
