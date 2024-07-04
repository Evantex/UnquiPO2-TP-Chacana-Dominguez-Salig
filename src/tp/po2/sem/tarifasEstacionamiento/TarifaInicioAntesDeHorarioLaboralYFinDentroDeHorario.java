package tp.po2.sem.tarifasEstacionamiento;

import java.time.Duration;
import java.time.LocalTime;

import tp.po2.sem.sistemaEstacionamiento.RangoHorario;

public class TarifaInicioAntesDeHorarioLaboralYFinDentroDeHorario implements Tarifa {

	@Override
	public boolean correspondePara(RangoHorario rangoLaboral, RangoHorario rangoEstacionamiento) {

		LocalTime inicioEstacionamiento = rangoEstacionamiento.getHoraInicioRango();
		LocalTime finEstacionamiento = rangoEstacionamiento.getHoraFinRango();

		return (!rangoLaboral.estaDentroDelRango(inicioEstacionamiento))
				&& rangoLaboral.estaDentroDelRango(finEstacionamiento);
	}

	@Override
	public double calcularPara(RangoHorario rangoLaboral, RangoHorario rangoEstacionamiento, int precioPorHora) {

        LocalTime inicio = rangoLaboral.getHoraInicioRango();
        LocalTime fin = rangoEstacionamiento.getHoraFinRango();
        Duration duracion = Duration.between(inicio, fin);
        return duracion.toHours() * precioPorHora;
	}

}
