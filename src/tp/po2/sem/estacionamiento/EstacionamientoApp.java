package tp.po2.sem.estacionamiento;

import java.awt.Point;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

import tp.po2.sem.app.*;
import tp.po2.sem.sistemaEstacionamiento.SistemaEstacionamiento;

public class EstacionamientoApp extends Estacionamiento {
	private App aplicacion;
	private String nroCelularApp;
	private LocalTime finEstacionamiento;
	private double costoFinal;

	public EstacionamientoApp(App app, String celular, String dominioVehiculo) {
		this.aplicacion = app;
		this.inicioEstacionamiento = LocalTime.now();
		this.finEstacionamiento = null;
		this.patenteVehiculo = dominioVehiculo;
		this.nroCelularApp = celular;
		this.vigenciaEstacionamiento = new EstacionamientoVigente();
	}

	public double getCostoFinal() throws Exception {
		verificarSiFinalizo();
		return costoFinal;
	}

	public void setCostoFinal(double costo) {
		costoFinal = costo;
	}

	@Override
	public String getIdentificadorEstacionamiento() {
		return this.nroCelularApp;
	}

	@Override
	public Duration getDuracionEnHoras() throws Exception {
		verificarSiFinalizo();
		return super.getDuracionEnHoras();
	}

	@Override
	public void finalizarEstacionamiento() {
		this.finEstacionamiento = LocalTime.now();
		Duration duracion = (Duration.between(this.inicioEstacionamiento, this.finEstacionamiento));
		this.duracionEnHoras = duracion;
		this.vigenciaEstacionamiento = new EstacionamientoNoVigente();
	}

	private void verificarSiFinalizo() throws Exception {
		if (this.finEstacionamiento == null) {
			throw new Exception("Aún no ha finalizado el estacionamiento.");
		}
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

	public LocalTime getFinEstacionamiento() {
		return finEstacionamiento;
	}

	public void setFinEstacionamiento(LocalTime finEstacionamiento) {
		this.finEstacionamiento = finEstacionamiento;
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

	public double getCostoEstacionamiento() {
		// Calcular la duración del estacionamiento
		Duration duracion = Duration.between(this.getInicioEstacionamiento(), this.getFinEstacionamiento());

		// Obtener la duración en horas
		long horas = duracion.toHours();

		// Multiplicar la duración por 40
		long costoTotal = horas * SistemaEstacionamiento.getPrecioporhora();
		return costoTotal;
	}

}
