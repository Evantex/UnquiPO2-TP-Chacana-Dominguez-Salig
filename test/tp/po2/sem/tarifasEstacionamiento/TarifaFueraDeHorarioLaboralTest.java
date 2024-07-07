package tp.po2.sem.tarifasEstacionamiento;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tp.po2.sem.sistemaEstacionamiento.RangoHorario;

public class TarifaFueraDeHorarioLaboralTest {

    private TarifaFueraDeHorarioLaboral tarifa;
    private RangoHorario rangoLaboral;
    private RangoHorario rangoEstacionamiento;
    private int precioPorHora = 40;

    @BeforeEach
    public void setUp() {
        tarifa = new TarifaFueraDeHorarioLaboral();
        rangoLaboral = new RangoHorario(LocalTime.of(7, 0), LocalTime.of(20, 0));
    }

    @Test
    public void testCalcularParaFueraDelRangoLaboral() {
        rangoEstacionamiento = new RangoHorario(LocalTime.of(21, 0), LocalTime.of(23, 0));
        double expected = 0; // Siempre devuelve 0
        double result = tarifa.calcularPara(rangoLaboral, rangoEstacionamiento, precioPorHora);
        assertEquals(expected, result);
    }

    @Test
    public void testCorrespondeParaFueraDelRangoLaboral() {
        rangoEstacionamiento = new RangoHorario(LocalTime.of(21, 0), LocalTime.of(23, 0));
        assertTrue(tarifa.correspondePara(rangoLaboral, rangoEstacionamiento));
    }

    @Test
    public void testCorrespondeParaDentroDelRangoLaboral() {
        rangoEstacionamiento = new RangoHorario(LocalTime.of(8, 0), LocalTime.of(10, 0));
        assertFalse(tarifa.correspondePara(rangoLaboral, rangoEstacionamiento));
    }

    @Test
    public void testCorrespondeParaParcialmenteDentroDelRangoLaboral() {
        rangoEstacionamiento = new RangoHorario(LocalTime.of(19, 0), LocalTime.of(21, 0));
        assertFalse(tarifa.correspondePara(rangoLaboral, rangoEstacionamiento));
    }
}
