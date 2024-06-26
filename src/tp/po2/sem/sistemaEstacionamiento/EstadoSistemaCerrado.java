package tp.po2.sem.sistemaEstacionamiento;

import java.time.Duration;
import java.time.LocalTime;

import tp.po2.sem.app.App;
import tp.po2.sem.puntoDeVenta.PuntoDeVenta;

public class EstadoSistemaCerrado implements EstadoSistema {

	

	@Override
	public boolean esEstadoAbierto() {
		return false;
	}

	@Override
	public void registrarEstacionamientoCompraPuntual(SistemaEstacionamiento sistemaEstacionamiento, String patente,
			Duration cantidadDeHoras, PuntoDeVenta puntoDeVenta) {
		
		//Si no esta en horario, no hace nada
		
	}

	@Override
	public void registrarEstacionamientoApp(SistemaEstacionamiento sistemaEstacionamiento, App app,
			String celular, String patente) {
		
		//Si no esta en horario, no hace nada
		
	}

	@Override
	public void verificarTransicion(SistemaEstacionamiento sem) {
		
		 LocalTime ahora = sem.getHoraActual();
	        if (!ahora.isBefore(sem.getHoraLaboralInicio()) && !ahora.isAfter(sem.getHoraLaboralFin())) {
	            sem.setEstadoSistema(new EstadoSistemaAbierto());
	        }
		
	}

	

}
