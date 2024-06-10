package tp.po2.sem.puntoDeVenta;

public class CompraRecargaCelular extends Compra {
	private PuntoDeVenta puntoDeVenta;
	private String numeroDecelular;
	private int montoSaldo;

	public CompraRecargaCelular(PuntoDeVenta puntoDeVenta, String numeroDecelular, int montoSaldo) {
		super();
		this.puntoDeVenta = puntoDeVenta;
		this.numeroDecelular = numeroDecelular;
		this.montoSaldo = montoSaldo;
	}

	public PuntoDeVenta getPuntoDeVenta() {
		return puntoDeVenta;
	}

	public void setPuntoDeVenta(PuntoDeVenta puntoDeVenta) {
		this.puntoDeVenta = puntoDeVenta;
	}

	public int getMontoSaldo() {
		return montoSaldo;
	}

	public void setMontoSaldo(int montoSaldo) {
		this.montoSaldo = montoSaldo;
	}

	public String getNumeroDecelular() {
		return numeroDecelular;
	}

	public void setNumeroDecelular(String numeroDecelular) {
		this.numeroDecelular = numeroDecelular;
	}

}
