package tp.po2.sem.puntoDeVenta;

import java.time.LocalDate;
import java.time.LocalDateTime;


public abstract class Compra {
	protected static int proximoNumeroDeControl = 0; // Variable estática para el número de control incremental

	protected PuntoDeVenta puntoDeVenta;
	protected int numeroDeControl;
	protected LocalDateTime fechaCompra;

	// Constructor sin parámetros
	public Compra() {
		this.numeroDeControl = generarNumeroDeControl();

	}


	// Método para generar el número de control incremental
	private synchronized int generarNumeroDeControl() {
		return proximoNumeroDeControl++;
	}

	// Getters y Setters
	public PuntoDeVenta getPuntoDeVenta() {
		return puntoDeVenta;
	}

	public void setPuntoDeVenta(PuntoDeVenta puntoDeVenta) {
		this.puntoDeVenta = puntoDeVenta;
	}

	public int getNumeroDeControl() {
		return numeroDeControl;
	}

	public LocalDateTime getFechaCompra() {
		return fechaCompra;
	}

	public static int getProximoNumeroDeControl() {
		return proximoNumeroDeControl;
	}

	public static void setProximoNumeroDeControl(int proximoNumeroDeControl) {
		Compra.proximoNumeroDeControl = proximoNumeroDeControl;
	}

	public void setFechaCompra(LocalDateTime fechaCompra) 
	{
		this.fechaCompra = fechaCompra;
	}

	public abstract boolean esCompraPuntual();

	public abstract boolean esCompraRecargaCelular();

}
