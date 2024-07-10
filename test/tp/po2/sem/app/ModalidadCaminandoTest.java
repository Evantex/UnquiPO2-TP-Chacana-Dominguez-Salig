package tp.po2.sem.app;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import tp.po2.sem.app.*;

import static org.mockito.Mockito.*;

public class ModalidadCaminandoTest {

    private ModalidadCaminando modalidadCaminando;
    private App mockApp;
    private EstadoEstacionamiento mockEstadoEstacionamiento;
    private ModoApp mockModoEstacionamiento;

    @BeforeEach
    public void setUp() {
        modalidadCaminando = new ModalidadCaminando();
        mockApp = mock(App.class);
        mockEstadoEstacionamiento = mock(EstadoEstacionamiento.class);
        mockModoEstacionamiento = mock(ModoApp.class);

        when(mockApp.getEstadoEstacionamiento()).thenReturn(mockEstadoEstacionamiento);
        when(mockApp.getModoEstacionamiento()).thenReturn(mockModoEstacionamiento);
    }

    @Test
    public void testCaminando() {
        modalidadCaminando.caminando(mockApp);
        
        
        verifyNoInteractions(mockApp);
    }

    @Test
    public void testConduciendo() throws Exception {
        modalidadCaminando.conduciendo(mockApp);
        
        verify(mockApp.getEstadoEstacionamiento()).posibleFinEstacionamiento(eq(modalidadCaminando), eq(mockApp));
        verify(mockApp).setModoDeDesplazamiento(any(ModalidadConduciendo.class));
    }

    @Test
    public void testUpdate() {
        modalidadCaminando.update(mockApp);
        
        verify(mockApp).setModoDeDesplazamiento(any(ModalidadConduciendo.class));
    }

    @Test
    public void testInicioEstacionamiento() throws Exception {
        modalidadCaminando.inicioEstacionamiento(mockApp);
        
        
        verifyNoInteractions(mockApp);
    }

    @Test
    public void testFinEstacionamiento() throws Exception {
        modalidadCaminando.finEstacionamiento(mockApp);
        
        verify(mockApp).notificarUsuario("Posible fin de estacionamiento.");
        verify(mockApp.getModoEstacionamiento()).finalizarEstacionamiento(mockApp);
    }


	/*@Test
	void testUpdate() {
		
		modalidadCaminando.update(aplicacion);

		
		verify(aplicacion).setModoDeDesplazamiento(any(ModalidadConduciendo.class));
	}

	@Test
	void testCaminandoNoHaceNada() {
		modalidadCaminando.caminando(aplicacion);
		verifyNoInteractions(aplicacion);
	}
*/
}
