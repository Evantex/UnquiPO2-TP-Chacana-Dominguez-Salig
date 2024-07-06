package tp.po2.sem.inspector;

import tp.po2.sem.ZonaDeEstacionamiento.ZonaDeEstacionamiento;
import tp.po2.sem.sistemaEstacionamiento.SistemaEstacionamiento;

public class Inspector {
	private String nombreInspector;
	private SistemaEstacionamiento sem;
	private ZonaDeEstacionamiento zonaAsignada;



	public Inspector(String nombreInspector, SistemaEstacionamiento sem, ZonaDeEstacionamiento zona) {
		super();
		this.nombreInspector = nombreInspector;
		this.sem = sem;
		this.zonaAsignada = zona;
	}

	public String getNombreInspector() {
		return nombreInspector;
	}

	public void setNombreInspector(String nombreInspector) {
		this.nombreInspector = nombreInspector;
	}

	public SistemaEstacionamiento getSistemadeEstacionamientoMedido() {
		return sem;
	}

	public void setSistemadeEstacionamientoMedido(SistemaEstacionamiento sistemadeEstacionamientoMedido) {
		this.sem = sistemadeEstacionamientoMedido;
	}

	public ZonaDeEstacionamiento getZonaAsignada() {
		return zonaAsignada;
	}

	public void setZonaAsignada(ZonaDeEstacionamiento zonaAsignada) {
		this.zonaAsignada = zonaAsignada;
	}
	
	public boolean verificarPatente(String patente) {
		
		boolean tieneEstacionamientoVigente = sem.poseeEstacionamientoVigente(patente);
		
		if (!tieneEstacionamientoVigente) {
			this.notificarInfraccion(patente);
		}
		
		return tieneEstacionamientoVigente;
		
	}

	public void notificarInfraccion(String patente) {

		this.sem.registrarInfraccion(patente, this);

	}
	
}
