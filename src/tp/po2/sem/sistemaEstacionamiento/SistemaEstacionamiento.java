package tp.po2.sem.sistemaEstacionamiento;

import java.util.Set;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import tp.po2.sem.ZonaDeEstacionamiento.ZonaDeEstacionamiento;
import tp.po2.sem.estacionamiento.Estacionamiento;
import tp.po2.sem.estacionamiento.EstacionamientoCompraPuntual;
import tp.po2.sem.inspector.Infraccion;
import tp.po2.sem.inspector.Inspector;
import tp.po2.sem.puntoDeVenta.CompraPuntual;

public class SistemaEstacionamiento {
	private Set<Estacionamiento> estacionamientos;
	private HashMap<String, Double> saldoCelular = new HashMap<>();
	private Set<Infraccion> infracciones;
	private LocalTime horaLaboralInicio;
	private LocalTime horaLaboralFin;

	public SistemaEstacionamiento(Set<Estacionamiento> estacionamientos, HashMap<String, Double> saldoCelular,
			Set<Infraccion> infracciones) {
		super();
		this.estacionamientos = estacionamientos;
		this.saldoCelular = saldoCelular;
		this.infracciones = infracciones;
		this.horaLaboralInicio = LocalTime.of(7, 0); // 7:00 AM
		this.horaLaboralFin = LocalTime.of(20, 0); // 8:00 PM
	}

	public SistemaEstacionamiento(Set<Estacionamiento> estacionamientos) {
		this.estacionamientos = estacionamientos;
	}

	/**
	 * Carga saldo a un número de celular. Si el celular ya tiene saldo, suma el
	 * saldo nuevo al existente.
	 *
	 * @param nroCelular El número de celular al que se cargará el saldo.
	 * @param saldo      El monto de saldo a cargar.
	 */
	public void cargarCelular(String nroCelular, double saldo) {
		// Obtiene el saldo actual del número de celular o 0.0 si no existe,
		// y luego suma el saldo nuevo al saldo actual.
		saldoCelular.put(nroCelular, saldoCelular.getOrDefault(nroCelular, 0.0) + saldo);
	}

	/**
	 * Obtiene el saldo actual de un número de celular.
	 *
	 * @param nroCelular El número de celular cuyo saldo se desea consultar.
	 * @return El saldo actual del número de celular, o 0.0 si no existe.
	 */
	public double obtenerSaldoCelular(String nroCelular) {
		return saldoCelular.getOrDefault(nroCelular, 0.0);
	}

	public void registrarEstacionamiento(Estacionamiento unEstacionamiento) {
		estacionamientos.add(unEstacionamiento);
	}

	public void registrarEstacionamientoCompraPuntual(String patente, Duration horasCompradas,
			CompraPuntual compraAsociada, String nombreZonaEstacionamiento) {
		EstacionamientoCompraPuntual estacionamiento = new EstacionamientoCompraPuntual(horasCompradas, patente,
				nombreZonaEstacionamiento, compraAsociada);
		estacionamientos.add(estacionamiento);
	}

	public Integer getCantidadEstacionamientos() {
		return estacionamientos.size();
	}

	public boolean poseeEstacionamientoVigente(String patente) {
		return true;
	}

	public void registrarInfraccion(String patente, Inspector inspector) {

		LocalDateTime fechaYHoraActual = LocalDateTime.now();
		ZonaDeEstacionamiento zonaInspector = inspector.getZonaAsignada();
		Infraccion infraccion = new Infraccion(patente, fechaYHoraActual, inspector, zonaInspector);

		infracciones.add(infraccion);

	}

	public Set<Estacionamiento> getEstacionamientos() {
		return estacionamientos;
	}

	public void setEstacionamientos(Set<Estacionamiento> estacionamientos) {
		this.estacionamientos = estacionamientos;
	}

	public HashMap<String, Double> getSaldoCelular() {
		return saldoCelular;
	}

	public void setSaldoCelular(HashMap<String, Double> saldoCelular) {
		this.saldoCelular = saldoCelular;
	}

	public Set<Infraccion> getInfracciones() {
		return infracciones;
	}

	public void setInfracciones(Set<Infraccion> infracciones) {
		this.infracciones = infracciones;
	}

	public LocalTime getHoraLaboralInicio() {
		return horaLaboralInicio;
	}

	public void setHoraLaboralInicio(LocalTime horaLaboralInicio) {
		this.horaLaboralInicio = horaLaboralInicio;
	}

	public LocalTime getHoraLaboralFin() {
		return horaLaboralFin;
	}

	public void setHoraLaboralFin(LocalTime horaLaboralFin) {
		this.horaLaboralFin = horaLaboralFin;
	}
}
