package tp.po2.sem.sistemaEstacionamiento;

import java.util.Set;
import java.util.stream.Collectors;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;


import tp.po2.sem.ZonaDeEstacionamiento.ZonaDeEstacionamiento;
import tp.po2.sem.app.CelularDeUsuario;
import tp.po2.sem.estacionamiento.Estacionamiento;
import tp.po2.sem.estacionamiento.EstacionamientoApp;
import tp.po2.sem.estacionamiento.EstacionamientoCompraPuntual;
import tp.po2.sem.inspector.Infraccion;
import tp.po2.sem.inspector.Inspector;
import tp.po2.sem.puntoDeVenta.Compra;
import tp.po2.sem.puntoDeVenta.CompraPuntual;

public class SistemaEstacionamiento {
	private static final int precioPorHora = 40;
	private static final LocalTime horaLaboralInicio = LocalTime.of(7, 0);
	private static final LocalTime horaLaboralFin = LocalTime.of(20, 0);
	private Set<Estacionamiento> estacionamientos;
	private Set<CelularDeUsuario> usuarios;
	private List<Infraccion> infracciones;
	private Set<Compra> comprasPuntoDeVenta;
	private Notificador sistemaAlertas;
	private Set<ZonaDeEstacionamiento> zonasDeEstacionamiento;
	private RangoHorario rangoHorario;
	
	
	public SistemaEstacionamiento() {
		super();
		this.estacionamientos = new HashSet<>();
		this.usuarios = new HashSet<>();
		this.setInfracciones(new ArrayList<>());
		this.comprasPuntoDeVenta = new HashSet<>();
		this.setSistemaAlertas(new Notificador());
		this.setZonasDeEstacionamiento(new HashSet<>());
		this.rangoHorario = new RangoHorario(horaLaboralInicio, horaLaboralFin);
		
	}

	// getters and setters

	public LocalTime getHoraLaboralInicio() {
		return horaLaboralInicio;
	}

	public LocalTime getHoraLaboralFin() {
		return horaLaboralFin;
	}
	

	public static int getPrecioporhora() {
		return precioPorHora;
	}


	public Set<Estacionamiento> getEstacionamientos() {
		return estacionamientos;
	}

	public void setEstacionamientos(Set<Estacionamiento> estacionamientos) {
		this.estacionamientos = estacionamientos;
	}

	public Set<Compra> getComprasPuntoDeVenta() {
		return comprasPuntoDeVenta;
	}

	public void setComprasPuntoDeVenta(Set<Compra> comprasPuntoDeVenta) {
		this.comprasPuntoDeVenta = comprasPuntoDeVenta;
	}

	public Integer getCantidadEstacionamientos() {
		return estacionamientos.size();
	}

	public List<Infraccion> getInfracciones() {
		return infracciones;
	}

	public void setInfracciones(List<Infraccion> infracciones) {
		this.infracciones = infracciones;
	}

	public Set<CelularDeUsuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Set<CelularDeUsuario> usuarios) {
		this.usuarios = usuarios;
	}


	public Set<ZonaDeEstacionamiento> getZonasDeEstacionamiento() {
		return zonasDeEstacionamiento;
	}

	public void setZonasDeEstacionamiento(Set<ZonaDeEstacionamiento> zonasDeEstacionamiento) {
		this.zonasDeEstacionamiento = zonasDeEstacionamiento;
	}

	// registraciones
	public void registrarZonaEstacionamiento(ZonaDeEstacionamiento zona) {
		zonasDeEstacionamiento.add(zona);
	}

	public void removerZonaEstacionamiento(ZonaDeEstacionamiento zona) {
		zonasDeEstacionamiento.remove(zona);
	}

	public void registrarCompra(Compra compra) {

		comprasPuntoDeVenta.add(compra);
	}

	public void registrarEstacionamiento(Estacionamiento estacionamiento) {
		estacionamientos.add(estacionamiento);
		
	}

	public void solicitudDeEstacionamientoApp(EstacionamientoApp unEstacionamiento) {

		this.registrarEstacionamiento(unEstacionamiento);
		this.notificarSistemaAlertasInicioEstacionamiento(unEstacionamiento);

	}

	public void solicitudDeEstacionamientoCompraPuntual(EstacionamientoCompraPuntual unEstacionamiento, CompraPuntual compraAsociada) {

		this.registrarEstacionamiento(unEstacionamiento);
		this.registrarCompra(compraAsociada);
	}
	
	public void verificarHorasValidasParaEstacionamientoCompraPuntual(LocalTime horaInicio, LocalTime horaFin) throws Exception {
		rangoHorario.validarHorasCompraPuntual(horaInicio, horaFin);
	}
	
	
	
	
	// CAMBIAR RECARGAS

	public void cargarCelular(String nroCelular, double saldo) {
		Optional<CelularDeUsuario> usuarioEncontrado = usuarios.stream()
				.filter(usuario -> usuario.getNroCelular().equals(nroCelular)).findFirst();

		if (usuarioEncontrado.isPresent()) {
			CelularDeUsuario usuario = usuarioEncontrado.get();
			usuario.recibirRecargaDeSaldo(saldo);
		} else {
			CelularDeUsuario usuarionuevo = new CelularDeUsuario(nroCelular, saldo);
			agregarUsuario(usuarionuevo); // Añadir el nuevo usuario a la lista
			usuarionuevo.recibirRecargaDeSaldo(saldo);
		}
	}

	public void agregarUsuario(CelularDeUsuario c) {
		usuarios.add(c);
	}

	// Logica Inspector

	public boolean poseeEstacionamientoVigente(String patente) {
		return this.estacionamientos.stream().anyMatch(e -> e.estaVigente() && e.getPatente().equals(patente));
	}

	public void registrarInfraccion(String patente, Inspector inspector) {

		LocalDateTime fechaYHoraActual = LocalDateTime.now();
		ZonaDeEstacionamiento zonaInspector = inspector.getZonaAsignada();
		Infraccion infraccion = new Infraccion(patente, fechaYHoraActual, inspector, zonaInspector);

		infracciones.add(infraccion);
	}

	// Método para obtener las infracciones por patente
	public List<Infraccion> buscarInfraccionesPorPatente(String patente) {
		return infracciones.stream().filter(infraccion -> infraccion.getPatente().equals(patente))
				.collect(Collectors.toList());
	}

	public void finalizarEstacionamiento(String identificadorEstacionamiento) {
		this.estacionamientos.stream().filter(
				e -> e.estaVigente() && e.getIdentificadorEstacionamiento().equals(identificadorEstacionamiento))
				.findAny().ifPresent(estacionamiento -> estacionamiento.finalizarEstacionamiento());
	}

	public Estacionamiento getEstacionamiento(String identificadorEstacionamiento) throws Exception {
		return this.estacionamientos.stream().filter(
				e -> e.estaVigente() && e.getIdentificadorEstacionamiento().equals(identificadorEstacionamiento))
				.findAny().orElseThrow(() -> new Exception("El estacionamiento no existe o no está vigente"));
	}

	// LOGICA DE "FIN DE FRANJA HORARIA, FINALIZAR TODOS LOS ESTACIONAMIENTOS
	// VIGENTES"
	public void finalizarTodosLosEstacionamientos() {

		LocalTime horaActual = LocalTime.now();
		if (horaActual == horaLaboralFin) {
			this.estacionamientos.stream().filter(e -> e.estaVigente()) // Filtrar solo los que están vigentes
					.forEach(e -> e.finalizarEstacionamiento()); // Finalizar solo los vigentes
		}
	}

	// LOGICA DE OBSERVER
	public void agregarObservador(Observer observador) {
		getSistemaAlertas().agregarObservador(observador);
	}

	public void eliminarObservador(Observer observador) {
		getSistemaAlertas().eliminarObservador(observador);
	}

	public Notificador getSistemaAlertas() {
		return sistemaAlertas;
	}

	public void setSistemaAlertas(Notificador sistemaAlertas) {
		this.sistemaAlertas = sistemaAlertas;
	}

	public void notificarSistemaAlertasFinEstacionamiento(Estacionamiento unEstacionamiento) {

		sistemaAlertas
				.notificarObservadores(new EventoEstacionamiento(EventoEstacionamiento.Tipo.FIN, unEstacionamiento));

	}

	public void notificarSistemaAlertasInicioEstacionamiento(Estacionamiento unEstacionamento) {
		sistemaAlertas
				.notificarObservadores(new EventoEstacionamiento(EventoEstacionamiento.Tipo.INICIO, unEstacionamento));

	}

	
}
