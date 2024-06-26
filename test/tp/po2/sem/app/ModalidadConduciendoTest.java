
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

import java.awt.Point;
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
	CelularDeUsuario cel;
	NotificacionActivada modoNotificacionActivada;
	NotificacionDesactivada modoNotificacionDesactivada;
	Manual modoEstacionamientoManual;
	Automatico modoEstacionamientoAutomatico;




	@BeforeEach
	void setUp() throws Exception 
	{
		modo = new ModalidadConduciendo();
		
		cel = mock( CelularDeUsuario.class );
		when( cel.getNroCelular() ).thenReturn("1145241966");
		when( cel.getUbicacion() ).thenReturn(new Point(1,2));
		// when( sem.obtenerSaldoCelular(cel.getNroCelular()) ).thenReturn(100.0);
		
		
		// El SEM lo seteo como objeto real por problemas con el manejo de listas
		sem = new SistemaEstacionamiento();
		sem.cargarCelular("1145241966", 100.0);

		// App( Celular cel, SistemaEstacionamiento sistema, String patenteAsociada )
	
		aplicacion = spy( new App( cel, sem, "GIO 002" ) );
		modoNotificacionDesactivada = spy( NotificacionDesactivada.class );
		modoNotificacionActivada = spy( NotificacionActivada.class );
		modoEstacionamientoManual = spy( Manual.class);
		modoEstacionamientoAutomatico = spy( Automatico.class );
		aplicacion.setModoDesplazamiento(modo);
		aplicacion.setModoEstacionamiento(modoEstacionamientoManual);
		// when( aplicacion.getPatente() ).thenReturn("GIO 002");

		// Set condiciones para poder estacionar
		when( aplicacion.estaDentroDeZonaEstacionamiento() ).thenReturn(true);
		when( aplicacion.seEncuentraEnFranjaHoraria() ).thenReturn(true);
	
	}

	@Test
	void verificoQueAppNoTengaEstacionamientosVigentes()
	{
		assertFalse( aplicacion.tieneEstacionamientoVigente() );
	}
	
	@Test
	void verificoQueNoSeEnvíenMensajesConNotificaciónDesactivada() throws Exception
	{
		aplicacion.setModoNotificacion(modoNotificacionDesactivada);
		modo.caminando(aplicacion, "GIO 002");
		verify(aplicacion, never()).notificarUsuario("Posible inicio de estacionamiento");
	}
	
	
	@Test
	void verificoQueEfectivamenteSeEnvíenMensajesConNotificaciónActivada() throws Exception
	{
		aplicacion.setModoNotificacion(modoNotificacionActivada);
		modo.caminando(aplicacion, aplicacion.getPatente());
		verify(aplicacion, times(1)).notificarUsuario("Posible inicio de estacionamiento");	
	}
	

	@Test
	void verificoQueNoSeEjecuteEstacionamientoEnModoManual() throws Exception
	{
		aplicacion.setModoNotificacion(modoNotificacionActivada);
		modo.caminando(aplicacion, "GIO 002");
		verify(aplicacion, never()).iniciarEstacionamiento("GIO 002");
	}
	
	
	@Test
	void verificoQueAppTengaEstacionamientoVigente()
	{
		aplicacion.iniciarEstacionamiento( aplicacion.getPatente() );
		assertEquals( 1,  sem.getEstacionamientos().size() );
		assertTrue( aplicacion.tieneEstacionamientoVigente() );
	}
	
	
	@Test
	void verificoQueEfectivamenteSeEjecuteEstacionamientoEnModoAutomatico() throws Exception
	{
		aplicacion.setModoNotificacion(modoNotificacionActivada);
		aplicacion.setModoEstacionamiento(modoEstacionamientoAutomatico);
		modo.caminando(aplicacion, "GIO 002");
		verify(modoEstacionamientoAutomatico, times(1)).iniciarEstacionamiento(aplicacion, "GIO 002");
		verify(aplicacion, times(1)).iniciarEstacionamiento( aplicacion.getPatente() );
		verify(aplicacion, times(1)).notificarUsuario("Inicio de estacionamiento realizado de forma automática");
		verify(aplicacion, times(1)).setModoDesplazamiento( any(ModalidadCaminando.class) );
		verify(aplicacion, times(1)).setUbicacionEstacionamiento( aplicacion.getUbicacionActual() );
	}
	
	
	@Test
	void verificoQueEnModalidadManualNoSeEjecuteElUpdate() throws Exception
	{
		assertFalse( aplicacion.tieneEstacionamientoVigente() );
		aplicacion.setModoNotificacion(modoNotificacionActivada);
		modo.caminando(aplicacion, "GIO 002");
		verify(aplicacion, never()).setModoDesplazamiento( any(ModalidadCaminando.class) );
		verify(aplicacion, never()).setUbicacionEstacionamiento( aplicacion.getUbicacionActual() );
		Exception error = assertThrows(Exception.class, () ->
		{
	       aplicacion.verificarSiPoseeEstacionamientoVigente();
	    });
	}
	
	@Test
	void verificoQueNoSeEjecuteNadaSiYaTengoEstacionamientoVigente() throws Exception
	{
		aplicacion.setModoNotificacion(modoNotificacionActivada);
		aplicacion.iniciarEstacionamiento(aplicacion.getPatente());
		modo.caminando(aplicacion, aplicacion.getPatente());
		Exception error = assertThrows(Exception.class, () ->
		{
	       aplicacion.verificarSiPoseeEstacionamientoVigente();
	    });
	}
	

	
}