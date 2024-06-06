package tp.po2.sem.inspector;

import tp.po2.sem.ZonaDeEstacionamiento.ZonaDeEstacionamiento;
import tp.po2.sem.sistemaEstacionamiento.SistemaEstacionamiento;

public class Inspector {
	private String nombreInspector;
	private SistemaEstacionamiento sistemadeEstacionamientoMedido;
	private ZonaDeEstacionamiento zonaAsignada;
	
	public Inspector() {};
	
	public Inspector(String nombreInspector, SistemaEstacionamiento sem, ZonaDeEstacionamiento zona) {
		super();
		this.nombreInspector = nombreInspector;
		this.sistemadeEstacionamientoMedido = sem;
		this.zonaAsignada = zona;
	}

	public String getNombreInspector() {
		return nombreInspector;
	}

	public void setNombreInspector(String nombreInspector) {
		this.nombreInspector = nombreInspector;
	}

	public SistemaEstacionamiento getSistemadeEstacionamientoMedido() {
		return sistemadeEstacionamientoMedido;
	}

	public void setSistemadeEstacionamientoMedido(SistemaEstacionamiento sistemadeEstacionamientoMedido) {
		this.sistemadeEstacionamientoMedido = sistemadeEstacionamientoMedido;
	}

	public ZonaDeEstacionamiento getZonaAsignada() {
		return zonaAsignada;
	}

	public void setZonaAsignada(ZonaDeEstacionamiento zonaAsignada) {
		this.zonaAsignada = zonaAsignada;
	}

	public boolean verificarPatente(String patente) {
		return this.sistemadeEstacionamientoMedido.poseeEstacionamientoVigente(patente);
		
	}

	public void registrarInfraccion(String patente) {
		
		Infraccion infraccion = Infraccion(patente, )
		
		this.sistemadeEstacionamientoMedido.
		
	}
}
