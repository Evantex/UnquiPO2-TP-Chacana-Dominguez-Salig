package tp.po2.sem.sistemaEstacionamiento;

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
import tp.po2.sem.app.CelularDeUsuario;
import tp.po2.sem.estacionamiento.Estacionamiento;
import tp.po2.sem.inspector.Infraccion;
import tp.po2.sem.puntoDeVenta.Compra;

public class SistemaEstacionamientoTest {

	SistemaEstacionamiento sistemaEstacionamiento;
	Estacionamiento estacionamientoMock;
	Set<Estacionamiento> spyListaEstacionamientos;
	private CelularDeUsuario celular;
	private Observer callCenter;
    private Observer otroSistema;
    private App appUsuario;
    

	@BeforeEach
	public void setUp() {
		// Creamos el mock de Estacionamiento
		estacionamientoMock = mock(Estacionamiento.class);

		// Creamos un spy de la lista de estacionamientos
		spyListaEstacionamientos = spy(new HashSet<Estacionamiento>());

		// Creamos el SistemaEstacionamiento con el spy de la lista de estacionamientos
		sistemaEstacionamiento = new SistemaEstacionamiento();
		
		celular = mock (CelularDeUsuario.class);
		
		appUsuario = mock (App.class);
		
		callCenter = mock(Observer.class);
        otroSistema = mock(Observer.class);
        
      
        
        
	}
	
	//TEST GETTERS Y SETTERS
	
	@Test
    void testGetHoraLaboralInicio() {
        LocalTime horaLaboralInicio = LocalTime.of(7, 0);
        assertEquals(horaLaboralInicio, sistemaEstacionamiento.getHoraLaboralInicio());
    }

    @Test
    void testSetHoraLaboralInicio() {
        LocalTime nuevaHora = LocalTime.of(8, 0);
        sistemaEstacionamiento.setHoraLaboralInicio(nuevaHora);
        assertEquals(nuevaHora, sistemaEstacionamiento.getHoraLaboralInicio());
    }

    @Test
    void testGetHoraLaboralFin() {
        LocalTime horaLaboralFin = LocalTime.of(20, 0);
        assertEquals(horaLaboralFin, sistemaEstacionamiento.getHoraLaboralFin());
    }

    @Test
    void testSetHoraLaboralFin() {
        LocalTime nuevaHora = LocalTime.of(21, 0);
        sistemaEstacionamiento.setHoraLaboralFin(nuevaHora);
        assertEquals(nuevaHora, sistemaEstacionamiento.getHoraLaboralFin());
    }

    @Test
    void testGetEstacionamientos() {
        sistemaEstacionamiento.setEstacionamientos(spyListaEstacionamientos);
        assertEquals(spyListaEstacionamientos, sistemaEstacionamiento.getEstacionamientos());
    }

    @Test
    void testSetEstacionamientos() {
        Set<Estacionamiento> nuevosEstacionamientos = new HashSet<>();
        sistemaEstacionamiento.setEstacionamientos(nuevosEstacionamientos);
        assertEquals(nuevosEstacionamientos, sistemaEstacionamiento.getEstacionamientos());
    }

    @Test
    void testGetComprasPuntoDeVenta() {
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
        sistemaEstacionamiento.registrarEstacionamientoApp(mock(Estacionamiento.class));
        assertEquals(1, sistemaEstacionamiento.getCantidadEstacionamientos());
    }

	@Test
	void testGetUsuarios() {
		Set<CelularDeUsuario> usuarios = new HashSet<>();
		assertEquals(usuarios, sistemaEstacionamiento.getUsuarios());
	}
	
	@Test 
	void testSetUsuarios() {
		Set<CelularDeUsuario> nuevosUsuarios = new HashSet<>();
		sistemaEstacionamiento.setUsuarios(nuevosUsuarios);
		assertEquals(nuevosUsuarios, sistemaEstacionamiento.getUsuarios());
	}
	
	@Test
	public void testRegistrarUnEstacionamiento() {
		// Ejecutamos el método que queremos probar, pasando el mock de Estacionamiento
		sistemaEstacionamiento.solicitudDeEstacionamientoCompraPuntual(estacionamientoMock);

		// Con el spy verifico que se le mandó correctamente el mensaje add a la
		// colección dentro del mensaje getCantidadEstacionamientos().
		assertEquals(sistemaEstacionamiento.getCantidadEstacionamientos(), 1);

		// Además se verifica que el envío de mensajes se haga en orden esperado.
		InOrder orden = inOrder(spyListaEstacionamientos);
		orden.verify(spyListaEstacionamientos).add(estacionamientoMock);

		// Verifico con el spy que se le mandó el mensaje size a la colección para
		// obtener la cantidad de estacionamientos esperada.
		verify(spyListaEstacionamientos).size();
	}
	

	
	@Test
	public void testElSistemaAgregaUnUsuarioASuListaDeUsuarios() {
		
		sistemaEstacionamiento.agregarUsuario(celular);
		Set<CelularDeUsuario> usuarios = sistemaEstacionamiento.getUsuarios();
		
		assertEquals(1, usuarios);
		assertTrue(usuarios.contains(celular));
	}
	
	@Test
	public void testCuandoElSistemaCargaUnCelularNuevoLoAgregaASuListaDeUsuarios() {
		String nroCelular = "1234567890";
		double saldo = 100.0;
		Set<CelularDeUsuario> usuarios = sistemaEstacionamiento.getUsuarios();
		
		// Cargar saldo por primera vez
		sistemaEstacionamiento.cargarCelular(nroCelular, saldo);

		// Verificar que el saldo se haya cargado correctamente
		 assertEquals(1, sistemaEstacionamiento.getUsuarios().size());
		 assertTrue(usuarios.contains(celular));
	}

	@Test
	public void testCargarCelularExistente() {
		String nroCelular = "1234567890";
        double saldoRecarga = 20.0;
		
		sistemaEstacionamiento.agregarUsuario(celular);
		
		sistemaEstacionamiento.cargarCelular(nroCelular, saldoRecarga);

		verify(celular).recibirRecargaDeSaldo(saldoRecarga);
	}

	@Test
	public void testCargarCelularMultiplesVeces() {
		String nroCelular = "1234567890";
		double saldo1 = 50.0;
		double saldo2 = 25.0;
		double saldo3 = 75.0;

		// Cargar saldo varias veces
		sistemaEstacionamiento.cargarCelular(nroCelular, saldo1);
		sistemaEstacionamiento.cargarCelular(nroCelular, saldo2);
		sistemaEstacionamiento.cargarCelular(nroCelular, saldo3);

		// Verificar que el saldo se haya sumado correctamente
		double saldoEsperado = saldo1 + saldo2 + saldo3;
		assertEquals(saldoEsperado, sistemaEstacionamiento.obtenerSaldoCelular(nroCelular), 0.01);
	}
	
	 @Test
	    public void testNotificarObservadores_IniciarEstacionamiento() {
		 
		 sistemaEstacionamiento.agregarObservador(callCenter);
		 sistemaEstacionamiento.agregarObservador(otroSistema);

	     appUsuario.iniciarEstacionamiento("Patente");

	     EventoEstacionamiento eventoEsperado = new EventoEstacionamiento(EventoEstacionamiento.Tipo.INICIO, "Patente", "nroTel");
	     verify(callCenter).actualizar(eventoEsperado);
	     verify(otroSistema).actualizar(eventoEsperado);
	    }
	 
	 @Test
	 public void testNotificarObservadores_FinalizarEstacionamiento() {
	     sistemaEstacionamiento.agregarObservador(callCenter);
	     sistemaEstacionamiento.agregarObservador(otroSistema);

	     appUsuario.iniciarEstacionamiento("Patente");  // Primero iniciar el estacionamiento

	     sistemaEstacionamiento.finalizarEstacionamiento("Patente");  // Llamar directamente a SEM para finalizar

	     EventoEstacionamiento eventoEsperado = new EventoEstacionamiento(EventoEstacionamiento.Tipo.FIN, "Patente", "nroTel");
	     verify(callCenter).actualizar(eventoEsperado);
	     verify(otroSistema).actualizar(eventoEsperado);
	 }
	 
	 @Test
	 public void testAgregarYEliminarObservador() {
	     sistemaEstacionamiento.agregarObservador(callCenter);
	     sistemaEstacionamiento.eliminarObservador(callCenter);

	     appUsuario.iniciarEstacionamiento();

	     EventoEstacionamiento eventoNoEsperado = new EventoEstacionamiento(EventoEstacionamiento.Tipo.INICIO, "Patente", "nroTel");
	     verify(callCenter, never()).actualizar(eventoNoEsperado);
	 }

	 
	 @Test
	 public void testCalculoDeHorasInicioEstacionamientoDe5AmA10Am() {
		 
		 double resultadoEsperado = sistemaEstacionamiento.calcularCuantoCobrar(LocalTime.of(5, 0), Duration.ofHours(5));
		 
		 assertEquals (resultadoEsperado, 120.0);
		 
	 }
	 
	 @Test
	 public void testCalculoDeHorasInicioEstacionamientoDe2amA10Am() {
		 
		 double resultadoEsperado = sistemaEstacionamiento.calcularCuantoCobrar(LocalTime.of(2, 0), Duration.ofHours(8));
		 
		 assertEquals (resultadoEsperado, 120.0);
		 
	 }
	 
	 @Test
	 public void testCalculoDeHorasInicioEstacionamientoDe10amA12pm() {
		 
		 double resultadoEsperado = sistemaEstacionamiento.calcularCuantoCobrar(LocalTime.of(10, 0), Duration.ofHours(4));
		 
		 assertEquals (resultadoEsperado, 160.0);
		 
	 }
}
