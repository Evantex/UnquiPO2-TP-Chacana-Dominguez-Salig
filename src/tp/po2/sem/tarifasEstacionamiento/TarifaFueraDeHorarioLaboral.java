package tp.po2.sem.tarifasEstacionamiento;

import java.time.LocalTime;

import tp.po2.sem.sistemaEstacionamiento.RangoHorario;

public class TarifaFueraDeHorarioLaboral implements Tarifa {

	@Override
	public double calcularPara(RangoHorario rangoLaboral, RangoHorario rangoEstacionamiento, int precioPorHora) {
		return 0;
	}

	@Override
	public boolean correspondePara(RangoHorario rangoLaboral, RangoHorario rangoEstacionamiento) {
		
		LocalTime horaInicioEstacionamiento = rangoEstacionamiento.getHoraInicioRango();
		LocalTime horaFinEstacionamiento = rangoEstacionamiento.getHoraFinRango();
		
		return !rangoLaboral.estaDentroDelRango(horaInicioEstacionamiento)
				&& !rangoLaboral.estaDentroDelRango(horaFinEstacionamiento);
	}

}
