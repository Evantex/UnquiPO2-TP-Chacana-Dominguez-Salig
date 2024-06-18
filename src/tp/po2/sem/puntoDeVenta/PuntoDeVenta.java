package tp.po2.sem.puntoDeVenta;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Set;

import tp.po2.sem.ZonaDeEstacionamiento.ZonaDeEstacionamiento;
import tp.po2.sem.estacionamiento.Estacionamiento;
import tp.po2.sem.estacionamiento.EstacionamientoCompraPuntual;
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

	public void registrarEstacionamientoCompraPuntual(String patente, Duration cantidadDeHoras) throws Exception {
		
		if (!sem.esValidoRegistrarEstacionamiento(cantidadDeHoras))  {
	        throw new Exception("No es un horario apto para procesar el estacionamiento");
	    }
			
			CompraPuntual compraPuntual = new CompraPuntual(this, cantidadDeHoras);

			sem.registrarEstacionamientoCompraPuntual(patente, cantidadDeHoras, compraPuntual);

			sem.registrarCompra(compraPuntual);
		}
		
		

	public void cargarSaldoEnCelular(String numeroCelular, double saldo) {
		CompraRecargaCelular CompraRecargaCelular = new CompraRecargaCelular(this, numeroCelular, saldo);
		sem.cargarCelular(numeroCelular, saldo);

		sem.registrarCompra(CompraRecargaCelular);
	}

	/*
	 * private void verificarSiLaCantidadDeHorasEsValidaEnEsteHorario(Duration
	 * cantidadDeHoras) throws Exception { if
	 * (!this.esValidaLaCantidadDeHorasEnEsteMomento(cantidadDeHoras)) { throw new
	 * Exception("No es valida la cantidad de horas solicitada en este momento"); }
	 * }
	 * 
	 * private boolean esValidaLaCantidadDeHorasEnEsteMomento(Duration
	 * cantidadDeHoras) { LocalTime horaActual = LocalTime.now(); LocalTime
	 * horaFinAlquiler = horaActual.plus(cantidadDeHoras);
	 * 
	 * // Verifica si la hora de fin del alquiler es antes o igual a la hora de
	 * cierre return !horaFinAlquiler.isAfter(sem.getHoraLaboralFin()); }
	 * 
	 * private void verificarHorarioPermitido() throws Exception { if
	 * (!this.seEncuentraEnFranjaHoraria()) { throw new
	 * Exception("Horario no permitido"); } }
	 * 
	 * public boolean seEncuentraEnFranjaHoraria() { LocalTime horaActual =
	 * LocalTime.now(); LocalTime horaMinima = sem.getHoraLaboralInicio(); LocalTime
	 * horaMaxima = sem.getHoraLaboralFin();
	 * 
	 * return (horaActual.isAfter(horaMinima)) && (horaActual.isBefore(horaMaxima));
	 * }
	 */

}