package tp.po2.sem.sistemaEstacionamiento;

import java.util.Set;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
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
	private HashMap<String, List<Infraccion>> infraccionesPorPatente;
	private LocalTime horaLaboralInicio;
	private LocalTime horaLaboralFin;

	public SistemaEstacionamiento(Set<Estacionamiento> estacionamientos) {
		super();
		this.estacionamientos = estacionamientos;
		this.saldoCelular = new HashMap<>();
		this.infraccionesPorPatente = new HashMap<>();
		this.horaLaboralInicio = LocalTime.of(7, 0); // 7:00 AM
		this.horaLaboralFin = LocalTime.of(20, 0); // 8:00 PM
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
				 compraAsociada);
		estacionamientos.add(estacionamiento);
	}

	public Integer getCantidadEstacionamientos() {
		return estacionamientos.size();
	}

	public boolean poseeEstacionamientoVigente(String identificadorEstacionamiento) 
	{
		return true;
	}

	public void registrarInfraccion(String patente, Inspector inspector) {
		
        LocalDateTime fechaYHoraActual = LocalDateTime.now();
        ZonaDeEstacionamiento zonaInspector = inspector.getZonaAsignada();
        Infraccion infraccion = new Infraccion(patente, fechaYHoraActual, inspector, zonaInspector);

        // Obtener la lista de infracciones asociada con la patente
        // El .get es un metodo por default del hashmap que te trae el value asociada a esa key
        List<Infraccion> infracciones = infraccionesPorPatente.get(patente);

        // Si no tenia infracciones, se crea una nueva lista de infracciones y la agrega al HashMap
        if (infracciones == null) {
            infracciones = new ArrayList<>();
            infraccionesPorPatente.put(patente, infracciones);
        }

        // Agrega la infracción a la lista si ya tenia infracciones anteriores.
        infracciones.add(infraccion);
    }

    // Método para obtener las infracciones por patente
    public List<Infraccion> obtenerInfraccionesDeLaPatente(String patente) {
        return infraccionesPorPatente.get(patente);
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
	
	
	public void finalizarEstacionamiento(String identificadorEstacionamiento)
	{
	    this.estacionamientos.stream()
	        .filter(e -> e.estaVigente() && e.getIdentificadorEstacionamiento().equals(identificadorEstacionamiento))
	        .findAny()
	        .ifPresent(estacionamiento -> estacionamiento.setFinEstacionamiento());
	}

	public Estacionamiento getEstacionamiento(String identificadorEstacionamiento) throws Exception {
	    return this.estacionamientos.stream()
	            .filter(e -> e.estaVigente() && e.getIdentificadorEstacionamiento().equals(identificadorEstacionamiento))
	            .findAny()
	            .orElseThrow(() -> new Exception("El estacionamiento no existe o no está vigente"));
	}
	
}
