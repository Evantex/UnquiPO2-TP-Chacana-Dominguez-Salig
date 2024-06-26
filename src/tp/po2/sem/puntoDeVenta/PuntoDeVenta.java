package tp.po2.sem.puntoDeVenta;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Set;

import tp.po2.sem.ZonaDeEstacionamiento.ZonaDeEstacionamiento;
import tp.po2.sem.estacionamiento.Estacionamiento;
import tp.po2.sem.estacionamiento.EstacionamientoCompraPuntual;
import tp.po2.sem.sistemaEstacionamiento.EstadoSistema;
import tp.po2.sem.sistemaEstacionamiento.SistemaEstacionamiento;

public class PuntoDeVenta { // responsabilidad de generar objetos compras
	private String identificadorPuntoDeVenta;
	private SistemaEstacionamiento sem;
	private ZonaDeEstacionamiento zona;

	public PuntoDeVenta(String identificadorPuntoDeVenta, SistemaEstacionamiento sem, ZonaDeEstacionamiento zona) {
		super();
		this.identificadorPuntoDeVenta = identificadorPuntoDeVenta;
		this.sem = sem;
		this.zona = zona;

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

	public void registrarEstacionamientoCompraPuntual(String patente, Duration cantidadDeHoras)  {
		
		sem.solicitudDeEstacionamientoCompraPuntual(patente, cantidadDeHoras, this);
		
	}
		
		

	public void cargarSaldoEnCelular(String numeroCelular, double saldo) {
		CompraRecargaCelular CompraRecargaCelular = new CompraRecargaCelular(this, numeroCelular, saldo);
		sem.cargarCelular(numeroCelular, saldo);

		sem.registrarCompra(CompraRecargaCelular);
	}

	

}