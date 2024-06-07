package tp.po2.sem.app;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import tp.po2.sem.sistemaEstacionamiento.*;
import tp.po2.sem.estacionamiento.*;

public class App implements MovementSensor
{
	private Usuario usuarioAsociado;
	private boolean deteccionDesplazamiento = true;
	private SistemaEstacionamiento SEM;
	private double saldoDisponible; // Fuente de dato del saldo en el SEM. 
	private ModoEstacionamiento modoEstacionamiento;
	private ModoDesplazamiento modoDesplazamiento;
	private NotificacionEstacionamiento notificacion;
	
	
	public boolean getDeteccionDesplazamiento()
	{
		return this.deteccionDesplazamiento;
	}
	
	
	@Override
	public void driving()
	{
		if( usuarioAsociado.estaEnZonaDeEstacionamiento() )
		{
			this.modoDesplazamiento.vaConduciendo(this);
		}
		// Va caminadno y va manejando podría ser un state.. dependiendo de eso walking y draving va a ejecutarse o no
		// Sgate en vaCaminando y .. 
	}
	
	
	
	
	
	@Override
	public void walking() 
	{
		this.notificacion.
		if( usuarioAsociado.estaEnZonaDeEstacionamiento() && !this.vaCaminando && 
				this.SEM.poseeEstacionamientoVigente(this.usuarioAsociado.getPatente()) )
		{
			this.vaCaminando = true;
			this.asistente.notificar(this);
		}
	}
	
	
	public boolean seEncuentraEnFranjaHoraria()
	{
		LocalTime horaActual = LocalTime.now();
		LocalTime horaMinima = LocalTime.of(7, 0);
		LocalTime horaMaxima = LocalTime.of(20, 0);
		return (horaActual.isBefore(horaMinima)) && (horaActual.isAfter(horaMaxima)); 
	}
	
	
	public void iniciarEstacionamiento( String dominioVehiculo )
	{
		if( this.saldoDisponible >= 40 && this.seEncuentraEnFranjaHoraria() )
		{
			this.SEM.registrarEstacionamiento( new EstacionamientoApp( LocalDateTime.now(), 
					this.usuarioAsociado.getPatente(), this) );
			this.asistente.actualizarEstado(this);
		}
		// 1° excepción a añadir: si no tiene saldo.
		// 2° excepción a añadir: si está fuera de franja horaria.
		// Deberían ir en lugar del if y antes de la ejecución del código
	}
	
	
	public void finalizarEstacionamiento()
	{
		this.asistente.actualizarEstado(this);
		this.SEM.
	}
	
	public void notificarUsuario(String msg)
	{
		this.usuarioAsociado.recibirNotificacion(msg);
	}
	
	
	public void setModoEstacionamiento( ModoEstacionamiento modo )
	{
		this.modoEstacionamiento = modo;
	}
	
	public void setModoDesplazamiento( ModoDesplazamiento modo )
	{
		this.modoDesplazamiento = modo;
	}
	
	
	
	
	
	public ModoEstacionamiento getModoEstacionamiento()
	{
		return this.modoEstacionamiento;
	}
	
	
	

}
