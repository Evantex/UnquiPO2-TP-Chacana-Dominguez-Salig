package tp.po2.sem.estacionamiento;

import tp.po2.sem.puntoDeVenta.*;

import java.time.Duration;

public class EstacionamientoCompraPuntual extends Estacionamiento {
	private CompraPuntual compraAsociada;

	public EstacionamientoCompraPuntual() {}

	public EstacionamientoCompraPuntual(Duration duracion, String patenteVehiculo, CompraPuntual compraAsociada) {
		super(duracion, patenteVehiculo);
		this.compraAsociada = compraAsociada;
	}

	public CompraPuntual getCompraAsociada() {
		return compraAsociada;
	}

	public void setCompraAsociada(CompraPuntual compraAsociada) {
		this.compraAsociada = compraAsociada;
	}


}
