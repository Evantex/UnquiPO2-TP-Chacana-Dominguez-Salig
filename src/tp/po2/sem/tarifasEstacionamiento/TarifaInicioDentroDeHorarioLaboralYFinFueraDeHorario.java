package tp.po2.sem.tarifasEstacionamiento;

import java.time.Duration;
import java.time.LocalTime;

import tp.po2.sem.sistemaEstacionamiento.RangoHorario;

public class TarifaInicioDentroDeHorarioLaboralYFinFueraDeHorario implements Tarifa {

	@Override
	public double calcularPara(RangoHorario rangoLaboral, RangoHorario rangoEstacionamiento, int precioPorHora) {
		
		Duration cantidadDeHorasEstacionadas = Duration.between(rangoEstacionamiento.getHoraInicioRango(),
				rangoEstacionamiento.getHoraFinRango());

		Duration cantidadDeHorasQueNoSeCobran = Duration.between(rangoEstacionamiento.getHoraFinRango(),
				rangoLaboral.getHoraFinRango());

		long horasACobrar = cantidadDeHorasEstacionadas.toHours() - cantidadDeHorasQueNoSeCobran.toHours();

		double montoFinal = horasACobrar * precioPorHora;

		return montoFinal;
		
	}

	@Override
	public boolean correspondePara(RangoHorario rangoLaboral, RangoHorario rangoEstacionamiento) {
		
		LocalTime horaInicioEstacionamiento = rangoEstacionamiento.getHoraInicioRango();
		LocalTime horaFinEstacionamiento = rangoEstacionamiento.getHoraFinRango();
		
		return rangoLaboral.estaDentroDelRango(horaInicioEstacionamiento)
				&& (!rangoLaboral.estaDentroDelRango(horaFinEstacionamiento));
	}

}
