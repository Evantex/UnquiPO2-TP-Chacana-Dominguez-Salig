package tp.po2.sem.ZonaDeEstacionamiento;

import java.util.Set;

import tp.po2.sem.inspector.Inspector;
import tp.po2.sem.puntoDeVenta.PuntoDeVenta;

public class ZonaDeEstacionamiento {

	private String identificardorDeZona;
	private Inspector inspectorAsignado;
	private Set<PuntoDeVenta> puntosDeVenta;

	public ZonaDeEstacionamiento(String identificardorDeZona, Inspector inspectorAsignado,
			Set<PuntoDeVenta> puntosDeVenta) {
		super();
		this.identificardorDeZona = identificardorDeZona;
		this.inspectorAsignado = inspectorAsignado;
		this.puntosDeVenta = puntosDeVenta;
	}

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

	public Set<PuntoDeVenta> getPuntosDeVenta() {
		return puntosDeVenta;
	}

	public void setPuntosDeVenta(Set<PuntoDeVenta> puntosDeVenta) {
		this.puntosDeVenta = puntosDeVenta;
	}

	public void agregarPuntoDeVenta(PuntoDeVenta p) {
		puntosDeVenta.add(p);
	}

	public int cantidadDePuntosDeVenta() {

		return puntosDeVenta.size();

	}

	public void removerPuntoDeVenta(PuntoDeVenta p) {
		puntosDeVenta.remove(p);

	}
}
