package tp.po2.sem.app;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import tp.po2.sem.sistemaEstacionamiento.*;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

// import paquetes
import tp.po2.sem.appModoNotificaciones.*;
import tp.po2.sem.estacionamiento.*;
import tp.po2.sem.tarifasEstacionamiento.*;
import tp.po2.sem.sistemaEstacionamiento.*;
import tp.po2.sem.appGPS.*;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
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
	ModalidadConduciendo modoConduciendo;
	App aplicacion;
	SistemaEstacionamiento sem;
	Celular cel;
	NotificacionActivada modoNotificacionActivada;
	NotificacionDesactivada modoNotificacionDesactivada;
	Manual modoEstacionamientoManual;
	Automatico modoEstacionamientoAutomatico;
	Vigente estadoVigente;
	NoVigente estadoNoVigente;




	@BeforeEach
	void setUp() throws Exception 
	{
		modoConduciendo = new ModalidadConduciendo();
		Celular celularOriginal = new Celular("1145241966", 0.0);
		cel = spy( celularOriginal );
		
		// when( cel.getNroCelular() ).thenReturn("1145241966");
		// when( cel.getUbicacion() ).thenReturn(new Point(1,2));
		// when( sem.obtenerSaldoCelular(cel.getNroCelular()) ).thenReturn(100.0);
		
		// El SEM lo seteo como objeto real por problemas con el manejo de listas
		sem = new SistemaEstacionamiento();

		aplicacion = spy( new App( cel, sem, "GIO 002" ) );
		
		when( aplicacion.getUbicacionActual() ).thenReturn(new Point(1,2));
		aplicacion.setEstadoGps( new UbicacionActivada() );
		modoNotificacionDesactivada = spy( NotificacionDesactivada.class );
		modoNotificacionActivada = spy( NotificacionActivada.class );
		modoEstacionamientoManual = spy( Manual.class);
		modoEstacionamientoAutomatico = spy( Automatico.class );
		estadoVigente = spy( Vigente.class );
		estadoNoVigente = spy( NoVigente.class );
		aplicacion.setModoDeDesplazamiento(modoConduciendo);
		aplicacion.setModoEstacionamiento(modoEstacionamientoManual);
		aplicacion.setEstadoEstacionamiento(estadoNoVigente);


		// Set condiciones para poder estacionar
		when( aplicacion.estaDentroDeZonaEstacionamiento() ).thenReturn(true);
		// when( aplicacion.seEncuentraEnFranjaHoraria() ).thenReturn(true);
	
	}

	@Test
	void verificoQueAppNoTengaEstacionamientosVigentes()
	{
		assertFalse( sem.poseeEstacionamientoVigente(aplicacion.getPatenteAsociada()) );
	}

	@Test
	void verificoQueNoSeEnvíenMensajesConNotificaciónDesactivada() throws Exception
	{
		aplicacion.setModoNotificacion(modoNotificacionDesactivada);
		modoConduciendo.caminando(aplicacion);
		
		verify(aplicacion, times(1)).notificarUsuario("Posible inicio de estacionamiento");
		verify(cel, never()).recibirMensaje("Posible inicio de estacionamiento");
	}


	@Test
	void verificoQueEfectivamenteSeEnvíenMensajesConNotificaciónActivada() throws Exception
	{
		aplicacion.setModoNotificacion(modoNotificacionActivada);
		modoConduciendo.caminando(aplicacion);
		verify(aplicacion, times(1)).notificarUsuario("Posible inicio de estacionamiento");
		verify(cel, times(1)).recibirMensaje("Posible inicio de estacionamiento");
	}
	


	@Test
	void verificoQueNoSeEjecuteEstacionamientoEnModoManual() throws Exception
	{
		aplicacion.setModoNotificacion(modoNotificacionActivada);
		modoConduciendo.caminando(aplicacion);
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
		sem.agregarUsuario(cel);
		sem.cargarCelular(sem.getUsuarioPorNro("1145241966"), 200.0);
		assertEquals( 200.0, cel.getSaldo() );
		aplicacion.verificarSaldoSuficiente();
	}
	
	
	@Test
	void verificoQueValideCorrectamenteQueEsPosibleEstacionar() throws Exception
	{
		sem.agregarUsuario(cel);
		sem.cargarCelular(sem.getUsuarioPorNro("1145241966"), 200.0);
		aplicacion.verificarValidacionesParaIniciarEstacionamiento();
	}
	
	
	@Test
	void verificoQueSeGenereUnNuevoEstacionamiento() throws Exception
	{
		sem.agregarUsuario(cel);
		sem.cargarCelular(sem.getUsuarioPorNro("1145241966"), 200.0);
		aplicacion.iniciarEstacionamiento();
		assertEquals( 1, sem.getCantidadEstacionamientos() );
		verify(modoEstacionamientoManual, times(1)).notificacionModoApp(aplicacion, "Se ha iniciado un estacionamiento de forma automática");
		verify(aplicacion, times(0)).notificarUsuario("Se ha iniciado un estacionamiento de forma automática");
	}
	
	
	@Test
	void verificoQueEfectivamenteSeEjecuteEstacionamientoEnModoAutomatico() throws Exception
	{
		aplicacion.setModoNotificacion(modoNotificacionActivada);
		aplicacion.setModoEstacionamiento(modoEstacionamientoAutomatico);
		sem.agregarUsuario(cel);
		sem.cargarCelular(sem.getUsuarioPorNro("1145241966"), 200.0);
		
		/*	
		 	Simulo que se baja del auto en un estacionamiento medido con las 
				condiciones requeridas para estacionar:
		*/
		modoConduciendo.caminando(aplicacion); 
		
		verify(modoEstacionamientoAutomatico, times(1)).iniciarEstacionamiento(aplicacion);
		
		// Funcionamiento de clase Automatico:
		verify(aplicacion, times(1)).iniciarEstacionamiento();
		verify(aplicacion, times(1)).notificarUsuario("Se ha iniciado un estacionamiento de forma automática");
		verify(aplicacion, times(1)).setUbicacionEstacionamiento();
		
		// Se cambia modalidad de desplazamiento y se setea ubicación de estacionamiento:
		verify(aplicacion, times(1)).setModoDeDesplazamiento( any(ModalidadCaminando.class) );
		
		
		// Se envían los detalles del estacionamiento y se setea a Vigente:
		verify(aplicacion, times(1)).enviarDetallesInicioEstacionamiento( any(Estacionamiento.class) );
		verify(aplicacion, times(1)).setEstadoEstacionamiento( any(Vigente.class) ); // Cambia a estado vigente
	}
	
    
	@Test
	void verificoQueEnModalidadManualNoSeConfigureUbicacionEstacionamiento() throws Exception
	{
		// Seteo modo manual, conduciendo sin estacionamiento vigente y notificaciones activadas:
		aplicacion.setModoDeDesplazamiento(modoConduciendo); 
		aplicacion.setModoEstacionamiento(modoEstacionamientoManual);
		aplicacion.setEstadoEstacionamiento(estadoNoVigente);
		aplicacion.setModoNotificacion(modoNotificacionActivada);
		when( aplicacion.estaDentroDeZonaEstacionamiento() ).thenReturn(true);

		modoConduciendo.caminando(aplicacion);
		
		verify(aplicacion, times(1)).setModoDeDesplazamiento( any(ModalidadCaminando.class) );
		verify(aplicacion, never()).setUbicacionEstacionamiento(); 
		assertTrue(aplicacion.getEstadoEstacionamiento() instanceof NoVigente);
		
		/*
			Ya seteada la modalidad caminando, debería volver al vehículo sin un estacionamiento vigente y simplemente debería cambiar modalidad
				de caminando a conduciendo sin emitir ninguna otra acción respecto a estacionamientos, como por ejemplo no notificar fin de estacionamiento
					a pesar de que las notificaciones estén activadas:
		*/
		aplicacion.getModoDeDesplazamiento().conduciendo(aplicacion);
		
		// 	Primer llamamiento en BeforeEach, segundo en línea 208 y tercero en línea 225.
		verify(aplicacion, times(3)).setModoDeDesplazamiento( any(ModalidadConduciendo.class) );

		// No emite acciones referentes a estacionar
		verify(aplicacion, never()).notificarUsuario("Posible fin de estacionamiento."); 
	}
	
	
	@Test
	void verificoFuncionamientoFinEstacionamientoApp() throws Exception
	{
		
		sem.agregarUsuario(cel);
		sem.cargarCelular(sem.getUsuarioPorNro("1145241966"), 200.0);
		aplicacion.setModoEstacionamiento( modoEstacionamientoAutomatico );
		aplicacion.setModoNotificacion(modoNotificacionActivada);
		
		// Se ejecuta todo el proceso de inicio estacionamiento:
		aplicacion.walking();
		
		// Se ejecuta todo el proceso de fin de estacionamiento:
		aplicacion.driving();	 
	}
	
	
	@Test
	void errorValidacionSaldoSuficiente() throws Exception
	{
		sem.agregarUsuario(cel);
		aplicacion.iniciarEstacionamiento();
			Exception error = assertThrows(Exception.class, () ->
			{
				aplicacion.verificarSaldoSuficiente();
			});
	}
		

	@Test
	void errorValidacionZonaEstacionamiento() throws Exception
	{
		sem.agregarUsuario(cel);
		sem.cargarCelular(sem.getUsuarioPorNro("1145241966"), 200.0);
		when( aplicacion.estaDentroDeZonaEstacionamiento() ).thenReturn(false);
		aplicacion.iniciarEstacionamiento();
			Exception error = assertThrows(Exception.class, () ->
			{
				aplicacion.verificarZonaEstacionamiento();
			});
	}

	
		@Test
	void errorValidacionEstacionamientoVigente() throws Exception
	{
		sem.agregarUsuario(cel);
		sem.cargarCelular(sem.getUsuarioPorNro("1145241966"), 200.0);
		aplicacion.iniciarEstacionamiento(); // Inicio un estacionamiento por primera vez
		aplicacion.iniciarEstacionamiento(); // Inicio un estacionamiento por segunda vez sin finalizar el primero
			Exception error = assertThrows(Exception.class, () ->
			{
			      sem.verificarQueNoTengaYaUnEstacionamientoVigente("GIO 002");
			 });	
	}
		
		@Test
		void testeoGPS()
		{
			aplicacion.activarGPS();
			aplicacion.desactivarGPS(); // Utiliza aplicacion.setEstadoGps
		}
		
		@Test
		void testeoAlgunosGetters()
		{
			aplicacion.setCelularAsociado(cel);
			assertEquals( cel, aplicacion.getCelularAsociado() );
			assertEquals( "GIO 002", aplicacion.getPatenteAsociada() );
			aplicacion.setPatenteAsociada("EAC 001");
			assertEquals( "1145241966", aplicacion.getNroCelularAsociado() );
		}
	
}
