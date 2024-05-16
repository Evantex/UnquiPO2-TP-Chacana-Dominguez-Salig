package tp.po2.sem.ZonaDeEstacionamiento;

import java.util.List;

import tp.po2.sem.inspector.Inspector;
import tp.po2.sem.puntoDeVenta.PuntoDeVenta;

public class ZonaDeEstacionamiento {

	private String identificardorDeZona;
	private Inspector inspectorAsignado;
	private List<PuntoDeVenta> puntosDeVenta;

	public String getIdentificardorDeZona() {
		return identificardorDeZona;
	}

	public void setIdentificardorDeZona(String identificardorDeZona) {
		this.identificardorDeZona = identificardorDeZona;
	}

	public Inspector getInspectorAsignado() {
		return inspectorAsignado;
	}

	public void setInspectorAsignado(Inspector inspectorAsignado) {
		this.inspectorAsignado = inspectorAsignado;
	}

	public List<PuntoDeVenta> getPuntosDeVenta() {
		return puntosDeVenta;
	}

	public void setPuntosDeVenta(List<PuntoDeVenta> puntosDeVenta) {
		this.puntosDeVenta = puntosDeVenta;
	}

}
