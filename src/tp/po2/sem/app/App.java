package tp.po2.sem.app;

import java.awt.Point;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import tp.po2.sem.sistemaEstacionamiento.*;
import tp.po2.sem.appGPS.EstadoGPS;
import tp.po2.sem.appGPS.UbicacionDesactivada;
import tp.po2.sem.appModoNotificaciones.ModoNotificaciones;
import tp.po2.sem.estacionamiento.*;

public class App implements MovementSensor 
{

	private SistemaEstacionamiento SEM;
	
	private ModoApp modoApp; // Strategy
	private ModoDesplazamiento modoDeDesplazamiento; // State
	private EstadoGPS deteccionDeDesplazamiento;
	private ModoNotificaciones modoNotificacion; // Strategy
	
	
	private Celular celularAsociado;
	private Point ubicacionUltimoEstacionamiento;
	private String patenteAsociada;

	App(Celular cel, SistemaEstacionamiento sistema, String patenteAsociada) 
	{
		this.celularAsociado = cel;
		this.SEM = sistema;
		this.patenteAsociada = patenteAsociada;
		this.deteccionDeDesplazamiento = new UbicacionDesactivada();
	}
	
	//GETTERS Y SETTERS
	
	public void setModoEstacionamiento(ModoApp modo)
	{
		this.modoApp = modo;
	}

	public Celular getCelularAsociado() {
		return celularAsociado;
	}

	public void setCelularAsociado(Celular celularAsociado)
	{
		this.celularAsociado = celularAsociado;
	}

	public void setModoNotificacion(ModoNotificaciones modo)
	{
		this.modoNotificacion = modo;
	}

	public ModoApp getModoEstacionamiento()
	{
		return this.modoApp;
	}

	public void setUbicacionEstacionamiento(Point ubicacion) 
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
	
	public void setEstadoGps(EstadoGPS estado)
	{
		this.deteccionDeDesplazamiento = estado;
	}
	
	public EstadoGPS getEstadoGps()
	{
		return deteccionDeDesplazamiento;
	}
	
	public ModoNotificaciones getModoNotificacion()
	{
		return this.modoNotificacion;
	}
	
	public ModoDesplazamiento getModoDeDesplazamiento()
	{
		return modoDeDesplazamiento;
	}

	public void setModoDeDesplazamiento(ModoDesplazamiento modoDeDesplazamiento)
	{
		this.modoDeDesplazamiento = modoDeDesplazamiento;
	}

	public String getPatenteAsociada()
	{
		return patenteAsociada;
	}

	public void setPatenteAsociada(String patenteAsociada)
	{
		this.patenteAsociada = patenteAsociada;
	}
	
	public String getNroCelularAsociado()
	{
		return this.celularAsociado.getNroCelular();
	}
	
	// LA APP INICIALIZA CON EL GPS DESACTIVADO, LO PUEDE ACTIVAR. 
	// NECESITA ESTAR ACTIVADO PARA UTILIZAR LAS FUNCIONES DE MOVEMENT SENSOR
	
	public void activarGPS()
	{
		this.deteccionDeDesplazamiento.activar(this);
	}

	public void desactivarGPS()
	{
		this.deteccionDeDesplazamiento.desactivar(this);
	}

	
	
	
	@Override
	public void driving() throws Exception
	{
		modoDeDesplazamiento.conduciendo(this);
	}
	
	
	@Override
	public void walking() throws Exception
	{
		modoDeDesplazamiento.caminando(this);
	}
	

	// VALIDACIONES
	public void verificarSaldoSuficiente() throws Exception
	{
		if (celularAsociado.getSaldo() < 40) 
		{
			throw new Exception("No tiene saldo suficiente para estacionar.");
		}
	}
	
	
	public void verificarZonaEstacionamiento() throws Exception 
	{
		if ( !this.estaDentroDeZonaEstacionamiento() )
		{
			throw new Exception("No está en una zona de estacionamiento");
		}
	}
		
	
	public boolean estaDentroDeZonaEstacionamiento()
	{
		return this.celularAsociado.estaDentroDeZonaEstacionamiento();
	}
	
	
	public boolean tieneEstacionamientoVigente() 
	{
		return SEM.poseeEstacionamientoVigente(patenteAsociada);
	}
	
	
	public void verificarEstacionamientoVigente() throws Exception 
	{
		if ( !this.tieneEstacionamientoVigente() )
		{
			throw new Exception("Ya tienes un estacionamiento vigente");
		}
	}
	
	
	public boolean validarMismoPuntoGeografico() 
	{
		return this.getUbicacionActual() == this.getUbicacionEstacionamiento();
	}
	
	
	public boolean estaActivadaLaUbicacion()
	{
		return deteccionDeDesplazamiento.seEncuentraActivada();
	}
	
		
	public void iniciarEstacionamiento()
	{
	    try 
	    {	
	    	this.verificarValidacionesParaIniciarEstacionamiento();
	        String celular = this.celularAsociado.getNroCelular();
	        String patente = this.getPatente();
	        EstacionamientoApp nuevoEstacionamiento = new EstacionamientoApp(this, celular, patente);
	        this.SEM.solicitudDeEstacionamientoApp( nuevoEstacionamiento );
	        this.enviarDetallesInicioEstacionamiento( nuevoEstacionamiento ); 
	    } 
	    catch (Exception e)
	    {
	        this.notificarUsuario( e.getMessage() );
	    }
	}
	
	
	public void verificarValidacionesParaIniciarEstacionamiento() throws Exception
{
		this.verificarEstacionamientoVigente();
		this.verificarSaldoSuficiente();
		this.verificarZonaEstacionamiento();
	}
	
	
	private double calcularPosibleMontoSegunSaldo(LocalTime horaInicioEstacionamiento,
			LocalDateTime horaMaximaFinEstacionamiento) throws Exception 
	{	
		Duration duracionEnHoras = Duration.between(horaInicioEstacionamiento, horaMaximaFinEstacionamiento);	
		return SEM.calcularCuantoCobrar(horaInicioEstacionamiento, duracionEnHoras);	
	}

	
	public void notificarUsuario(String msg) 
	{
		this.getModoNotificacion().notificar(this, msg);
	}
	

	public void finalizarEstacionamiento() throws Exception 
	{
		String Numerocelular = this.celularAsociado.getNroCelular();
		Celular celular = this.celularAsociado;
		Estacionamiento est = this.SEM.getEstacionamiento(Numerocelular);
		this.SEM.finalizarEstacionamiento(Numerocelular);
		solicitarCostoEstacionamiento(celular, est);
		this.enviarDetallesFinEstacionamiento(est);
		this.SEM.notificarSistemaAlertasFinEstacionamiento(est);
	}

	
	
	public void solicitarCostoEstacionamiento(Celular celular, Estacionamiento est) throws Exception 
	{
		this.SEM.cobrarPorEstacionamiento(est, celular);
	}
	

	// LOGICA DE Información al Usuario en Estacionamiento vía App

	public LocalTime getHoraMaximaFinEstacionamiento() 
	{
		LocalTime horaMáximaPermitidaSaldo = LocalTime.now().plusHours(this.getHorasMaximasPermitidasEstacionamiento());
				LocalTime horaMáximaPermitidaDelDía = SistemaEstacionamiento.getHoraLaboralFin();

		return horaMáximaPermitidaSaldo.isBefore(horaMáximaPermitidaDelDía) ? horaMáximaPermitidaSaldo
				: horaMáximaPermitidaDelDía;
	}
	

	public int getHorasMaximasPermitidasEstacionamiento()
	{
		double saldoCelular = this.celularAsociado.getSaldo();
		int precioPorHora = SistemaEstacionamiento.getPrecioporhora();
		return (int) Math.floor(saldoCelular / precioPorHora); // Usamos floor para redondear hacia abajo
	}
	
	
	public void enviarDetallesInicioEstacionamiento( Estacionamiento estacionamiento )
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String inicio = "Hora de inicio del estacionamiento: " + estacionamiento.getInicioEstacionamiento().format(formatter);
		String fin = "Hora máxima fin del estacionamiento respecto al saldo: " + this.getHoraMaximaFinEstacionamiento().format(formatter);
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
	

}
