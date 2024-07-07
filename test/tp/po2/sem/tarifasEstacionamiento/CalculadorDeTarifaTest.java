package tp.po2.sem.tarifasEstacionamiento;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tp.po2.sem.sistemaEstacionamiento.RangoHorario;

public class CalculadorDeTarifaTest {

    private CalculadorDeTarifa calculadorDeTarifa;
    private Tarifa tarifaMock;
    private RangoHorario rangoLaboral;
    private RangoHorario rangoEstacionamiento;
    private int precioPorHora = 40;

    @BeforeEach
    public void setUp() {
    	
        tarifaMock = mock(Tarifa.class);
        
        List<Tarifa> tiposDeTarifa = new ArrayList<>();
        tiposDeTarifa.add(tarifaMock);
        
        calculadorDeTarifa = new CalculadorDeTarifa(tiposDeTarifa);

        rangoLaboral = new RangoHorario(LocalTime.of(7, 0), LocalTime.of(20, 0));
        rangoEstacionamiento = new RangoHorario(LocalTime.of(6, 0), LocalTime.of(21, 0));
    }

    @Test
    public void testDeterminarCobroParaConTarifaValida() throws Exception {
    	
        when(tarifaMock.correspondePara(rangoLaboral, rangoEstacionamiento)).thenReturn(true);
        when(tarifaMock.calcularPara(rangoLaboral, rangoEstacionamiento, precioPorHora)).thenReturn(100.0);

        double cobro = calculadorDeTarifa.determinarCobroPara(rangoLaboral, rangoEstacionamiento, precioPorHora);

        assertEquals(100.0, cobro);
    }

    @Test
    public void testDeterminarCobroParaSinTarifaValida() {

    	when(tarifaMock.correspondePara(rangoLaboral, rangoEstacionamiento)).thenReturn(false);

        Exception exception = assertThrows(Exception.class, () -> {
            calculadorDeTarifa.determinarCobroPara(rangoLaboral, rangoEstacionamiento, precioPorHora);
        });

        assertEquals("No se puede establecer una tarifa para el estacionamiento", exception.getMessage());
    }
}
