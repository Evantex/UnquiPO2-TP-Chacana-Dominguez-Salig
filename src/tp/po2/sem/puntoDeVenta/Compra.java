package tp.po2.sem.puntoDeVenta;

import java.time.LocalDateTime;

public abstract class Compra {
	private static int proximoNumeroDeControl = 1; // Variable estática para el número de control incremental

	private PuntoDeVenta puntoDeVenta;
	private String numeroDeControl;

	private LocalDateTime fechaHoraCompra;

	// Constructor sin parámetros
	public Compra() {
		this.numeroDeControl = generarNumeroDeControl();
		this.fechaHoraCompra = LocalDateTime.now();
	}

	// Constructor con parámetros
	public Compra(PuntoDeVenta puntoDeVenta) {
		this.puntoDeVenta = puntoDeVenta;
		this.numeroDeControl = generarNumeroDeControl();
		this.fechaHoraCompra = LocalDateTime.now();
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

	public LocalDateTime getFechaHoraCompra() {
		return fechaHoraCompra;
	}

	public void setFechaHoraCompra(LocalDateTime fechaHoraCompra) {
		this.fechaHoraCompra = fechaHoraCompra;
	}
}
