package tp.po2.sem.sistemaEstacionamiento;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RangoHorarioTest {

    private RangoHorario rango;

    @BeforeEach
    public void setUp() {
        
        LocalTime horaInicio = LocalTime.of(8, 0); // 08:00
        LocalTime horaFin = LocalTime.of(18, 0);   // 18:00
        rango = new RangoHorario(horaInicio, horaFin);
    }
    
    @Test
    public void testGetHoraInicioRango() {
        
        assertEquals(LocalTime.of(8, 0), rango.getHoraInicioRango());
    }

    @Test
    public void testSetHoraInicioRango() {
        
        LocalTime nuevaHoraInicio = LocalTime.of(9, 0);
        rango.setHoraInicioRango(nuevaHoraInicio);
        assertEquals(nuevaHoraInicio, rango.getHoraInicioRango());
    }

    @Test
    public void testGetHoraFinRango() {
        
        assertEquals(LocalTime.of(18, 0), rango.getHoraFinRango());
    }

    @Test
    public void testSetHoraFinRango() {
        
        LocalTime nuevaHoraFin = LocalTime.of(17, 0);
        rango.setHoraFinRango(nuevaHoraFin);
        assertEquals(nuevaHoraFin, rango.getHoraFinRango());
    }
    
    @Test
    public void testValidarHoras_HoraInicioIgualAHoraFin() throws Exception {
        
        LocalTime mismoTiempo = LocalTime.of(12, 0); // 12:00
        rango.validarHoras(mismoTiempo, mismoTiempo);
        assertDoesNotThrow(() -> rango.validarHoras(mismoTiempo, mismoTiempo));
    }
    
    @Test
    public void testValidarHoras_HoraInicioMenorAHoraFin() throws Exception {
        
        LocalTime inicio = LocalTime.of(9, 0);  // 09:00
        LocalTime fin = LocalTime.of(10, 0);    // 10:00
        assertDoesNotThrow(() -> rango.validarHoras(inicio, fin));
    }

    @Test
    public void testValidarHoras_HoraInicioMayorAHoraFin() {
        
        LocalTime inicio = LocalTime.of(14, 0); // 14:00
        LocalTime fin = LocalTime.of(13, 0);   // 13:00
        Exception exception = assertThrows(Exception.class,
                () -> rango.validarHoras(inicio, fin));
        assertEquals("La hora de inicio no puede ser mayor que la hora de fin", exception.getMessage());
    }
    
    @Test
    public void testEstaDentroDelRango_HoraDentroDelRango() {
        
        LocalTime dentroDelRango = LocalTime.of(12, 0); // 12:00
        assertTrue(rango.estaDentroDelRango(dentroDelRango));
    }

    @Test
    public void testEstaDentroDelRango_HoraAntesDelInicio() {
        
        LocalTime antesDelInicio = LocalTime.of(7, 0); // 07:00
        assertFalse(rango.estaDentroDelRango(antesDelInicio));
    }

    @Test
    public void testEstaDentroDelRango_HoraDespuesDelFin() {
        
        LocalTime despuesDelFin = LocalTime.of(19, 0); // 19:00
        assertFalse(rango.estaDentroDelRango(despuesDelFin));
    }
}
