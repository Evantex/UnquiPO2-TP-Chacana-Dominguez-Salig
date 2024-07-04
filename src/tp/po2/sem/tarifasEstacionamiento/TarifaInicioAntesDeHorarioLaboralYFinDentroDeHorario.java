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

		Duration cantidadDeHorasEstacionadas = Duration.between(rangoEstacionamiento.getHoraInicioRango(),
				rangoEstacionamiento.getHoraFinRango());

		Duration cantidadDeHorasQueNoSeCobran = Duration.between(rangoEstacionamiento.getHoraInicioRango(),
				rangoLaboral.getHoraInicioRango());
		
		long horasACobrar = cantidadDeHorasEstacionadas.toHours() - cantidadDeHorasQueNoSeCobran.toHours();

		double montoFinal = horasACobrar * precioPorHora;

		return montoFinal;
	}

}
