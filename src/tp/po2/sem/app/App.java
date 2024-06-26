package tp.po2.sem.app;
import java.awt.Point;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import tp.po2.sem.sistemaEstacionamiento.*;
import tp.po2.sem.estacionamiento.*;

public class App implements MovementSensor
{

	private SistemaEstacionamiento SEM;
	private ModoEstacionamiento modoEstacionamiento; // Strategy
	private ModoDesplazamiento modoDeDesplazamiento; // State
/*
 	Patrón State: Ésta interfaz 'ModoDesplazamiento' cumple el rol de estado dentro de la jerarquía de state. A su vez
		la clase App cumple el rol de contexto. Además, los estados concretos son las clases: 'ModalidadCaminando' y 'MolidadConduciendo'.
*/
	
	private ModoNotificaciones modoNotificacion; // Strategy
	private CelularDeUsuario celularAsociado;
	private Point ubicacionUltimoEstacionamiento;
	private String patenteAsociada;

	
	App( CelularDeUsuario cel, SistemaEstacionamiento sistema, String patenteAsociada )
	{
		this.celularAsociado  = cel;
		this.SEM = sistema;
		this.patenteAsociada = patenteAsociada;
	}
	
	
	public ModoNotificaciones getModoNotificacion()
	{
		return this.modoNotificacion;
	}
	
	
	@Override
	public void driving() throws Exception
	{
		this.modoDeDesplazamiento.conduciendo( this, this.patenteAsociada );
	}


	@Override
	public void walking() throws Exception 
	{
		this.modoDeDesplazamiento.caminando(this, this.patenteAsociada);		
	}
	
	
	public boolean estaDentroDeZonaEstacionamiento()
	{
		return this.celularAsociado.estaDentroDeZonaEstacionamiento();
	}
	
	
	public void iniciarEstacionamiento()
	{
	    try 
	    {	
	    	this.verificarValidacionesParaIniciarEstacionamiento();
	        
	    	// Si no se lanza la excepción, continuar con la creación del estacionamiento
	    	EstacionamientoApp estacionamiento = new EstacionamientoApp(this, celularAsociado.getNroCelular(), this.getPatente());
	        this.SEM.solicitudDeEstacionamientoApp(estacionamiento);
	        this.enviarDetallesInicioEstacionamiento(estacionamiento);
	      
	        
	    } 
	    catch (Exception e)
	    {
	        this.notificarUsuario( e.getMessage() );
	       
	    }
	}
	
	public void verificarValidacionesParaIniciarEstacionamiento() throws Exception {
		this.verificarSiPoseeEstacionamientoVigente();
		this.verificarSaldoSuficiente();
    	this.verificarHorarioPermitido(); 
		this.verificarZonaEstacionamiento();
	}
	
	public void finalizarEstacionamiento() throws Exception
	{	
		String celular = this.celularAsociado.getNroCelular();
		Estacionamiento est = this.SEM.getEstacionamiento( celular );
		this.SEM.finalizarEstacionamiento( celular );
		this.enviarDetallesFinEstacionamiento(est);
		this.SEM.notificarSistemaAlertasFinEstacionamiento(est);
	}
	
	// LOGICA DE Información al Usuario en Estacionamiento vía App
	public void enviarDetallesInicioEstacionamiento( EstacionamientoApp estacionamiento )
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String inicio = "Hora de inicio del estacionamiento: " + estacionamiento.getInicioEstacionamiento().format(formatter);
		String fin = "Hora máxima fin del estacionamiento respecto al saldo: " + this.getHoraMaximaFinEstacionamiento(estacionamiento).format(formatter);
		String msg = inicio + "\n" + fin;
		this.notificarUsuario(msg);
	}
	

	public void enviarDetallesFinEstacionamiento( Estacionamiento estacionamiento ) throws Exception
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String inicio = "Hora de inicio del estacionamiento: " + estacionamiento.getInicioEstacionamiento().format(formatter);
		String fin = "Hora máxima fin del estacionamiento: " + estacionamiento.getFinEstacionamiento().format(formatter);
		String duracion = "La duración en horas del estacionamiento fué de " + estacionamiento.getDuracionEnHoras();
		String precio = "El costo del estacionamiento fué de: " + estacionamiento.getCostoEstacionamiento();
		String msg = inicio + "\n" + fin + "\n" + duracion + "\n" + precio;
		this.notificarUsuario(msg);
	}
	
	
	
	public void verificarSaldoSuficiente() throws Exception 
	{
	    if (celularAsociado.getSaldo() < 40)
	    {
	        throw new Exception("No tiene saldo suficiente para estacionar.");
	    }
	}
	
	
	private void verificarHorarioPermitido() throws Exception 
	{
	    SEM.validarHorarioPermitido(LocalTime.now());
	}
	
	public void verificarZonaEstacionamiento() throws Exception 
	{
		if ( !this.estaDentroDeZonaEstacionamiento() )
		{
			throw new Exception("No está en una zona de estacionamiento");
		}
	}
	
	public void verificarSiPoseeEstacionamientoVigente() throws Exception 
	{
		if ( this.tieneEstacionamientoVigente() )
		{
			throw new Exception("Ya tienes un estacionamiento vigente");
		}
	}
	
	public boolean tieneEstacionamientoVigente() {
		return SEM.poseeEstacionamientoVigente(patenteAsociada);
	}
	
	public LocalTime getHoraMaximaFinEstacionamiento(EstacionamientoApp unEstacionamiento) {
		
		LocalTime horaMáximaPermitidaSaldo = unEstacionamiento.getInicioEstacionamiento().plusHours(this.getHorasMaximasPermitidasEstacionamiento());
		
		LocalTime horaMáximaPermitidaDelDía = SEM.getHoraLaboralFin();
		
		return horaMáximaPermitidaSaldo.isBefore(horaMáximaPermitidaDelDía) ? horaMáximaPermitidaSaldo
				: horaMáximaPermitidaDelDía;
	}
	
	public int getHorasMaximasPermitidasEstacionamiento()
	{
		double saldoCelular = this.celularAsociado.getSaldo();
		int precioPorHora   = SistemaEstacionamiento.getPrecioporhora();
		return (int) Math.round(saldoCelular / precioPorHora);
	}
	
	
	/*
	public boolean validarSiEsPosibleEstacionar()
	{
		return this.SEM.obtenerSaldoCelular(this.celularAsociado.getNroCelular()) >= 40
				&& this.seEncuentraEnFranjaHoraria();
	}
	*/
	
	
	public void notificarUsuario(String msg)
	{
		this.celularAsociado.recibirMensaje(msg);
	}
	
	
	public void setModoEstacionamiento( ModoEstacionamiento modo )
	{
		this.modoEstacionamiento = modo;
	}
	
	
	public void setModoDesplazamiento( ModoDesplazamiento modo )
	{
		this.modoDeDesplazamiento = modo;
	}
	
	public void setModoNotificacion( ModoNotificaciones modo )
	{
		this.modoNotificacion = modo;
	}
	
	
	public ModoEstacionamiento getModoEstacionamiento()
	{
		return this.modoEstacionamiento;
	}
	
	
	public void setUbicacionEstacionamiento( Point ubicacion )
	{
		this.ubicacionUltimoEstacionamiento = ubicacion;
	}
	
	public Point getUbicacionEstacionamiento()
	{
		return this.ubicacionUltimoEstacionamiento;
	}
	
	public Point getUbicacionActual()
	{
		return this.celularAsociado.getUbicacion();
	}
	
	public String getPatente()
	{
		return this.patenteAsociada;
	}

	
/*
	public void setNombreUltimaZonaEstacionamiento()
	{
		this.modoDeDesplazamiento.setNombreZonaEstacionamientoActual( this.getNombreEstacionamientoVigenteActual() );
	}
	
	
	public String getNombreUltimaZonaEstacionamiento()
	{
		return this.modoDeDesplazamiento.getNombreZonaEstacionamientoActual();
	}
	
	
	public String getNombreEstacionamientoVigenteActual()
	{
		// Colocar excepción en el caso de que la respuesta de. getEstacionamiento de SEM sea un NullPointerExcepcion...
		return this.SEM.getEstacionamiento( this.celularAsociado.getNroCelular() ).getIdentificadorEstacionamiento();
	}
*/
	
}
