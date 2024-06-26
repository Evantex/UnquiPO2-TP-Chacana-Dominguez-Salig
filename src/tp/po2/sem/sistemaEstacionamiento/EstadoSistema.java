package tp.po2.sem.sistemaEstacionamiento;


import java.time.Duration;

import tp.po2.sem.app.App;
import tp.po2.sem.puntoDeVenta.PuntoDeVenta;

public interface EstadoSistema {
	

	boolean esEstadoAbierto();

	void registrarEstacionamientoCompraPuntual(SistemaEstacionamiento sistemaEstacionamiento, String patente,
			Duration cantidadDeHoras, PuntoDeVenta puntoDeVenta);

	void registrarEstacionamientoApp(SistemaEstacionamiento sistemaEstacionamiento, App app, String nroCelular,
			String patente);
	
}
