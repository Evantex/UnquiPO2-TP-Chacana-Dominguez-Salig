package tp.po2.sem.sistemaEstacionamiento;

import java.time.Duration;
import java.time.LocalTime;

public class TarifaParcial implements Tarifa {

    @Override
    public double calcular(RangoHorario rangoLaboral, RangoHorario rangoEstacionamiento, int precioPorHora) {
        LocalTime inicioEstacionamiento = rangoEstacionamiento.getHoraInicioRango();
        LocalTime finEstacionamiento = rangoEstacionamiento.getHoraFinRango();
        long horasCobrar = 0;

        if (inicioEstacionamiento.isBefore(rangoLaboral.getHoraInicioRango())) {
            inicioEstacionamiento = rangoLaboral.getHoraInicioRango();
        }

        if (finEstacionamiento.isAfter(rangoLaboral.getHoraFinRango())) {
            finEstacionamiento = rangoLaboral.getHoraFinRango();
        }

        horasCobrar = Duration.between(inicioEstacionamiento, finEstacionamiento).toHours();

        return horasCobrar * precioPorHora;
    }
}
