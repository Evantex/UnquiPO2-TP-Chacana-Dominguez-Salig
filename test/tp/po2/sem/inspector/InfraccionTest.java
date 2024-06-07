/**
 * 
 */
package tp.po2.sem.inspector;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import tp.po2.sem.ZonaDeEstacionamiento.ZonaDeEstacionamiento;

class InfraccionTest {
	
	private String patente;
	private LocalDateTime fechaYHora;
	private Inspector inspector;
	private ZonaDeEstacionamiento zona;
	private Infraccion infraccion;
	
	@BeforeEach
	public void setUp() {
		patente = "BBB333";
		fechaYHora = LocalDateTime.now();
		inspector = mock (Inspector.class);
		zona = mock(ZonaDeEstacionamiento.class);
		infraccion = new Infraccion(patente, fechaYHora, inspector, zona); 
	}
	
	@Test
	public void testGetPatente() {
		assertEquals(patente, infraccion.getPatente());
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"AAA111", "BBB222", "CCC333"}) // valores de ejemplo para el parámetro
	public void testSetPatente(String nuevaPatente) {
	    infraccion.setPatente(nuevaPatente);
	    assertEquals(nuevaPatente, infraccion.getPatente());
	}

	
	@Test
	public void testGetFechaYHora() {
		assertEquals(fechaYHora, infraccion.getFechaYHora());
	}
	
	@Test
	public void testSetFechaYHora() {
		LocalDateTime nuevaFechaYHora = LocalDateTime.of(2024, 6, 7, 10, 30, 45);
		infraccion.setFechaYHora(nuevaFechaYHora);
		assertEquals(nuevaFechaYHora, infraccion.getFechaYHora());
	}
	
	@Test
	public void testGetZona() {
		assertEquals(zona, infraccion.getZona());
	}
	
	@Test
	public void testSetZona() {
		ZonaDeEstacionamiento nuevaZona = mock (ZonaDeEstacionamiento.class);
		infraccion.setZona(nuevaZona);
		assertEquals(nuevaZona, infraccion.getZona());
	}
	
	@Test
	public void testGetInspector() {
		assertEquals(inspector, infraccion.getInspector());
	}
	
	@Test
	public void testSetInspector() {
		Inspector nuevoInspector = mock (Inspector.class);
		infraccion.setInspector(nuevoInspector);
		assertEquals(nuevoInspector, infraccion.getInspector());
	}

}
