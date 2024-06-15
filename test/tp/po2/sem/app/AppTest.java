package tp.po2.sem.app;
import tp.po2.sem.sistemaEstacionamiento.*;
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
	CelularDeUsuario cel;
	SistemaEstacionamiento sem;
	ModoDesplazamiento modalidadCaminando;
	ModoDesplazamiento modalidadConduciendo;
	
	@BeforeEach
	void setUp() throws Exception 
	{
		cel = mock( CelularDeUsuario.class );
		sem = mock( SistemaEstacionamiento.class );
		modalidadCaminando = spy( ModalidadCaminando.class );
		modalidadConduciendo = spy( ModalidadConduciendo.class );
		aplicacion = new App( cel, sem, "GIO 002" );
		
		
		when( cel.getNroCelular() ).thenReturn("1145241966");
		when( cel.estaDentroDeZonaEstacionamiento() ).thenReturn(true);
		when( sem.poseeEstacionamientoVigente( "1145241966") ).thenReturn(false);
		
		
		
		
		// App( Celular cel, SistemaEstacionamiento sistema, String patenteAsociada )
	}
	
	@Test
	void asignoNotificacionDesactivadaYChequeoQueNoSeEnvíeElMensajeAlUsuario()
	{
		aplicacion.setModoNotificacion(new NotificacionDesactivada() );
		aplicacion.setModoDesplazamiento(modalidadConduciendo);
		aplicacion.setModoEstacionamiento( new Manual() );
		aplicacion.walking();
		verify( modalidadConduciendo ).caminando(aplicacion, aplicacion.getPatente() );
		verify(cel, never()).recibirMensaje("Posible inicio de estacionamiento");
	}
	
	@Test
	void asignoNotificacionActivadaYChequeoQueEfectivamenteSeEnvíeElMensajeAlUsuario()
	{
		aplicacion.setModoNotificacion(new NotificacionActivada() );
		aplicacion.setModoDesplazamiento(modalidadConduciendo);
		aplicacion.setModoEstacionamiento( new Manual() );
		aplicacion.walking();
		// verify( modalidadConduciendo ).caminando(aplicacion, aplicacion.getPatente() );
		verify(cel, times(1)).recibirMensaje("Posible inicio de estacionamiento");	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
