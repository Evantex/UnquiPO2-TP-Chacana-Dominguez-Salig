package tp.po2.sem.Reloj;

import static org.junit.Assert.*;

import java.time.LocalTime;

import org.junit.Test;

public class RelojSemTest {

	@Test
	public void testHoraActual() {
		// Crear una instancia de RelojSem (SUT)
		RelojSem relojSem = new RelojSem();

		LocalTime horaObtenida = relojSem.horaActual();

		// Verificar que la hora obtenida no sea nula
		assertNotNull(horaObtenida);

		// verificar que la hora obtenida sea cercana a la hora actual
		LocalTime horaActual = LocalTime.now();
		assertTrue(horaObtenida.isBefore(horaActual.plusSeconds(1)));
		assertTrue(horaObtenida.isAfter(horaActual.minusSeconds(1)));
	}
}
