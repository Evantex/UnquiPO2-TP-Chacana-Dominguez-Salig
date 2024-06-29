package tp.po2.sem.puntoDeVenta;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class CompraPuntual extends Compra {

	private int horasCompradas;
	private LocalTime horaInicio;
	private LocalTime horaFin;


	// Constructor con parámetros
	public CompraPuntual(PuntoDeVenta puntoDeVenta, int horasCompradas, LocalDate fechaCompra, LocalTime horaInicio, LocalTime horaFin) {
		this.puntoDeVenta = puntoDeVenta;
        this.horasCompradas = horasCompradas;
        this.fechaCompra = fechaCompra;
        this.setHoraInicio(horaInicio);
        this.setHoraFin(horaFin);
    }

	// Getters y setters
	public int getHorasCompradas() {
		return horasCompradas;
	}

	public void setHorasCompradas(int cantidadDeHorasCompradas) {
		this.horasCompradas = cantidadDeHorasCompradas;
	}
	
	
	// Distincion
	public boolean esCompraPuntual() {
		return true;
	}

	public boolean esCompraRecargaCelular() {
		return false;
	}

	public LocalTime getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(LocalTime horaInicio) {
		this.horaInicio = horaInicio;
	}

	public LocalTime getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(LocalTime horaFin) {
		this.horaFin = horaFin;
	}

}
