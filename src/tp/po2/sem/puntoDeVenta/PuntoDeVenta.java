package tp.po2.sem.puntoDeVenta;

import java.time.Duration;
import java.util.Set;

import tp.po2.sem.ZonaDeEstacionamiento.ZonaDeEstacionamiento;
import tp.po2.sem.estacionamiento.Estacionamiento;
import tp.po2.sem.estacionamiento.EstacionamientoCompraPuntual;
import tp.po2.sem.sistemaEstacionamiento.SistemaEstacionamiento;

public class PuntoDeVenta {
	private String identificadorPuntoDeVenta;
	private SistemaEstacionamiento sem;
	private Set<Compra> setDeCompras;
	private ZonaDeEstacionamiento zona;

	public PuntoDeVenta(String identificadorPuntoDeVenta, SistemaEstacionamiento sem, Set<Compra> setDeCompras,ZonaDeEstacionamiento zona) {
		super();
		this.identificadorPuntoDeVenta = identificadorPuntoDeVenta;
		this.sem = sem;
		this.zona =zona;
		this.setDeCompras = setDeCompras;
	}

	public ZonaDeEstacionamiento getZona() {
		return zona;
	}

	public void setZona(ZonaDeEstacionamiento zona) {
		this.zona = zona;
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
		try {
			this.verificarHorarioLaboral();
			this.verificarSiLaCantidadDeHorasEsValidaEnEsteHorario();
		CompraPuntual compraPuntual = new CompraPuntual(this, cantidadDeHoras);
		String IdentificardorDeZonaDelPuntoDeVenta  = this.getZona().getIdentificardorDeZona();

		sem.registrarEstacionamientoCompraPuntual(patente, cantidadDeHoras, compraPuntual,IdentificardorDeZonaDelPuntoDeVenta);

		setDeCompras.add(compraPuntual);
		}
		catch (Exception e) {
	}
	}
	private void verificarHorarioLaboral() {
		return sem.
		
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