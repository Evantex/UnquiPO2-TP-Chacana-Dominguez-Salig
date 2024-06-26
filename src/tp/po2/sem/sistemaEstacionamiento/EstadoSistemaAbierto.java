package tp.po2.sem.sistemaEstacionamiento;


import java.time.Duration;

import tp.po2.sem.app.App;
import tp.po2.sem.estacionamiento.EstacionamientoApp;
import tp.po2.sem.estacionamiento.EstacionamientoCompraPuntual;
import tp.po2.sem.puntoDeVenta.CompraPuntual;
import tp.po2.sem.puntoDeVenta.PuntoDeVenta;

public class EstadoSistemaAbierto implements EstadoSistema {
	
	@Override
	public boolean esEstadoAbierto() {
		
		return true;
	}


	@Override
	public void registrarEstacionamientoCompraPuntual(SistemaEstacionamiento sem, String patente,
			Duration cantidadDeHoras, PuntoDeVenta puntoDeVenta) {
		
		CompraPuntual compraAsociada = new CompraPuntual(puntoDeVenta, cantidadDeHoras);
		EstacionamientoCompraPuntual unEstacionamiento = new EstacionamientoCompraPuntual(cantidadDeHoras, patente, compraAsociada);
		
		sem.addEstacionamiento(unEstacionamiento);
		sem.registrarCompra(compraAsociada);
		
	}



	@Override
	public void registrarEstacionamientoApp(SistemaEstacionamiento sem, App app,
			String nroCelular, String patente) {
		
		
		EstacionamientoApp unEstacionamiento = new EstacionamientoApp(app, nroCelular, patente);
		
		sem.addEstacionamiento(unEstacionamiento);
		app.enviarDetallesInicioEstacionamiento(unEstacionamiento);
		
	}


}
