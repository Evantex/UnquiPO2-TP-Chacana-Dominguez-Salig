package tp.po2.sem.tarifasEstacionamiento;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tp.po2.sem.sistemaEstacionamiento.RangoHorario;

public class TarifaInicioDentroDeHorarioLaboralYFinFueraDeHorarioTest {

    private TarifaInicioDentroDeHorarioLaboralYFinFueraDeHorario tarifa;
    private RangoHorario rangoLaboral;
    private RangoHorario rangoEstacionamiento;
    private int precioPorHora = 40;

    @BeforeEach
    public void setUp() {
        tarifa = new TarifaInicioDentroDeHorarioLaboralYFinFueraDeHorario();
        rangoLaboral = new RangoHorario(LocalTime.of(7, 0), LocalTime.of(20, 0));
    }

    @Test
    public void testCorrespondeParaInicioDentroDeHorarioLaboralYFinFueraDeHorario() {
        rangoEstacionamiento = new RangoHorario(LocalTime.of(18, 0), LocalTime.of(21, 0));
        assertTrue(tarifa.correspondePara(rangoLaboral, rangoEstacionamiento));
    }

    @Test
    public void testCorrespondeParaInicioYFinDentroDeHorarioLaboral() {
        rangoEstacionamiento = new RangoHorario(LocalTime.of(8, 0), LocalTime.of(10, 0));
        assertFalse(tarifa.correspondePara(rangoLaboral, rangoEstacionamiento));
    }

    @Test
    public void testCorrespondeParaInicioYFinFueraDeHorarioLaboral() {
        rangoEstacionamiento = new RangoHorario(LocalTime.of(21, 0), LocalTime.of(23, 0));
        assertFalse(tarifa.correspondePara(rangoLaboral, rangoEstacionamiento));
    }

    @Test
    public void testCalcularParaInicioDentroDeHorarioLaboralYFinFueraDeHorario() {
        rangoEstacionamiento = new RangoHorario(LocalTime.of(18, 0), LocalTime.of(21, 0));
        double expected = 2 * precioPorHora; // De 18:00 a 20:00 son 2 horas
        double result = tarifa.calcularPara(rangoLaboral, rangoEstacionamiento, precioPorHora);
        assertEquals(expected, result);
    }

}
