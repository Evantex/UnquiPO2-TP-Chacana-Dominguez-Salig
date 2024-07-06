package tp.po2.sem.app;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ModalidadCaminandoTest {

	@Mock
	private App aplicacion; // Se crea un mock para la clase App

	@Mock
	private ModoApp modoEstacionamiento; // Se crea un mock para la clase ModoEstacionamiento

	@InjectMocks
	private ModalidadCaminando modalidadCaminando; // Se crea una instancia de ModalidadCaminando y se inyectan los
													// mocks

	@BeforeEach
	void setUp() {
		// Inicializa los mocks y la instancia de ModalidadCaminando
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testConduciendo_conEstacionamientoVigenteYEnElMismoPuntoGeografico() throws Exception {
		// Configura el comportamiento de los mocks
		when(aplicacion.tieneEstacionamientoVigente()).thenReturn(true);
		when(aplicacion.validarMismoPuntoGeografico()).thenReturn(true);
		when(aplicacion.getModoEstacionamiento()).thenReturn(modoEstacionamiento);

		// Llama al método que se está probando
		modalidadCaminando.conduciendo(aplicacion);

		// Verifica que los métodos correctos fueron llamados en los mocks
		verify(aplicacion).notificarUsuario("Alerta Fin estacionamiento");
		verify(modoEstacionamiento).finalizarEstacionamiento(aplicacion);
		verify(aplicacion).setModoDeDesplazamiento(any(ModalidadConduciendo.class));
	}

	@Test
	void testConduciendo_sinEstacionamientoVigente() throws Exception {
		// Configura el comportamiento de los mocks
		when(aplicacion.tieneEstacionamientoVigente()).thenReturn(false);

		// Llama al método que se está probando
		modalidadCaminando.conduciendo(aplicacion);

		// Verifica que no se llamaron los métodos en los mocks
		verify(aplicacion, never()).validarMismoPuntoGeografico();
		verify(aplicacion, never()).notificarUsuario(anyString());
		verify(modoEstacionamiento, never()).finalizarEstacionamiento(any());
		verify(aplicacion, never()).setModoDeDesplazamiento(any(ModalidadConduciendo.class));
	}

	@Test
	void testConduciendo_conEstacionamientoVigentePeroDiferentePuntoGeografico() throws Exception {
		// Configura el comportamiento de los mocks
		when(aplicacion.tieneEstacionamientoVigente()).thenReturn(true);
		when(aplicacion.validarMismoPuntoGeografico()).thenReturn(false);

		// Llama al método que se está probando
		modalidadCaminando.conduciendo(aplicacion);

		// Verifica que no se llamaron los métodos en los mocks
		verify(aplicacion, never()).notificarUsuario(anyString());
		verify(modoEstacionamiento, never()).finalizarEstacionamiento(any());
		verify(aplicacion, never()).setModoDeDesplazamiento(any(ModalidadConduciendo.class));
	}

	@Test
	void testUpdate() {
		// Llama al método que se está probando
		modalidadCaminando.update(aplicacion);

		// Verifica que el método correcto fue llamado en el mock
		verify(aplicacion).setModoDeDesplazamiento(any(ModalidadConduciendo.class));
	}

	@Test
	void testCaminandoNoHaceNada() {
		modalidadCaminando.caminando(aplicacion);
		verifyNoInteractions(aplicacion);
	}

}
