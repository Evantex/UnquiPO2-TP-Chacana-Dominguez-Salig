package tp.po2.sem.puntoDeVenta;

import java.time.LocalDate;
import java.time.LocalDateTime;

public abstract class Compra {
	private static int proximoNumeroDeControl = 1; // Variable estática para el número de control incremental

	protected PuntoDeVenta puntoDeVenta;
	protected String numeroDeControl;
	protected LocalDate fechaCompra;

	// Constructor sin parámetros
	public Compra() {
		this.numeroDeControl = generarNumeroDeControl();
		
	}

	// Método para generar el número de control incremental
	private synchronized String generarNumeroDeControl() {
		return String.valueOf(proximoNumeroDeControl++);
	}

	// Getters y Setters
	public PuntoDeVenta getPuntoDeVenta() {
		return puntoDeVenta;
	}

	public void setPuntoDeVenta(PuntoDeVenta puntoDeVenta) {
		this.puntoDeVenta = puntoDeVenta;
	}

	public String getNumeroDeControl() {
		return numeroDeControl;
	}

	public LocalDate getFechaCompra() {
		return fechaCompra;
	}


	public abstract boolean esCompraPuntual();

	public abstract boolean esCompraRecargaCelular();

}
