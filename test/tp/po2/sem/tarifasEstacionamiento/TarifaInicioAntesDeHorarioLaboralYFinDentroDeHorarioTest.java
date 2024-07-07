package tp.po2.sem.tarifasEstacionamiento;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tp.po2.sem.sistemaEstacionamiento.RangoHorario;

public class TarifaInicioAntesDeHorarioLaboralYFinDentroDeHorarioTest {

    private TarifaInicioAntesDeHorarioLaboralYFinDentroDeHorario tarifa;
    private RangoHorario rangoLaboral;
    private RangoHorario rangoEstacionamiento;
    private int precioPorHora = 40;

    @BeforeEach
    public void setUp() {
        tarifa = new TarifaInicioAntesDeHorarioLaboralYFinDentroDeHorario();
        rangoLaboral = new RangoHorario(LocalTime.of(7, 0), LocalTime.of(20, 0));
    }

    @Test
    public void testCorrespondeParaInicioAntesDeHorarioLaboralYFinDentroDeHorario() {
        rangoEstacionamiento = new RangoHorario(LocalTime.of(6, 0), LocalTime.of(10, 0));
        assertTrue(tarifa.correspondePara(rangoLaboral, rangoEstacionamiento));
    }

    @Test
    public void testCorrespondeParaInicioYFinDentroDeHorarioLaboral() {
        rangoEstacionamiento = new RangoHorario(LocalTime.of(8, 0), LocalTime.of(10, 0));
        assertFalse(tarifa.correspondePara(rangoLaboral, rangoEstacionamiento));
    }

    @Test
    public void testCorrespondeParaInicioYFinFueraDeHorarioLaboral() {
        rangoEstacionamiento = new RangoHorario(LocalTime.of(6, 0), LocalTime.of(21, 0));
        assertFalse(tarifa.correspondePara(rangoLaboral, rangoEstacionamiento));
    }

    @Test
    public void testCalcularParaInicioAntesDeHorarioLaboralYFinDentroDeHorario() {
        rangoEstacionamiento = new RangoHorario(LocalTime.of(6, 0), LocalTime.of(10, 0));
        double expected = 3 * precioPorHora; // De 7:00 a 10:00 son 3 horas
        double result = tarifa.calcularPara(rangoLaboral, rangoEstacionamiento, precioPorHora);
        assertEquals(expected, result);
    }

}
