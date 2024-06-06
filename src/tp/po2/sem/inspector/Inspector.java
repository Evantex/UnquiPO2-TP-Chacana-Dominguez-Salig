package tp.po2.sem.inspector;

import tp.po2.sem.sistemaEstacionamiento.SistemaEstacionamiento;

public class Inspector {
	private String nombreInspector;
	private SistemaEstacionamiento sistemadeEstacionamientoMedido;
	
	public Inspector(String nombreInspector, SistemaEstacionamiento sistemadeEstacionamientoMedido) {
		super();
		this.nombreInspector = nombreInspector;
		this.sistemadeEstacionamientoMedido = sistemadeEstacionamientoMedido;
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

	public boolean verificarPatente(String patente) {
		return false;
	}
}
