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
	public Duration gethorasCompradas() {
		return horasCompradas;
	}

	public void sethorasCompradas(Duration cantidadDeHorasCompradas) {
		this.horasCompradas = cantidadDeHorasCompradas;
	}
}
