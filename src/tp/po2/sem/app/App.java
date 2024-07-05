package tp.po2.sem.app;

import java.awt.Point;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import tp.po2.sem.sistemaEstacionamiento.*;
import tp.po2.sem.appGPS.EstadoGPS;
import tp.po2.sem.appGPS.UbicacionDesactivada;
import tp.po2.sem.estacionamiento.*;

public class App implements MovementSensor {

	private SistemaEstacionamiento SEM;
	private ModoEstacionamiento modoEstacionamiento; // Strategy
	private ModoDesplazamiento modoDeDesplazamiento; // State
	private EstadoGPS deteccionDeDesplazamiento;
	private ModoNotificaciones modoNotificacion; // Strategy
	private CelularDeUsuario celularAsociado;
	private Point ubicacionUltimoEstacionamiento;
	private String patenteAsociada;

	App(CelularDeUsuario cel, SistemaEstacionamiento sistema, String patenteAsociada) {
		this.celularAsociado = cel;
		this.SEM = sistema;
		this.patenteAsociada = patenteAsociada;
		this.deteccionDeDesplazamiento = new UbicacionDesactivada();
	}

	public void activarGPS() {
		this.deteccionDeDesplazamiento.activarUbicacion(this);
	}

	public void desactivarGPS() {
		this.deteccionDeDesplazamiento.desactivarUbicacion(this);
	}

	public void setUbicacionGps(EstadoGPS estado) {
		this.deteccionDeDesplazamiento = estado;
	}

	public ModoNotificaciones getModoNotificacion() {
		return this.modoNotificacion;
	}

	@Override
	public void driving() throws Exception {
		if (estaActivadaLaUbicacion()) {
			modoDeDesplazamiento.conduciendo(this);
		}
	}

	@Override
	public void walking() throws Exception {
		if (estaActivadaLaUbicacion()) {
			modoDeDesplazamiento.caminando(this);
		}
	}

	public boolean estaActivadaLaUbicacion() {
		return deteccionDeDesplazamiento.seEncuentraActivada();
	}

	public boolean estaDentroDeZonaEstacionamiento() {
		return this.celularAsociado.estaDentroDeZonaEstacionamiento();
	}

	public ModoDesplazamiento getModoDeDesplazamiento() {
		return modoDeDesplazamiento;
	}

	public void setModoDeDesplazamiento(ModoDesplazamiento modoDeDesplazamiento) {
		this.modoDeDesplazamiento = modoDeDesplazamiento;
	}

	public String getPatenteAsociada() {
		return patenteAsociada;
	}

	public void setPatenteAsociada(String patenteAsociada) {
		this.patenteAsociada = patenteAsociada;
	}

	public void iniciarEstacionamiento() throws Exception {

		try {

			verificarZonaEstacionamiento();
			verificarSaldoSuficiente();

			SEM.puedeEstacionar(this.getPatente(), LocalTime.now(), this.getHoraMaximaFinEstacionamiento());

			// Si no se lanza la excepción, continuar con la creación del estacionamiento
			EstacionamientoApp estacionamiento = new EstacionamientoApp(this, celularAsociado.getNroCelular(),
					this.getPatente());
			this.SEM.solicitudDeEstacionamientoApp(estacionamiento);
			this.enviarDetallesInicioEstacionamiento(estacionamiento);

		} catch (Exception e) {

			this.notificarUsuario(e.getMessage());

		}
	}

	public LocalTime getHoraMaximaFinEstacionamiento() {

		LocalTime horaMáximaPermitidaSaldo = LocalTime.of(this.getHorasMaximasPermitidasEstacionamiento(), 0);

		LocalTime horaMáximaPermitidaDelDía = SistemaEstacionamiento.getHoraLaboralFin();

		return horaMáximaPermitidaSaldo.isBefore(horaMáximaPermitidaDelDía) ? horaMáximaPermitidaSaldo
				: horaMáximaPermitidaDelDía;
	}

	public int getHorasMaximasPermitidasEstacionamiento() {
		double saldoCelular = this.celularAsociado.getSaldo();
		int precioPorHora = SistemaEstacionamiento.getPrecioporhora();
		return (int) Math.round(saldoCelular / precioPorHora);
	}

	public void notificarUsuario(String msg) {
		this.modoNotificacion.notificar(this.celularAsociado, msg);
	}

	public void finalizarEstacionamiento() throws Exception {
		String Numerocelular = this.celularAsociado.getNroCelular();
		CelularDeUsuario celular = this.celularAsociado;
		Estacionamiento est = this.SEM.getEstacionamiento(Numerocelular);

		this.SEM.finalizarEstacionamiento(Numerocelular);
		this.SEM.cobrarPorEstacionamiento(est, celular);
		this.enviarDetallesFinEstacionamiento(est);
		this.SEM.notificarSistemaAlertasFinEstacionamiento(est);
	}

	// LOGICA DE Información al Usuario en Estacionamiento vía App
	public void enviarDetallesInicioEstacionamiento(EstacionamientoApp estacionamiento) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String inicio = "Hora de inicio del estacionamiento: "
				+ estacionamiento.getInicioEstacionamiento().format(formatter);
		String fin = "Hora máxima fin del estacionamiento respecto al saldo: "
				+ this.getHoraMaximaFinEstacionamiento().format(formatter);
		String msg = inicio + "\n" + fin;
		this.notificarUsuario(msg);
	}

	public void enviarDetallesFinEstacionamiento(Estacionamiento estacionamiento) throws Exception {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		String inicio = "Hora de inicio del estacionamiento: "

				+ estacionamiento.getInicioEstacionamiento().format(formatter);

		String fin = "Hora máxima fin del estacionamiento: "

				+ estacionamiento.getFinEstacionamiento().format(formatter);

		String duracion = "La duración en horas del estacionamiento fué de " + estacionamiento.getDuracionEnHoras();

		String precio = "El costo del estacionamiento fué de: " + estacionamiento.getCostoEstacionamiento();

		String msg = inicio + "\n" + fin + "\n" + duracion + "\n" + precio;

		this.notificarUsuario(msg);
	}

	public void verificarSaldoSuficiente() throws Exception {
		if (celularAsociado.getSaldo() < 40) {
			throw new Exception("No tiene saldo suficiente para estacionar.");
		}
	}

	public void verificarZonaEstacionamiento() throws Exception {
		if (!this.estaDentroDeZonaEstacionamiento()) {
			throw new Exception("No está en una zona de estacionamiento");
		}
	}

	public boolean tieneEstacionamientoVigente() {
		return SEM.poseeEstacionamientoVigente(patenteAsociada);
	}

	/*
	 * public boolean validarSiEsPosibleEstacionar() { return
	 * this.SEM.obtenerSaldoCelular(this.celularAsociado.getNroCelular()) >= 40 &&
	 * this.seEncuentraEnFranjaHoraria(); }
	 */

	public void setModoEstacionamiento(ModoEstacionamiento modo) {
		this.modoEstacionamiento = modo;
	}

	public void setModoNotificacion(ModoNotificaciones modo) {
		this.modoNotificacion = modo;
	}

	public ModoEstacionamiento getModoEstacionamiento() {
		return this.modoEstacionamiento;
	}

	public void setUbicacionEstacionamiento(Point ubicacion) {
		this.ubicacionUltimoEstacionamiento = ubicacion;
	}

	public Point getUbicacionEstacionamiento() {
		return this.ubicacionUltimoEstacionamiento;
	}

	public Point getUbicacionActual() {
		return this.celularAsociado.getUbicacion();
	}

	public String getPatente() {
		return this.patenteAsociada;
	}

	public boolean validarMismoPuntoGeografico() {

		return this.getUbicacionActual() == this.getUbicacionEstacionamiento();
	}

	/*
	 * public void setNombreUltimaZonaEstacionamiento() {
	 * this.modoDeDesplazamiento.setNombreZonaEstacionamientoActual(
	 * this.getNombreEstacionamientoVigenteActual() ); }
	 * 
	 * 
	 * public String getNombreUltimaZonaEstacionamiento() { return
	 * this.modoDeDesplazamiento.getNombreZonaEstacionamientoActual(); }
	 * 
	 * 
	 * public String getNombreEstacionamientoVigenteActual() { // Colocar excepción
	 * en el caso de que la respuesta de. getEstacionamiento de SEM sea un
	 * NullPointerExcepcion... return this.SEM.getEstacionamiento(
	 * this.celularAsociado.getNroCelular() ).getIdentificadorEstacionamiento(); }
	 */

}
