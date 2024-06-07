package tp.po2.sem.inspector;

import java.time.LocalDateTime;

import tp.po2.sem.ZonaDeEstacionamiento.ZonaDeEstacionamiento;

public class Infraccion {
	private String patente;
	private LocalDateTime fechaYHora;
	private Inspector inspector;		//inspector que realizo la infraccion
	private ZonaDeEstacionamiento zona; //zona donde se realizo
	
	public Infraccion(String patente, LocalDateTime fechaYHora, Inspector inspector, ZonaDeEstacionamiento zona) {
		super();
		this.patente = patente;
		this.fechaYHora = fechaYHora;
		this.inspector = inspector;
		this.zona = zona;
	}

	public String getPatente() {
		return patente;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}

	public LocalDateTime getFechaYHora() {
		return fechaYHora;
	}

	public void setFechaYHora(LocalDateTime fechaYHora) {
		this.fechaYHora = fechaYHora;
	}

	public ZonaDeEstacionamiento getZona() {
		return zona;
	}

	public void setZona(ZonaDeEstacionamiento zona) {
		this.zona = zona;
	}

	public Inspector getInspector() {
		return inspector;
	}

	public void setInspector(Inspector inspector) {
		this.inspector = inspector;
	}

	

}
