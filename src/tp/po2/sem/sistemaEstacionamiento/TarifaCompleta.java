package tp.po2.sem.sistemaEstacionamiento;

import java.time.Duration;

public class TarifaCompleta implements Tarifa {
	
	@Override
	public double calcular(RangoHorario rangoLaboral, RangoHorario rangoEstacionamiento, int precioPorHora) {
		long cantidadDeHoras = Duration
				.between(rangoEstacionamiento.getHoraInicioRango(), rangoEstacionamiento.getHoraFinRango()).toHours();
		return cantidadDeHoras * precioPorHora;
	}
}
