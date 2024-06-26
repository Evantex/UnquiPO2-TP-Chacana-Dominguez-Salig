package tp.po2.sem.sistemaEstacionamiento;

import java.util.Set;
import java.util.stream.Collectors;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;


import tp.po2.sem.Reloj.RelojSem;
import tp.po2.sem.ZonaDeEstacionamiento.ZonaDeEstacionamiento;
import tp.po2.sem.app.App;
import tp.po2.sem.app.CelularDeUsuario;
import tp.po2.sem.estacionamiento.Estacionamiento;
import tp.po2.sem.estacionamiento.EstacionamientoCompraPuntual;
import tp.po2.sem.inspector.Infraccion;
import tp.po2.sem.inspector.Inspector;
import tp.po2.sem.puntoDeVenta.Compra;
import tp.po2.sem.puntoDeVenta.CompraPuntual;
import tp.po2.sem.puntoDeVenta.PuntoDeVenta;

public class SistemaEstacionamiento {
	private Set<Estacionamiento> estacionamientos;
	private Set<CelularDeUsuario> usuarios;
	private List<Infraccion> infracciones;
	private Set<Compra> comprasPuntoDeVenta;
	private LocalTime horaLaboralInicio;
	private LocalTime horaLaboralFin;
	private Notificador sistemaAlertas;
	private RelojSem relojSem;
	private EstadoSistema estadoActual;
	private Set<ZonaDeEstacionamiento> zonasDeEstacionamiento;

	
	public SistemaEstacionamiento() {
		super();
		this.estacionamientos = new HashSet<>();
		this.usuarios = new HashSet<>();
		this.setInfracciones(new ArrayList<>());
		this.comprasPuntoDeVenta = new HashSet<>();
		this.setSistemaAlertas(new Notificador());
		this.relojSem = new RelojSem();
		this.setZonasDeEstacionamiento(new HashSet<>());
		
		//RESPECTO AL ESTADO DEL SISTEMA, DE 7 A 20 ESTA ABIERTO
		this.horaLaboralInicio = LocalTime.of(7, 0); // 7:00 AM
		this.horaLaboralFin = LocalTime.of(20, 0); // 8:00 PM
		this.estadoActual = new EstadoSistemaCerrado();
        this.estadoActual.verificarTransicion(this);
	}
	

	// getters and setters

	public LocalTime getHoraLaboralInicio() {
		return horaLaboralInicio;
	}

	public void setHoraLaboralInicio(LocalTime horaLaboralInicio) {
		this.horaLaboralInicio = horaLaboralInicio;
	}

	public LocalTime getHoraLaboralFin() {
		return horaLaboralFin;
	}

	public void setHoraLaboralFin(LocalTime horaLaboralFin) {
		this.horaLaboralFin = horaLaboralFin;
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
	
	public void setEstadoSistema(EstadoSistema estado) {
		this.estadoActual = estado;
	}
	
	public EstadoSistema getEstadoSistema() {
		return estadoActual;
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
		this.notificarSistemaAlertasInicioEstacionamiento(estacionamiento);
	}
	
	public void solicitudDeEstacionamientoApp(App app, String celular, String patente)  {
		
		estadoActual.registrarEstacionamientoApp(this, app, celular, patente);
		
	}
	
	public void solicitudDeEstacionamientoCompraPuntual(String patente, Duration cantidadDeHoras, PuntoDeVenta puntoDeVenta) {
		
		estadoActual.registrarEstacionamientoCompraPuntual(this, patente, cantidadDeHoras, puntoDeVenta);
		
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

	// metodo que utiliza el Inspector, este debe consultar por patente
	public boolean poseeEstacionamientoVigente(String patente) {

		// dada la patente: filtro para que me retorne el identificador de
		// estacionamiento asociada a esa patente
		// de ese modo puedo utilizar el metodo estaVigente()
		Optional<String> identificadorEstacionamientoOptional = estacionamientos.stream()
				.filter(e -> e.getPatente().equals(patente))
				.map(e -> String.valueOf(e.getIdentificadorEstacionamiento())).findFirst();

		String identificadorEstacionamiento = identificadorEstacionamientoOptional.orElse(null);
		return estaVigente(identificadorEstacionamiento);
	}

	public boolean estaVigente(String identificadorEstacionamiento) {
		return this.estacionamientos.stream().anyMatch(
				e -> e.estaVigente() && e.getIdentificadorEstacionamiento().equals(identificadorEstacionamiento));
	}

	public void registrarInfraccion(String patente, Inspector inspector) {

		LocalDateTime fechaYHoraActual = LocalDateTime.now();
		ZonaDeEstacionamiento zonaInspector = inspector.getZonaAsignada();
		Infraccion infraccion = new Infraccion(patente, fechaYHoraActual, inspector, zonaInspector);

		infracciones.add(infraccion);
	}

	// Método para obtener las infracciones por patente
	public List<Infraccion> buscarInfraccionesPorPatente(String patente) {
        return infracciones.stream()
                           .filter(infraccion -> infraccion.getPatente().equals(patente))
                           .collect(Collectors.toList());
    }


	/*
	 * public HashMap<String, Double> getSaldoCelular() { return saldoCelular; }
	 */

	public void finalizarEstacionamiento(String identificadorEstacionamiento) {
		this.estacionamientos.stream().filter(
				e -> e.estaVigente() && e.getIdentificadorEstacionamiento().equals(identificadorEstacionamiento))
				.findAny().ifPresent(estacionamiento -> estacionamiento.setFinEstacionamiento());
	}

	public Estacionamiento getEstacionamiento(String identificadorEstacionamiento) throws Exception {
		return this.estacionamientos.stream().filter(
				e -> e.estaVigente() && e.getIdentificadorEstacionamiento().equals(identificadorEstacionamiento))
				.findAny().orElseThrow(() -> new Exception("El estacionamiento no existe o no está vigente"));
	}
	
	// LOGICA DE "FIN DE FRANJA HORARIA, FINALIZAR TODOS LOS ESTACIONAMIENTOS VIGENTES"
	public void finalizarTodosLosEstacionamientos() {
		
		LocalTime horaActual = relojSem.getHoraActual();
		if (horaActual == this.horaLaboralFin) {
	    this.estacionamientos.stream()
	        .filter(e -> e.estaVigente()) // Filtrar solo los que están vigentes
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


	public LocalTime getHoraActual() {
		
		return this.relojSem.getHoraActual();
	}

	

	



}
