package tp.po2.sem.app;

import java.awt.Point;

public class CelularDeUsuario {
	private String nroCelular;
	private Point ubicacion;
	private double saldo;

	public CelularDeUsuario(String nroCelular, double saldo) {
		super();
		this.nroCelular = nroCelular;
		this.saldo = saldo;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public void setNroCelular(String nroCelular) {
		this.nroCelular = nroCelular;
	}

	public void setUbicacion(Point ubicacion) {
		this.ubicacion = ubicacion;
	}

	public void recibirMensaje(String msg) {
	};

	public boolean estaDentroDeZonaEstacionamiento() {
		return true;
	}

	public String getNroCelular() {
		return this.nroCelular;
	}

	public void recibirRecargaDeSaldo(double saldoNuevo) {
		
		saldo += saldoNuevo;
	}

	public Point getUbicacion() {
		
		return this.ubicacion;
	}
	
}
