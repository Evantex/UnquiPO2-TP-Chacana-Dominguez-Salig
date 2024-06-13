
package tp.po2.sem.app;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import tp.po2.sem.sistemaEstacionamiento.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class ModalidadConduciendoTest
{
	ModalidadConduciendo modo;
	App aplicacion;
	SistemaEstacionamiento sem;
	Celular cel;
	NotificacionActivada modoNotificacionActivada;
	NotificacionDesactivada modoNotificacionDesactivada;
	Manual modoEstacionamientoManual;
	Automatico modoEstacionamientoAutomatico;




	@BeforeEach
	void setUp() throws Exception 
	{
		modo = new ModalidadConduciendo();
		cel = mock( Celular.class );
		sem = mock( SistemaEstacionamiento.class );
		aplicacion = spy( new App( cel, sem, "GIO 002" ) );
		// App( Celular cel, SistemaEstacionamiento sistema, String patenteAsociada )
		modoNotificacionDesactivada = spy( NotificacionDesactivada.class );
		modoNotificacionActivada = spy( NotificacionActivada.class );
		modoEstacionamientoManual = spy( Manual.class);
		modoEstacionamientoAutomatico = spy( Automatico.class );
		
		
		aplicacion.setModoDesplazamiento(modo);
		aplicacion.setModoEstacionamiento(modoEstacionamientoManual);
		when( aplicacion.getPatente() ).thenReturn("GIO 002");
		when( cel. )



		// Set condiciones para poder estacionar
		when( aplicacion.estaDentroDeZonaEstacionamiento() ).thenReturn(true);
		when( aplicacion.tieneEstacionamientoVigente() ).thenReturn(false);
		
		
		/*
		when( cel.getNroCelular() ).thenReturn("1145241966");
		when( cel.estaDentroDeZonaEstacionamiento() ).thenReturn(true);
		*/
		
		// return aplicacion.estaDentroDeZonaEstacionamiento() && !aplicacion.tieneEstacionamientoVigente();
		
	}

	@Test
	void verificoQueNoSeEnvíenMensajesConNotificaciónDesactivada()
	{
		aplicacion.setModoNotificacion(modoNotificacionDesactivada);
		modo.caminando(aplicacion, "GIO 002");
		verify(aplicacion, never()).notificarUsuario("Posible inicio de estacionamiento");
	}
	
	@Test
	void verificoQueEfectivamenteSeEnvíenMensajesConNotificaciónActivada()
	{
		aplicacion.setModoNotificacion(modoNotificacionActivada);
		modo.caminando(aplicacion, "GIO 002");
		verify(aplicacion, times(1)).notificarUsuario("Posible inicio de estacionamiento");	
	}
	
	@Test
	void verificoQueNoSeEjecuteEstacionamientoEnModoManual()
	{
		aplicacion.setModoNotificacion(modoNotificacionActivada);
		modo.caminando(aplicacion, "GIO 002");
		verify(aplicacion, never()).iniciarEstacionamiento("GIO 002");
	}
	
	@Test
	void verificoQueEfectivamenteSeEjecuteEstacionamientoEnModoAutomatico()
	{
		aplicacion.setModoNotificacion(modoNotificacionActivada);
		aplicacion.setModoEstacionamiento(modoEstacionamientoAutomatico);
		modo.caminando(aplicacion, "GIO 002");
		verify(aplicacion, times(1)).iniciarEstacionamiento("GIO 002");
		verify(aplicacion, times(1)).notificarUsuario("Inicio de estacionamiento realizado de forma automática");
	}
	
	
	


}