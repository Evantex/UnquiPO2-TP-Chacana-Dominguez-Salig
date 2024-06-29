package tp.po2.sem.puntoDeVenta;

import java.time.LocalDate;
import java.time.LocalTime;
import tp.po2.sem.ZonaDeEstacionamiento.ZonaDeEstacionamiento;
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

	public void registrarEstacionamientoCompraPuntual(String patente, int cantidadDeHoras, LocalDate fechaCompra,
			LocalTime horaInicio, LocalTime horaFin) throws Exception {

		this.horarioCoherente(horaInicio, horaFin);
		sem.validarHorarioPermitido(horaInicio);
		sem.validarHorarioPermitido(horaFin);

		// Si no se lanza la excepción, continuar con la creación del estacionamiento
		CompraPuntual compraEstacionamiento = new CompraPuntual(this, cantidadDeHoras, fechaCompra, horaInicio,
				horaFin);

		EstacionamientoCompraPuntual estacionamiento = new EstacionamientoCompraPuntual(patente, compraEstacionamiento);

		sem.solicitudDeEstacionamientoCompraPuntual(estacionamiento, compraEstacionamiento);

	}

	public void horarioCoherente(LocalTime horaInicio, LocalTime horaFin) throws Exception {
		if (horaInicio.isAfter(horaFin)) {
			throw new Exception("La hora de inicio no puede ser mayor que la hora de fin");
		}

		if (horaFin.isBefore(horaInicio)) {
			throw new Exception("La hora de fin no puede ser menor que la hora de inicio");
		}
	}

	public void cargarSaldoEnCelular(String numeroCelular, double saldo) {

		CompraRecargaCelular CompraRecargaCelular = new CompraRecargaCelular(this, numeroCelular, saldo);
		sem.cargarCelular(numeroCelular, saldo);

		sem.registrarCompra(CompraRecargaCelular);
	}

}