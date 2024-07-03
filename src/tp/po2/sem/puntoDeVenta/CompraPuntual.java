package tp.po2.sem.puntoDeVenta;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class CompraPuntual extends Compra {

	private Duration  horasCompradas;
	private LocalTime horaInicioEstacionamiento;
	private LocalTime horaFinEstacionamiento;

	public CompraPuntual() {
	}

	// Constructor con parámetros
	public CompraPuntual(PuntoDeVenta puntoDeVenta, LocalDate fechaCompra, Duration horasCompradas) {
		this.puntoDeVenta = puntoDeVenta;
		this.horasCompradas = horasCompradas;
		this.fechaCompra = fechaCompra;
		this.horaInicioEstacionamiento = LocalTime.now(); // clase reloj?
		this.horaFinEstacionamiento = this.getHoraInicio().plus(horasCompradas); // chequear

	}

	public Duration getHorasCompradas() {
		return this.horasCompradas;
	}

	public void setHorasCompradas(Duration horasCompradas) {
		this.horasCompradas = horasCompradas;
	}

	// Distincion
	public boolean esCompraPuntual() {
		return true;
	}

	public boolean esCompraRecargaCelular() {
		return false;
	}

	public LocalTime getHoraFin() {

		return this.horaFinEstacionamiento;
	}

	public LocalTime getHoraInicio() {

		return this.horaInicioEstacionamiento;
	}

}
