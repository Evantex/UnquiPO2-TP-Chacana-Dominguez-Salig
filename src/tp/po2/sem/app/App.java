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
	 * Patrón State: Ésta interfaz 'ModoDesplazamiento' cumple el rol de estado dentro de la jerarquía de state. A su vez
	 * la clase App cumple el rol de contexto. Además, los estados concretos son las clases: 'ModalidadCaminando' y 'MolidadConduciendo'.
	 */
	
	private ModoNotificaciones modoNotificacion; // Strategy
	private Celular celularAsociado;
	private Point ubicacionUltimoEstacionamiento;
	private String patenteAsociada;

	

	

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
	
	
	/*
	public void iniciarEstacionamiento( String dominioVehiculo )
	{
		if( this.validarSiEsPosibleEstacionar() )
		{
			this.SEM.registrarEstacionamiento(new EstacionamientoApp(this, 
					this.celularAsociado.getNroCelular(), this.getPatente() ));
		}
		// 1° excepción a añadir: si no tiene saldo.
		// 2° excepción a añadir: si está fuera de franja horaria.
		// Deberían ir en lugar del if y antes de la ejecución del código
	}
	*/
	
	
	public void iniciarEstacionamiento(String dominioVehiculo)
	{
	    try 
	    {
	        this.verificarSaldoSuficiente();
	        this.verificarHorarioPermitido();
	        Estacionamiento nuevoEstacionamiento = new EstacionamientoApp(this, this.celularAsociado.getNroCelular(),
	        		this.getPatente() );
	        this.SEM.registrarEstacionamiento( nuevoEstacionamiento );
	        this.enviarDetallesEstacionamiento( nuevoEstacionamiento );
	    } 
	    catch (Exception e)
	    {
	        this.notificarUsuario( e.getMessage() );
	        // Aquí puedes añadir lógica adicional para manejar el caso de error
	    }
	}
	
	
	public void enviarDetallesEstacionamiento( Estacionamiento estacionamiento )
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String inicio = "Hora de inicio del estacionamiento: " + estacionamiento.getInicioEstacionamiento().format(formatter);
		String fin = "Hora máxima del estacionamiento: " + estacionamiento.getFinEstacionamiento().format(formatter);
		String msg = inicio + "\n" + fin;
		this.notificarUsuario(msg);
	}
	

	public void finalizarEstacionamiento()
	{
		this.asistente.actualizarEstado(this);
		this.SEM.
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
	
	
	public boolean validarSiEsPosibleEstacionar()
	{
		return this.SEM.obtenerSaldoCelular(this.celularAsociado.getNroCelular()) >= 40
				&& this.seEncuentraEnFranjaHoraria();
	}
	
	
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
	
	
	public ModoEstacionamiento getModoEstacionamiento()
	{
		return this.modoEstacionamiento;
	}
	
	
	public boolean tieneEstacionamientoVigente()
	{
		return this.SEM.poseeEstacionamientoVigente( this.celularAsociado.getNroCelular() );
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
