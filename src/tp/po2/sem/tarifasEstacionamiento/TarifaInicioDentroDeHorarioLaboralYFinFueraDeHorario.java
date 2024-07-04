package tp.po2.sem.tarifasEstacionamiento;

import java.time.Duration;
import java.time.LocalTime;

import tp.po2.sem.sistemaEstacionamiento.RangoHorario;

public class TarifaInicioDentroDeHorarioLaboralYFinFueraDeHorario implements Tarifa {

	@Override
	public double calcularPara(RangoHorario rangoLaboral, RangoHorario rangoEstacionamiento, int precioPorHora) {
		
		LocalTime inicio = rangoEstacionamiento.getHoraInicioRango();
		LocalTime fin = rangoLaboral.getHoraFinRango();
		Duration duracion = Duration.between(inicio, fin);
		return duracion.toHours() * precioPorHora;
		
	}

	@Override
	public boolean correspondePara(RangoHorario rangoLaboral, RangoHorario rangoEstacionamiento) {
		
		LocalTime horaInicioEstacionamiento = rangoEstacionamiento.getHoraInicioRango();
		LocalTime horaFinEstacionamiento = rangoEstacionamiento.getHoraFinRango();
		
		return rangoLaboral.estaDentroDelRango(horaInicioEstacionamiento)
				&& (!rangoLaboral.estaDentroDelRango(horaFinEstacionamiento));
	}

}
