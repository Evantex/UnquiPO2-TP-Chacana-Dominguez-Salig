package tp.po2.sem.puntoDeVenta;

public class CompraRecargaCelular extends Compra {
	private PuntoDeVenta puntoDeVenta;
	private String numeroDecelular;
	private double montoSaldo;

	public CompraRecargaCelular(PuntoDeVenta puntoDeVenta, String numeroDecelular, double montoSaldo) {
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

	public double getMontoSaldo() {
		return montoSaldo;
	}

	public void setMontoSaldo(double montoSaldo) {
		this.montoSaldo = montoSaldo;
	}

	public String getNumeroDecelular() {
		return numeroDecelular;
	}

	public void setNumeroDecelular(String numeroDecelular) {
		this.numeroDecelular = numeroDecelular;
	}
	
	public boolean esCompraPuntual() {
		return false;
	}

	public boolean esCompraRecargaCelular() {
		return true;
	}

}
