package tp.po2.sem.sistemaEstacionamiento;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import tp.po2.sem.app.App;
import tp.po2.sem.app.Celular;
import tp.po2.sem.estacionamiento.*;
import tp.po2.sem.inspector.Infraccion;
import tp.po2.sem.inspector.Inspector;
import tp.po2.sem.puntoDeVenta.*;
import tp.po2.sem.ZonaDeEstacionamiento.*;


public class SistemaEstacionamientoTest
{

	SistemaEstacionamiento sistemaEstacionamiento;
	Estacionamiento estacionamientoMock;
	Set<Estacionamiento> spyListaEstacionamientos;
	private Celular celular;
    private App appUsuario;
    
    
    // Spy's estacionamiento:
    EstacionamientoCompraPuntual spyEstacionamientoPuntual;
    EstacionamientoApp spyEstacionamientoApp;
    
    // Spy de app
    App spyApp;
    
    // Spy de compra puntual
    CompraPuntual spyCompraPuntual;
    
    // Spy de zona estacionamiento
    ZonaDeEstacionamiento spyZonaEstacionamiento;
    
    // Mock Inspector
    Inspector mockInspector;
    
    // Spy estacionamiento para verificaciones puntuales
    SistemaEstacionamiento spySistemaEstacionamiento;
    
    
    

	@BeforeEach
	public void setUp() 
	{
		// Creamos el mock de Estacionamiento
		estacionamientoMock = mock(Estacionamiento.class);

		// Creamos un spy de la lista de estacionamientos
		spyListaEstacionamientos = spy(new HashSet<Estacionamiento>());

		// Creamos el SistemaEstacionamiento con el spy de la lista de estacionamientos
		sistemaEstacionamiento = new SistemaEstacionamiento();
		
		celular = mock (Celular.class);
		when( celular.getNroCelular() ).thenReturn("1132339688");
		
		appUsuario = mock (App.class);
        
        // Mocks
        mockInspector = mock( Inspector.class );
        
        
        // Spy's
        spyEstacionamientoPuntual = spy( EstacionamientoCompraPuntual.class );
        spyEstacionamientoApp = spy( EstacionamientoApp.class );
        spyApp = spy( App.class );
        spyZonaEstacionamiento = spy( ZonaDeEstacionamiento.class );
        
        
        spyCompraPuntual = spy( CompraPuntual.class );
		when( spyCompraPuntual.getHoraInicio() ).thenReturn(LocalTime.of(17, 00));
		when( spyCompraPuntual.getHoraFin() ).thenReturn(LocalTime.of(19, 00));
		when( spyCompraPuntual.getHorasCompradas() ).thenReturn( Duration.ofHours(2) );
      
		spySistemaEstacionamiento = spy( new SistemaEstacionamiento() );
        
	}
	
	//TEST GETTERS Y SETTERS
	
	@Test
    void testGetHoraLaboralInicio() {
        LocalTime horaLaboralInicio = LocalTime.of(7, 0);
        assertEquals(horaLaboralInicio, sistemaEstacionamiento.getHoraLaboralInicio());
    }

    @Test
    void testGetHoraLaboralFin() 
    {
        LocalTime horaLaboralFin = LocalTime.of(20, 0);
        assertEquals(horaLaboralFin, sistemaEstacionamiento.getHoraLaboralFin());
    }

    @Test
    void testGetEstacionamientos() 
    {
        sistemaEstacionamiento.setEstacionamientos(spyListaEstacionamientos);
        assertEquals(spyListaEstacionamientos, sistemaEstacionamiento.getEstacionamientos());
    }

    @Test
    void testSetEstacionamientos() 
    {
        Set<Estacionamiento> nuevosEstacionamientos = new HashSet<>();
        sistemaEstacionamiento.setEstacionamientos(nuevosEstacionamientos);
        assertEquals(nuevosEstacionamientos, sistemaEstacionamiento.getEstacionamientos());
    }

    @Test
    void testGetComprasPuntoDeVenta() 
    {
        Set<Compra> compras = new HashSet<>();
        sistemaEstacionamiento.setComprasPuntoDeVenta(compras);
        assertEquals(compras, sistemaEstacionamiento.getComprasPuntoDeVenta());
    }

    @Test
    void testSetComprasPuntoDeVenta() {
        Set<Compra> nuevasCompras = new HashSet<>();
        sistemaEstacionamiento.setComprasPuntoDeVenta(nuevasCompras);
        assertEquals(nuevasCompras, sistemaEstacionamiento.getComprasPuntoDeVenta());
    }

    @Test
    void testGetInfracciones() {
        List<Infraccion> infracciones = new ArrayList<>();
        assertEquals(infracciones, sistemaEstacionamiento.getInfracciones());
    }

    @Test
    void testSetInfracciones() {
        List<Infraccion> nuevasInfracciones = new ArrayList<>();
        sistemaEstacionamiento.setInfracciones(nuevasInfracciones);
        assertEquals(nuevasInfracciones, sistemaEstacionamiento.getInfracciones());
    }

    @Test
    void testGetCantidadEstacionamientos() {
        assertEquals(0, sistemaEstacionamiento.getCantidadEstacionamientos());
        sistemaEstacionamiento.solicitudDeEstacionamientoApp( spyEstacionamientoApp );
        assertEquals(1, sistemaEstacionamiento.getCantidadEstacionamientos());
    }

	@Test
	void testGetUsuarios() {
		Set<Celular> usuarios = new HashSet<>();
		assertEquals(usuarios, sistemaEstacionamiento.getUsuarios());
	}
	
	@Test 
	void testSetUsuarios() {
		Set<Celular> nuevosUsuarios = new HashSet<>();
		sistemaEstacionamiento.setUsuarios(nuevosUsuarios);
		assertEquals(nuevosUsuarios, sistemaEstacionamiento.getUsuarios());
	}
	
	@Test
	public void testRegistrarUnEstacionamiento() throws Exception 
	{
		sistemaEstacionamiento.solicitudDeEstacionamientoCompraPuntual( "GIO 002", spyCompraPuntual );
		
		assertEquals(sistemaEstacionamiento.getCantidadEstacionamientos(), 1);
	}
	

 @Test void testeoGettersYSetters() throws Exception
 {
	 sistemaEstacionamiento.getZonasDeEstacionamiento();
	 sistemaEstacionamiento.setRangoHorario( new RangoHorario( LocalTime.of(12, 00), LocalTime.of(15, 00)) );
	 sistemaEstacionamiento.registrarZonaEstacionamiento( spyZonaEstacionamiento );
	 sistemaEstacionamiento.registrarInfraccion("GIO 002", mockInspector);
	 sistemaEstacionamiento.buscarInfraccionesPorPatente("GIO 002");
	 sistemaEstacionamiento.finalizarTodosLosEstacionamientos();
	 sistemaEstacionamiento.removerZonaEstacionamiento(spyZonaEstacionamiento);
	 sistemaEstacionamiento.puedeEstacionar("GIO 002", LocalTime.of(8, 00), LocalTime.of(15, 00));
	 sistemaEstacionamiento.getSistemaAlertas();
	 sistemaEstacionamiento.notificarSistemaAlertasFinEstacionamiento(estacionamientoMock);
	 
 }
 
@Test void chequeoValidacionSiExisteUsuario() throws Exception
{
	sistemaEstacionamiento.agregarUsuario(celular);
	Exception error = assertThrows(Exception.class, () ->
	{
		sistemaEstacionamiento.agregarUsuario(celular);
	 });	
}
	
	 @Test
	 public void testCalculoDeHorasInicioEstacionamientoDe5AmA10Am() throws Exception {
	     double resultadoEsperado = 120.0;  
	     double resultadoObtenido = sistemaEstacionamiento.calcularCuantoCobrar(LocalTime.of(5, 0), Duration.ofHours(5));
	     assertEquals(resultadoEsperado, resultadoObtenido);
	 }
	 
	 @Test
	 public void testCalculoDeHorasInicioEstacionamientoDe2amA10Am() throws Exception {
		 double resultadoEsperado = 120.0;
		 double resultadoObtenido = sistemaEstacionamiento.calcularCuantoCobrar(LocalTime.of(2, 0), Duration.ofHours(8));
		 
		 assertEquals (resultadoEsperado, resultadoObtenido);
		 
	 }
	 
	 @Test
	 public void testCalculoDeHorasInicioEstacionamientoDe10amA12pm() throws Exception {
		 
		 double resultadoEsperado = 160.0;
		 double resultadoObtenido = sistemaEstacionamiento.calcularCuantoCobrar(LocalTime.of(10, 0), Duration.ofHours(4));
		 
		 assertEquals (resultadoEsperado, resultadoObtenido);
		 
	 }
	 
	 @Test
	 public void testCalculoDeHorasInicioEstacionamientoDe4amA6am() throws Exception {
		 
		 double resultadoEsperado = 0.0;
		 double resultadoObtenido = sistemaEstacionamiento.calcularCuantoCobrar(LocalTime.of(4, 0), Duration.ofHours(2));
		 
		 assertEquals (resultadoEsperado, resultadoObtenido);
		 
	 }
	 
	 @Test
	 public void testCalculoDeHorasInicioEstacionamientoDe9pmA11pm() throws Exception {
		 
		 double resultadoEsperado = 0.0;
		 double resultadoObtenido = sistemaEstacionamiento.calcularCuantoCobrar(LocalTime.of(21, 0), Duration.ofHours(2));
		 
		 assertEquals (resultadoEsperado, resultadoObtenido);
		 
	 }
	 
	 @Test
	 public void testCalculoDeHorasInicioEstacionamientoDe9pmA9am() throws Exception {
		 
		 double resultadoEsperado = 80.0;
		 double resultadoObtenido = sistemaEstacionamiento.calcularCuantoCobrar(LocalTime.of(21, 0), Duration.ofHours(12));
		 
		 assertEquals (resultadoEsperado, resultadoObtenido);
		 
	 }
	 
	    @Test
	    public void testeoPoseeEstacionamientoVigente_EstacionamientoVigente() {
	        
	        when(spyEstacionamientoPuntual.getPatente()).thenReturn("patente");
	        when(spyEstacionamientoPuntual.estaVigente()).thenReturn(true);

	        sistemaEstacionamiento.registrarEstacionamiento(spyEstacionamientoPuntual);

	        assertTrue(sistemaEstacionamiento.poseeEstacionamientoVigente("patente"));
	    }

	    @Test
	    public void testeoPoseeEstacionamientoVigente_NoEstacionamientoVigente() {

	        when(spyEstacionamientoPuntual.getPatente()).thenReturn("patente");
	        when(spyEstacionamientoPuntual.estaVigente()).thenReturn(false);

	        sistemaEstacionamiento.registrarEstacionamiento(spyEstacionamientoPuntual);

	        assertFalse(sistemaEstacionamiento.poseeEstacionamientoVigente("patente"));
	    }

	    @Test
	    public void testeoPoseeEstacionamientoVigente_VariosEstacionamientosSinVigenciaParaPatente() {
	        // Configurar varios estacionamientos donde ninguno esté vigente para la patente deseada
	        EstacionamientoCompraPuntual spyEstacionamiento1 = spy(new EstacionamientoCompraPuntual());
	        EstacionamientoCompraPuntual spyEstacionamiento2 = spy(new EstacionamientoCompraPuntual());

	        when(spyEstacionamiento1.getPatente()).thenReturn("patente");
	        when(spyEstacionamiento1.estaVigente()).thenReturn(false);

	        when(spyEstacionamiento2.getPatente()).thenReturn("patente");
	        when(spyEstacionamiento2.estaVigente()).thenReturn(false);

	        // Registrar los estacionamientos
	        sistemaEstacionamiento.registrarEstacionamiento(spyEstacionamiento1);
	        sistemaEstacionamiento.registrarEstacionamiento(spyEstacionamiento2);

	        // Verificar que no posee estacionamientos vigentes para la patente "patente"
	        assertFalse(sistemaEstacionamiento.poseeEstacionamientoVigente("patente"));
	    }
	 
	 @Test 
	 public void testeoFinalizacionDeTodosLosEstacionamientos () {
		 
		 sistemaEstacionamiento.registrarEstacionamiento(spyEstacionamientoPuntual);
		 
		 sistemaEstacionamiento.finalizarTodosLosEstacionamientos();
		 
		 verify(spyEstacionamientoPuntual).finalizarEstacionamiento();
		 assertFalse(spyEstacionamientoPuntual.estaVigente());
	 }
	 
	   @Test
	    public void testGetEstacionamiento_ExisteYVigente() throws Exception {
		   
	        Estacionamiento mockEstacionamiento1 = mock(Estacionamiento.class);

	        when(mockEstacionamiento1.getIdentificadorEstacionamiento()).thenReturn("123");
	        when(mockEstacionamiento1.estaVigente()).thenReturn(true);

	        sistemaEstacionamiento.registrarEstacionamiento(mockEstacionamiento1);

	        Estacionamiento estacionamiento = sistemaEstacionamiento.getEstacionamiento("123");
	        assertNotNull(estacionamiento);
	        assertEquals("123", estacionamiento.getIdentificadorEstacionamiento());
	    }

	    @Test
	    public void testGetEstacionamiento_NoExiste() {
	        Exception exception = assertThrows(Exception.class, () -> {
	            sistemaEstacionamiento.getEstacionamiento("789");
	        });

	        String expectedMessage = "El estacionamiento no existe o no está vigente";
	        String actualMessage = exception.getMessage();

	        assertTrue(actualMessage.contains(expectedMessage));
	    }

	    @Test
	    public void testGetEstacionamiento_ExistePeroNoVigente() {
	    	
	        Estacionamiento mockEstacionamiento1 = mock(Estacionamiento.class);

	        when(mockEstacionamiento1.getIdentificadorEstacionamiento()).thenReturn("123");
	        when(mockEstacionamiento1.estaVigente()).thenReturn(false);

	        sistemaEstacionamiento.registrarEstacionamiento(mockEstacionamiento1);
	    	
	        Exception exception = assertThrows(Exception.class, () -> {
	            sistemaEstacionamiento.getEstacionamiento("123");
	        });

	        String expectedMessage = "El estacionamiento no existe o no está vigente";
	        String actualMessage = exception.getMessage();

	        assertTrue(actualMessage.contains(expectedMessage));
	    }
	    
	    @Test
	    public void testFinalizarEstacionamiento_EstacionamientoVigente() {
	        
	        when(spyEstacionamientoPuntual.getIdentificadorEstacionamiento()).thenReturn("123");
	        when(spyEstacionamientoPuntual.estaVigente()).thenReturn(true);

	        sistemaEstacionamiento.registrarEstacionamiento(spyEstacionamientoPuntual);

	        sistemaEstacionamiento.finalizarEstacionamiento("123");

	        verify(spyEstacionamientoPuntual, times(1)).finalizarEstacionamiento();
	    }

	    @Test
	    public void testFinalizarEstacionamiento_NoExisteEstacionamiento() {

	        sistemaEstacionamiento.finalizarEstacionamiento("456");

	        verifyNoInteractions(spyEstacionamientoPuntual);
	    }

	    @Test
	    public void testFinalizarEstacionamiento_ExistePeroNoVigente() {

	        when(spyEstacionamientoPuntual.getIdentificadorEstacionamiento()).thenReturn("123");
	        when(spyEstacionamientoPuntual.estaVigente()).thenReturn(false);

	        sistemaEstacionamiento.registrarEstacionamiento(spyEstacionamientoPuntual);

	        sistemaEstacionamiento.finalizarEstacionamiento("123");

	        verify(spyEstacionamientoPuntual, never()).finalizarEstacionamiento();
	    }
}

