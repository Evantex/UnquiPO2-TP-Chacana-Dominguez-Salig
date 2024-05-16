package tp.po2.sem.puntoDeVenta;

import tp.po2.sem.sistemaEstacionamiento.SistemaEstacionamiento;

public class PuntoDeVenta {
	private String identificadorPuntoDeVenta;
	private SistemaEstacionamiento sem;

	public String getIdentificadorPuntoDeVenta() {
		return identificadorPuntoDeVenta;
	}

	public void setIdentificadorPuntoDeVenta(String identificadorPuntoDeVenta) {
		this.identificadorPuntoDeVenta = identificadorPuntoDeVenta;
	}

	public SistemaEstacionamiento getSem() {
		return sem;
	}

	public void setSem(SistemaEstacionamiento sem) {
		this.sem = sem;
	}

}
