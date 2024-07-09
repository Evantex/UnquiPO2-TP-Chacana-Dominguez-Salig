package tp.po2.sem.app;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import tp.po2.sem.sistemaEstacionamiento.*;
import static org.junit.jupiter.api.Assertions.*;
import tp.po2.sem.appModoNotificaciones.*;
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
	Celular cel;
	NotificacionActivada modoNotificacionActivada;
	NotificacionDesactivada modoNotificacionDesactivada;
	Manual modoEstacionamientoManual;
	Automatico modoEstacionamientoAutomatico;




	@BeforeEach
	void setUp() throws Exception 
	{
		modo = new ModalidadConduciendo();
		Celular celularOriginal = new Celular("1145241966", 0.0);
		cel = spy( celularOriginal );
		
		
		// when( cel.getNroCelular() ).thenReturn("1145241966");
		// when( cel.getUbicacion() ).thenReturn(new Point(1,2));
		// when( sem.obtenerSaldoCelular(cel.getNroCelular()) ).thenReturn(100.0);
		
		
		// El SEM lo seteo como objeto real por problemas con el manejo de listas
		sem = new SistemaEstacionamiento();
		// sem.agregarUsuario(cel);
		// sem.cargarCelular("1145241966", 100.0);

		// App( Celular cel, SistemaEstacionamiento sistema, String patenteAsociada )
	
		aplicacion = spy( new App( cel, sem, "GIO 002" ) );
		modoNotificacionDesactivada = spy( NotificacionDesactivada.class );
		modoNotificacionActivada = spy( NotificacionActivada.class );
		modoEstacionamientoManual = spy( Manual.class);
		modoEstacionamientoAutomatico = spy( Automatico.class );
		aplicacion.setModoDeDesplazamiento(modo);
		aplicacion.setModoEstacionamiento(modoEstacionamientoManual);
		// when( aplicacion.getPatente() ).thenReturn("GIO 002");

		// Set condiciones para poder estacionar
		when( aplicacion.estaDentroDeZonaEstacionamiento() ).thenReturn(true);
		// when( aplicacion.seEncuentraEnFranjaHoraria() ).thenReturn(true);
	
	}

	@Test
	void verificoQueAppNoTengaEstacionamientosVigentes()
	{
		assertFalse( sem.poseeEstacionamientoVigente(aplicacion.getPatente()) );
	}

	@Test
	void verificoQueNoSeEnvíenMensajesConNotificaciónDesactivada() throws Exception
	{
		aplicacion.setModoNotificacion(modoNotificacionDesactivada);
		modo.caminando(aplicacion);
		verify(aplicacion, times(1)).notificarUsuario("Posible inicio de estacionamiento");
		verify(cel, never()).recibirMensaje("Posible inicio de estacionamiento");
	}
	

	@Test
	void verificoQueEfectivamenteSeEnvíenMensajesConNotificaciónActivada() throws Exception
	{
		aplicacion.setModoNotificacion(modoNotificacionActivada);
		modo.caminando(aplicacion);
		verify(aplicacion, times(1)).notificarUsuario("Posible inicio de estacionamiento");
		verify(cel, times(1)).recibirMensaje("Posible inicio de estacionamiento");
	}
	


	@Test
	void verificoQueNoSeEjecuteEstacionamientoEnModoManual() throws Exception
	{
		aplicacion.setModoNotificacion(modoNotificacionActivada);
		modo.caminando(aplicacion);
		verify(aplicacion, never()).iniciarEstacionamiento();
	}
	
	
	@Test
	void verificoQueSEMRetornePrecioPorHoraCorrectamente()
	{
		assertEquals( 40.0, sem.getPrecioPorHora() );
	}
	
	@Test
	void verificoFuncionamientoListaUsuariosSEM() throws Exception
	{
		assertEquals( 0, sem.getCantidadUsuarios() );
		sem.cargarCelular("1145241966", 200.0);
		assertEquals( 1, sem.getCantidadUsuarios() );
		assertEquals( cel, sem.getCelular("1145241966") );
		
		
		// assertEquals( 200.0, cel.getSaldo() );
		
		
		
		// aplicacion.verificarSaldoSuficiente(); // Efectivamente posee saldo, de lo contrario daría error
	}
	
	
	/*
	@Test
	void verificoQueValideCorrectamenteQueEsPosibleEstacionar() throws Exception
	{
		aplicacion.verificarEstacionamientoVigente();
		aplicacion.verificarSaldoSuficiente();
		aplicacion.verificarZonaEstacionamiento();
	}
	*/
	
	/*
	@Test
	void verificoQueAppTengaEstacionamientoVigente()
	{
		when( aplicacion.tieneEstacionamientoVigente() ).thenReturn(false);
		when( aplicacion.estaDentroDeZonaEstacionamiento() ).thenReturn(true);
		
		aplicacion.iniciarEstacionamiento();
		assertEquals( 1,  sem.getCantidadEstacionamientos() );
		assertTrue( aplicacion.tieneEstacionamientoVigente() );
	}
	*/
	
	
/*
	
	@Test
	void verificoQueEfectivamenteSeEjecuteEstacionamientoEnModoAutomatico() throws Exception
	{
		aplicacion.setModoNotificacion(modoNotificacionActivada);
		aplicacion.setModoEstacionamiento(modoEstacionamientoAutomatico);
		modo.caminando(aplicacion);
		verify(modoEstacionamientoAutomatico, times(1)).iniciarEstacionamiento(aplicacion);
		verify(aplicacion, times(1)).iniciarEstacionamiento();
		verify(aplicacion, times(1)).notificarUsuario("Se ha iniciado un estacionamiento de forma automática");
		verify(aplicacion, times(1)).setModoDeDesplazamiento( any(ModalidadCaminando.class) );
		verify(aplicacion, times(1)).setUbicacionEstacionamiento( aplicacion.getUbicacionActual() );
	}
	
	
	@Test
	void verificoQueEnModalidadManualNoSeEjecuteElUpdate() throws Exception
	{
		assertFalse( aplicacion.tieneEstacionamientoVigente() );
		aplicacion.setModoNotificacion(modoNotificacionActivada);
		modo.caminando(aplicacion);
		verify(aplicacion, never()).setModoDeDesplazamiento( any(ModalidadCaminando.class) );
		verify(aplicacion, never()).setUbicacionEstacionamiento( aplicacion.getUbicacionActual() );
		Exception error = assertThrows(Exception.class, () ->
		{
	       aplicacion.verificarEstacionamientoVigente();
	    });
	}
	
	@Test
	void verificoQueNoSeEjecuteNadaSiYaTengoEstacionamientoVigente() throws Exception
	{
		aplicacion.setModoNotificacion(modoNotificacionActivada);
		aplicacion.iniciarEstacionamiento();
		modo.caminando(aplicacion);
		Exception error = assertThrows(Exception.class, () ->
		{
	       aplicacion.verificarEstacionamientoVigente();
	    });
	}
*/

	
}
