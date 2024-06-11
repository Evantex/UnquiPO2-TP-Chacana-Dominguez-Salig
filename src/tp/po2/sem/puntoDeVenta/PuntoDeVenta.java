package tp.po2.sem.puntoDeVenta;

import java.time.Duration;
import java.util.Set;

import tp.po2.sem.estacionamiento.Estacionamiento;
import tp.po2.sem.estacionamiento.EstacionamientoCompraPuntual;
import tp.po2.sem.sistemaEstacionamiento.SistemaEstacionamiento;

public class PuntoDeVenta {
	private String identificadorPuntoDeVenta;
	private SistemaEstacionamiento sem;
	private Set<Compra> setDeCompras;

	public PuntoDeVenta(String identificadorPuntoDeVenta, SistemaEstacionamiento sem, Set<Compra> setDeCompras) {
		super();
		this.identificadorPuntoDeVenta = identificadorPuntoDeVenta;
		this.sem = sem;
		this.setDeCompras = setDeCompras;
	}

	public String getIdentificadorPuntoDeVenta() {
		return identificadorPuntoDeVenta;
	}

	public void setIdentificadorPuntoDeVenta(String identificadorPuntoDeVenta) {
		this.identificadorPuntoDeVenta = identificadorPuntoDeVenta;
	}

	public SistemaEstacionamiento getSem() {
		return sem;
	}

	public void setSem(SistemaEstacionamiento sem) {
		this.sem = sem;
	}

	public Set<Compra> getSetDeCompras() {
		return setDeCompras;
	}

	public void setSetDeCompras(Set<Compra> setDeCompras) {
		this.setDeCompras = setDeCompras;
	}

	public void registrarEstacionamiento(String patente, Duration cantidadDeHoras) {
		CompraPuntual compraPuntual = new CompraPuntual(this, cantidadDeHoras);

		sem.registrarEstacionamientoCompraPuntual(patente, cantidadDeHoras, compraPuntual);

		setDeCompras.add(compraPuntual);
	}

	public void cargarSaldoEnCelular(String numeroCelular, double saldo) {
		CompraRecargaCelular CompraRecargaCelular = new CompraRecargaCelular(this, numeroCelular, saldo);
		sem.cargarCelular(numeroCelular, saldo);

		setDeCompras.add(CompraRecargaCelular);
	}

	public int getCantidadCompras() {

		return setDeCompras.size();
	}

}