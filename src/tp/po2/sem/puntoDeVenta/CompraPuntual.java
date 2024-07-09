package tp.po2.sem.puntoDeVenta;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class CompraPuntual extends Compra {

	private Duration horasCompradas;
	private LocalTime horaInicioEstacionamiento;
	private LocalTime horaFinEstacionamiento;

	public CompraPuntual() {
	}

	// Constructor con parámetros
	public CompraPuntual(PuntoDeVenta puntoDeVenta, LocalDate fechaCompra, LocalTime horaInicio,
			Duration horasCompradas) {
		super();
		this.puntoDeVenta = puntoDeVenta;
		this.horasCompradas = horasCompradas;
		this.fechaCompra = fechaCompra;
		this.horaInicioEstacionamiento = horaInicio;
		this.horaFinEstacionamiento = this.getHoraInicio().plus(horasCompradas);

	}

	public LocalTime getHoraInicioEstacionamiento() {
		return horaInicioEstacionamiento;
	}

	public void setHoraInicioEstacionamiento(LocalTime horaInicioEstacionamiento) {
		this.horaInicioEstacionamiento = horaInicioEstacionamiento;
	}

	public LocalTime getHoraFinEstacionamiento() {
		return horaFinEstacionamiento;
	}

	public void setHoraFinEstacionamiento(LocalTime horaFinEstacionamiento) {
		this.horaFinEstacionamiento = horaFinEstacionamiento;
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
