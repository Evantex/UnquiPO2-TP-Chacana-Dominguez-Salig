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
	private boolean vaCaminando;
	private double saldoDisponible;
	private AsistenciaUsuario asistente;
	private ModoEstacionamiento modoEstacionamiento;
	
	
	public boolean getDeteccionDesplazamiento()
	{
		return this.deteccionDesplazamiento;
	}
	
	
	@Override
	public void driving()
	{
		if( usuarioAsociado.estaEnZonaDeEstacionamiento() && this.vaCaminando )
		{
			this.vaCaminando = false;
			this.asistente.notificar(this, this.usuarioAsociado.getPatente());
		}
			
	}
	
	
	
	
	
	@Override
	public void walking() 
	{
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
		// Excepción 1° si no tiene saldo
		// Excepción 2° si está fuera de franja horaria
	}
	
	
	public void finalizarEstacionamiento()
	{
		this.asistente.actualizarEstado(this);
	}
	
	public void notificarUsuario(String msg)
	{
		this.usuarioAsociado.recibirNotificacion(msg);
	}
	
	
	public void setModoEstacionamiento( ModoEstacionamiento modo )
	{
		this.modoEstacionamiento = modo;
	}
	
	public void setAsistenteUsuario( AsistenciaUsuario asistente )
	{
		this.asistente = asistente;
	}
	
	
	
	
	
	public ModoEstacionamiento getModoEstacionamiento()
	{
		return this.modoEstacionamiento;
	}
	
	
	

}
