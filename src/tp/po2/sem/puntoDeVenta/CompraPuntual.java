package tp.po2.sem.puntoDeVenta;

import java.time.Duration;

public class CompraPuntual extends Compra {

	private Duration horasCompradas;

	// Constructor sin parámetros
	public CompraPuntual() {
		super();
	}

	// Constructor con parámetros
	public CompraPuntual(PuntoDeVenta puntoDeVenta, Duration cantidadDeHorasCompradas) {
		super(puntoDeVenta);
		this.horasCompradas = cantidadDeHorasCompradas;
	}

	// Getters y setters
	public Duration getHorasCompradas() {
		return horasCompradas;
	}

	public void setHorasCompradas(Duration cantidadDeHorasCompradas) {
		this.horasCompradas = cantidadDeHorasCompradas;
	}
	
	
	// Distincion
	public boolean esCompraPuntual() {
		return true;
	}

	public boolean esCompraRecargaCelular() {
		return false;
	}

}
