package tp.po2.sem.sistemaEstacionamiento;

import java.util.Set;
import java.util.stream.Collectors;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


import tp.po2.sem.ZonaDeEstacionamiento.ZonaDeEstacionamiento;
import tp.po2.sem.app.Celular;
import tp.po2.sem.estacionamiento.Estacionamiento;
import tp.po2.sem.estacionamiento.EstacionamientoApp;
import tp.po2.sem.estacionamiento.EstacionamientoCompraPuntual;
import tp.po2.sem.inspector.Infraccion;
import tp.po2.sem.inspector.Inspector;
import tp.po2.sem.puntoDeVenta.Compra;
import tp.po2.sem.puntoDeVenta.CompraPuntual;
import tp.po2.sem.tarifasEstacionamiento.CalculadorDeTarifa;

public class SistemaEstacionamiento
{
	private int precioPorHora = 40;
	private static final LocalTime horaLaboralInicio = LocalTime.of(7, 0);
	private static final LocalTime horaLaboralFin = LocalTime.of(20, 0);
	private Set<Estacionamiento> estacionamientos;
	private Set<Celular> usuarios;
	private List<Infraccion> infracciones;
	private Set<Compra> comprasPuntoDeVenta;
	private Notificador sistemaAlertas;
	private Set<ZonaDeEstacionamiento> zonasDeEstacionamiento;
	private RangoHorario rangoHorario;
	private CalculadorDeTarifa calculador;

	public SistemaEstacionamiento() 
	{
		super();
		this.estacionamientos = new HashSet<>();
		this.usuarios = new HashSet<>();
		this.setInfracciones(new ArrayList<>());
		this.comprasPuntoDeVenta = new HashSet<>();
		this.setSistemaAlertas(new Notificador());
		this.setZonasDeEstacionamiento(new HashSet<>());
		this.rangoHorario = new RangoHorario(horaLaboralInicio, horaLaboralFin);
		this.calculador = new CalculadorDeTarifa();
	}

	// getters and setters

	public static LocalTime getHoraLaboralInicio() 
	{
		return horaLaboralInicio;
	}

	public static LocalTime getHoraLaboralFin() {
		return horaLaboralFin;
	}

	public int getPrecioPorHora() 
	{
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

	public Set<Celular> getUsuarios() {
		return usuarios;
	}
	
	public int getCantidadUsuarios()
	{
		return this.usuarios.size();
	}

	public void setUsuarios(Set<Celular> usuarios) {
		this.usuarios = usuarios;
	}

	public Set<ZonaDeEstacionamiento> getZonasDeEstacionamiento() {
		return zonasDeEstacionamiento;
	}

	public void setZonasDeEstacionamiento(Set<ZonaDeEstacionamiento> zonasDeEstacionamiento) {
		this.zonasDeEstacionamiento = zonasDeEstacionamiento;
	}

	public RangoHorario getRangoHorario() {
		return rangoHorario;
	}

	public void setRangoHorario(RangoHorario rangoHorario)
	{
		this.rangoHorario = rangoHorario;
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

	public void registrarEstacionamiento(Estacionamiento estacionamiento) 
	{
		estacionamientos.add(estacionamiento);
	}

	public void solicitudDeEstacionamientoApp(EstacionamientoApp unEstacionamiento)
	{
		this.registrarEstacionamiento(unEstacionamiento);
		this.notificarSistemaAlertasInicioEstacionamiento(unEstacionamiento);
	}

	public void solicitudDeEstacionamientoCompraPuntual(String patente, CompraPuntual compraAsociada) throws Exception
	{
		EstacionamientoCompraPuntual estacionamiento = new EstacionamientoCompraPuntual(patente, compraAsociada);
		double costoEstacionamiento = this.calcularCuantoCobrar(compraAsociada.getHoraInicio(), compraAsociada.getHorasCompradas());
		estacionamiento.setCostoEstacionamiento(costoEstacionamiento);
		this.registrarEstacionamiento(estacionamiento);
		this.registrarCompra(compraAsociada);
	}
 
	
	public void puedeEstacionar(String patente, LocalTime horaInicio, LocalTime horaFin) throws Exception
	{
		this.verificarQueNoTengaYaUnEstacionamientoVigente(patente);
		this.verificarHorasValidasParaEstacionamiento(horaInicio, horaFin);
	}

	public void verificarHorasValidasParaEstacionamiento(LocalTime horaInicio, LocalTime horaFin) throws Exception 
	{
		rangoHorario.validarHoras(horaInicio, horaFin);
	}

	public void verificarQueNoTengaYaUnEstacionamientoVigente(String patente) throws Exception 
	{
		if (poseeEstacionamientoVigente(patente))
		{
			throw new Exception("Ya tiene un estacionamiento valido en curso");
		}
	}

	
	// CAMBIAR RECARGAS
	public void cargarCelular(Celular cel, double saldo) 
	{
		this.usuarios.add(cel);
		cel.recibirRecargaDeSaldo(saldo);
	}
	
	public void agregarUsuario(Celular cel) throws Exception
	{
		boolean existeElNro = this.usuarios.stream()
				.anyMatch( user -> user.getNroCelular().equals(cel.getNroCelular()) );
		
		if( existeElNro )
		{
			throw new Exception("El número de celular ya se encuentra siendo utilizado.");
		}
		else
		{
			this.usuarios.add(cel);
		}
	}
	
	
	public Celular getUsuarioPorNro( String nroCelular )
	{
		return this.usuarios.stream()
					.filter( celular -> celular.getNroCelular().equals(nroCelular ))
					.findFirst()
					.orElseThrow(() -> new NoSuchElementException("El usuario no se encuentra registrado"));
	}
	

	

	// Logica Inspector

	public boolean poseeEstacionamientoVigente(String patente)
	{
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

	public void finalizarEstacionamiento(String identificadorEstacionamiento)  {
		this.estacionamientos.stream().filter(
				e -> e.estaVigente() && e.getIdentificadorEstacionamiento().equals(identificadorEstacionamiento))
				.findAny().ifPresent(estacionamiento -> estacionamiento.finalizarEstacionamiento());

	}

	public void cobrarPorEstacionamiento(Estacionamiento estacionamiento ,Celular celular) throws Exception 
	{
		double montoADisminuir = this.calcularCuantoCobrar(estacionamiento.getInicioEstacionamiento(), estacionamiento.getDuracionEnHoras());
		estacionamiento.setCostoEstacionamiento(montoADisminuir);
		celular.disminuirSaldo(montoADisminuir);
	}
	
	public double calcularCuantoCobrar(LocalTime inicioEstacionamiento, Duration cantidadDeHoras) throws Exception
	{
		RangoHorario rangoHorarioLaboral = this.getRangoHorario();
		RangoHorario rangoHorarioEstacionamiento = new RangoHorario(inicioEstacionamiento, inicioEstacionamiento.plus(cantidadDeHoras));
		
		double montoFinal = calculador.determinarCobroPara(rangoHorarioLaboral, rangoHorarioEstacionamiento, precioPorHora);
		
		return montoFinal;
	}
	

	public Estacionamiento getEstacionamiento(String identificadorEstacionamiento) throws Exception
	{
		return this.estacionamientos.stream().filter(
				e -> e.estaVigente() && e.getIdentificadorEstacionamiento().equals(identificadorEstacionamiento))
				.findAny().orElseThrow(() -> new Exception("El estacionamiento no existe o no está vigente"));
	}
	

	// LOGICA DE "FIN DE FRANJA HORARIA, FINALIZAR TODOS LOS ESTACIONAMIENTOS
	// VIGENTES"
	public void finalizarTodosLosEstacionamientos() {

		this.estacionamientos.stream().filter(e -> e.estaVigente()) // Filtrar solo los que están vigentes
				.forEach(e -> e.finalizarEstacionamiento()); // Finalizar solo los vigentes

	}

	// LOGICA DE OBSERVER

	public Notificador getSistemaAlertas() 
	{
		return sistemaAlertas;
	}

	public void setSistemaAlertas(Notificador sistemaAlertas) 
	{
		this.sistemaAlertas = sistemaAlertas;
	}

	public void notificarSistemaAlertasFinEstacionamiento(Estacionamiento unEstacionamiento)
	{
		sistemaAlertas
				.notificarObservadores(new EventoEstacionamiento(EventoEstacionamiento.Tipo.FIN, unEstacionamiento));
	}

	public void notificarSistemaAlertasInicioEstacionamiento(Estacionamiento unEstacionamento)
	{
		sistemaAlertas
				.notificarObservadores(new EventoEstacionamiento(EventoEstacionamiento.Tipo.INICIO, unEstacionamento));
	}

}
