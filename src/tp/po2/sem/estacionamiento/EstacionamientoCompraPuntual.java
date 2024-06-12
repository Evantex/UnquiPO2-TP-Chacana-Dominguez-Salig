package tp.po2.sem.estacionamiento;

import java.time.Duration;
import java.time.LocalDateTime;

import tp.po2.sem.puntoDeVenta.*;

public class EstacionamientoCompraPuntual extends Estacionamiento {
	private CompraPuntual compraAsociada;
	private Duration duracion;

	public EstacionamientoCompraPuntual(LocalDateTime inicioEstacionamiento, LocalDateTime finEstacionamiento,
			String patenteVehiculo, boolean estaVigente, String nombreZonaEstacionamiento,
			CompraPuntual compraAsociada) {
		super(inicioEstacionamiento, finEstacionamiento, patenteVehiculo, nombreZonaEstacionamiento);
		this.compraAsociada = compraAsociada;
		this.finEstacionamiento = this.inicioEstacionamiento.plus(duracion); // se establece la hora fin en base a la
																				// horas alquiladas
	}

	public CompraPuntual getCompraAsociada() {
		return compraAsociada;
	}

	public void setCompraAsociada(CompraPuntual compraAsociada) {
		this.compraAsociada = compraAsociada;
	}

	@Override
	public boolean estaVigente() {
		return LocalDateTime.now().isBefore(this.finEstacionamiento);
	}

	@Override
	public String getIdentificadorEstacionamiento() {
		// TODO Auto-generated method stub
		return null;
	}

}
