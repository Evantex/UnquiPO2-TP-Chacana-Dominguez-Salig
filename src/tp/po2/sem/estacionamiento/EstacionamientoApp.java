package tp.po2.sem.estacionamiento;

import java.awt.Point;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

import tp.po2.sem.app.*;

public class EstacionamientoApp extends Estacionamiento {
	private App aplicacion;
	private String nroCelularApp;

	public EstacionamientoApp(App app, String celular, String dominioVehiculo) {
		this.aplicacion = app;
		this.inicioEstacionamiento = LocalTime.now();
		// this.finEstacionamiento = app.
		this.patenteVehiculo = dominioVehiculo;
		this.nroCelularApp = celular;
		this.vigenciaEstacionamiento = new EstacionamientoVigente();
	}
	
	@Override
	public String getIdentificadorEstacionamiento() {
		return this.nroCelularApp;
	}
	
	@Override
	public int getDuracionEnHoras() throws Exception {
		verificarSiFinalizo();
		return super.getDuracionEnHoras();
	}

	@Override
	public void finalizarEstacionamiento() {
		this.finEstacionamiento = LocalTime.now();
		int duracion = (int) (Duration.between(this.inicioEstacionamiento, this.finEstacionamiento)).toHours();
		this.duracionEnHoras = duracion;
		this.vigenciaEstacionamiento = new EstacionamientoNoVigente();
	}

	private void verificarSiFinalizo() throws Exception {
		if (this.finEstacionamiento == null) {
			throw new Exception("Aún no ha finalizado el estacionamiento.");
		}
	}

	public boolean esEstacionamientoCompraPuntual() {
		return false;
	}

	public boolean esEstacionamientoApp() {
		return true;
	}
	
	public String getNroCelular() {
		return this.nroCelularApp;
	}

}
