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
		aplicacion = spy( App.class );
		modoNotificacionDesactivada = spy( NotificacionDesactivada.class );
		modoNotificacionActivada = spy( NotificacionActivada.class );
		modoEstacionamientoManual = spy( Manual.class);
		modoEstacionamientoAutomatico = spy( Automatico.class );
		
	

		
		/*
		when( cel.getNroCelular() ).thenReturn("1145241966");
		when( cel.estaDentroDeZonaEstacionamiento() ).thenReturn(true);
		*/
		
		
		
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


}
