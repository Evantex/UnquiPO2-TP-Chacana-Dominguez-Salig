package tp.po2.sem.sistemaEstacionamiento;

import java.util.Set;
import java.time.Duration;
import java.time.LocalDateTime;
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
	public double obtenerSaldoCelular(String nroCelular) 
	{
		return saldoCelular.getOrDefault(nroCelular, 0.0);
	}

	public void registrarEstacionamiento(Estacionamiento unEstacionamiento) {
		estacionamientos.add(unEstacionamiento);
	}

	public void registrarEstacionamientoCompraPuntual(String patente, Duration horasCompradas,
			CompraPuntual compraAsociada) {
		EstacionamientoCompraPuntual estacionamiento = new EstacionamientoCompraPuntual(horasCompradas, patente,
				compraAsociada);
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

}
