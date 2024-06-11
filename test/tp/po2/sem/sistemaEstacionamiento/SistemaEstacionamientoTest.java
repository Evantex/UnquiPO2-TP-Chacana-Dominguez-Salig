package tp.po2.sem.sistemaEstacionamiento;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import tp.po2.sem.estacionamiento.Estacionamiento;

public class SistemaEstacionamientoTest {

	SistemaEstacionamiento sistemaEstacionamiento;
	Estacionamiento estacionamientoMock;
	Set<Estacionamiento> spyListaEstacionamientos;

	@BeforeEach
	public void setUp() {
		// Creamos el mock de Estacionamiento
		estacionamientoMock = mock(Estacionamiento.class);

		// Creamos un spy de la lista de estacionamientos
		spyListaEstacionamientos = spy(new HashSet<Estacionamiento>());

		// Creamos el SistemaEstacionamiento con el spy de la lista de estacionamientos
		sistemaEstacionamiento = new SistemaEstacionamiento(spyListaEstacionamientos);
	}

	@Test
	public void testRegistrarUnEstacionamiento() {
		// Ejecutamos el método que queremos probar, pasando el mock de Estacionamiento
		sistemaEstacionamiento.registrarEstacionamiento(estacionamientoMock);

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
	public void testCargarCelularNuevo() {
		String nroCelular = "1234567890";
		double saldo = 100.0;

		// Cargar saldo por primera vez
		sistemaEstacionamiento.cargarCelular(nroCelular, saldo);

		// Verificar que el saldo se haya cargado correctamente
		assertEquals(saldo, sistemaEstacionamiento.obtenerSaldoCelular(nroCelular), 0.01);
	}

	@Test
	public void testCargarCelularExistente() {
		String nroCelular = "1234567890";
		double saldoInicial = 100.0;
		double saldoAdicional = 50.0;

		// Cargar saldo por primera vez
		sistemaEstacionamiento.cargarCelular(nroCelular, saldoInicial);

		// Cargar saldo adicional
		sistemaEstacionamiento.cargarCelular(nroCelular, saldoAdicional);

		// Verificar que el saldo se haya sumado correctamente
		double saldoEsperado = saldoInicial + saldoAdicional;
		assertEquals(saldoEsperado, sistemaEstacionamiento.obtenerSaldoCelular(nroCelular), 0.01);
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
}
