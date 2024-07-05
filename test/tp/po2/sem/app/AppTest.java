package tp.po2.sem.app;
import tp.po2.sem.sistemaEstacionamiento.*;
import tp.po2.sem.appModoNotificaciones.NotificacionActivada;
import tp.po2.sem.appModoNotificaciones.NotificacionDesactivada;
import tp.po2.sem.estacionamiento.*;
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

public class AppTest 
{
	App aplicacion;
	Celular cel;
	SistemaEstacionamiento sem;
	ModoDesplazamiento modalidadCaminando;
	ModoDesplazamiento modalidadConduciendo;
	
	@BeforeEach
	void setUp() throws Exception 
	{
		cel = mock( Celular.class );
		sem = mock( SistemaEstacionamiento.class );
		modalidadCaminando = spy( ModalidadCaminando.class );
		modalidadConduciendo = spy( ModalidadConduciendo.class );
		aplicacion = new App( cel, sem, "GIO 002" );
		
		
		when( cel.getNroCelular() ).thenReturn("1145241966");
		when( cel.estaDentroDeZonaEstacionamiento() ).thenReturn(true);
		when( sem.poseeEstacionamientoVigente( "GIO 002") ).thenReturn(false);
		
		
		
		
		// App( Celular cel, SistemaEstacionamiento sistema, String patenteAsociada )
	}
	
	@Test
	void asignoNotificacionDesactivadaYChequeoQueNoSeEnvíeElMensajeAlUsuario() throws Exception
	{
		aplicacion.setModoNotificacion(new NotificacionDesactivada() );
		aplicacion.setModoDesplazamiento(modalidadConduciendo);
		aplicacion.setModoEstacionamiento( new Manual() );
		aplicacion.walking();
		verify( modalidadConduciendo ).caminando(aplicacion, aplicacion.getPatente() );
		verify(cel, never()).recibirMensaje("Posible inicio de estacionamiento");
	}
	
	@Test
	void asignoNotificacionActivadaYChequeoQueEfectivamenteSeEnvíeElMensajeAlUsuario() throws Exception
	{
		aplicacion.setModoNotificacion(new NotificacionActivada() );
		aplicacion.setModoDesplazamiento(modalidadConduciendo);
		aplicacion.setModoEstacionamiento( new Manual() );
		aplicacion.walking();
		// verify( modalidadConduciendo ).caminando(aplicacion, aplicacion.getPatente() );
		verify(cel, times(1)).recibirMensaje("Posible inicio de estacionamiento");	
	}
	
	@Test
	void testElUsuarioDeLaAppNoTieneSaldoSuficienteParaIniciarUnEstacionamient() throws Exception
	{
		when (cel.getSaldo()).thenReturn(0.0);
		Exception exception = assertThrows(Exception.class, () -> {
	        aplicacion.verificarSaldoSuficiente();
	    });
		assertEquals("No tiene saldo suficiente para estacionar.", exception.getMessage());
	}
	
	@Test 
	void testElUsuarioTieneSaldoSuficienteParaIniciarUnEstacionamiento() throws Exception 
	{
		when (cel.getSaldo()).thenReturn(200.0);
		assertDoesNotThrow(() -> {
            aplicacion.verificarSaldoSuficiente();
        });
	}
	
	@Test
	void testElUsuarioNoSeEncuentraEnLaFranjaHorariaPermitida() throws Exception
	{
		//PUEDE UN USUARIO ESTAR DENTRO DE UN ESTACIONAMIENTO EN UNA FRANJA HORARIO NO PERMITIDA?
	}
	
	@Test
	void testElUsuarioNoPuedeIniciarDosEstacionamientosEnSimultaneoConLaMismaPatente() throws Exception 
	{
		aplicacion.iniciarEstacionamiento();
		
		when( sem.poseeEstacionamientoVigente( "GIO 002") ).thenReturn(true);
		
		Exception exception = assertThrows(Exception.class, () -> {
	        aplicacion.verificarSiPoseeEstacionamientoVigente();
	    });
		
		assertEquals("Ya tienes un estacionamiento vigente", exception.getMessage());
		
	}
	
	@Test
	void testElUsuarioNoTieneUnEstacionamientoVigenteSiNuncaInicioUnEstacionamiento() throws Exception
	{
		assertDoesNotThrow(() -> {
            aplicacion.verificarSiPoseeEstacionamientoVigente();
        });
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
