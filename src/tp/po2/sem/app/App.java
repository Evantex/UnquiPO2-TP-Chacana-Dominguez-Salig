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
	private Celular celularAsociado;
	private Point ubicacionUltimoEstacionamiento;
	private String patenteAsociada;

	
	App( Celular cel, SistemaEstacionamiento sistema, String patenteAsociada )
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
	public void driving()
	{
		this.modoDeDesplazamiento.conduciendo( this, this.patenteAsociada );
	}


	@Override
	public void walking() 
	{
		this.modoDeDesplazamiento.caminando(this, this.patenteAsociada);		
	}
	
	
	public boolean estaDentroDeZonaEstacionamiento()
	{
		return this.celularAsociado.estaDentroDeZonaEstacionamiento();
	}
	
	public boolean seEncuentraEnFranjaHoraria()
	{
		LocalTime horaActual = LocalTime.now();
		LocalTime horaMinima = LocalTime.of(7, 0);
		LocalTime horaMaxima = LocalTime.of(20, 0);
		return (horaActual.isBefore(horaMinima)) && (horaActual.isAfter(horaMaxima)); 
	}
	
	public void iniciarEstacionamiento(String dominioVehiculo)
	{
	    try 
	    {
	        this.verificarSaldoSuficiente();
	        this.verificarHorarioPermitido();
	        this.verificarSiPoseeEstacionamientoVigente();
	        this.verificarZonaEstacionamiento();
	        String celular = this.celularAsociado.getNroCelular();
	        String patente = this.getPatente();
	        Estacionamiento nuevoEstacionamiento = new EstacionamientoApp(this, celular, patente);
	        this.SEM.registrarEstacionamiento( nuevoEstacionamiento );
	        this.enviarDetallesInicioEstacionamiento( nuevoEstacionamiento );
	        this.SEM.notificarSistemaAlertasInicioEstacionamiento(patente, celular);
	    } 
	    catch (Exception e)
	    {
	        this.notificarUsuario( e.getMessage() );
	        // Aquí puedes añadir lógica adicional para manejar el caso de error
	    }
	}
	
	
	public void enviarDetallesInicioEstacionamiento( Estacionamiento estacionamiento )
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String inicio = "Hora de inicio del estacionamiento: " + estacionamiento.getInicioEstacionamiento().format(formatter);
		String fin = "Hora máxima fin del estacionamiento respecto al saldo: " + estacionamiento.getHoraMaximaFinEstacionamiento().format(formatter);
		String msg = inicio + "\n" + fin;
		this.notificarUsuario(msg);
	}
	

	public void finalizarEstacionamiento() throws Exception
	{	
		String celular = this.celularAsociado.getNroCelular();
		String patente = this.getPatente();
		Estacionamiento est = this.SEM.getEstacionamiento( celular );
		this.SEM.finalizarEstacionamiento( celular );
		this.enviarDetallesFinEstacionamiento(est);
		this.SEM.notificarSistemaAlertasFinEstacionamiento(patente, celular);
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
	
	
	
	private void verificarSaldoSuficiente() throws Exception 
	{
	    if ( this.SEM.obtenerSaldoCelular(this.celularAsociado.getNroCelular()) 
	    		< 40 )
	    {
	        throw new Exception("No tiene saldo suficiente para estacionar.");
	    }
	}
	
	
	private void verificarHorarioPermitido() throws Exception 
	{
	    if ( !this.seEncuentraEnFranjaHoraria() )
	    {
	        throw new Exception("Horario no permitido");
	    }
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
	
	
	public int getHorasMaximasPermitidasEstacionamiento()
	{
		double saldoCelular = this.SEM.obtenerSaldoCelular(this.celularAsociado.getNroCelular());
		return (int) Math.round(saldoCelular / 40);
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
	
	
	public boolean tieneEstacionamientoVigente()
	{
		return this.SEM.estaVigente( this.celularAsociado.getNroCelular() );
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
