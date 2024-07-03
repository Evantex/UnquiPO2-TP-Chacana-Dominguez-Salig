package tp.po2.sem.sistemaEstacionamiento;

import java.time.Duration;
import java.time.LocalTime;

public class RangoHorario {

	private LocalTime horaLaboralInicio;
	private LocalTime horaLaboralFin;

	public RangoHorario(LocalTime horaInicio, LocalTime horaFin) {
		this.horaLaboralInicio = horaInicio;
		this.horaLaboralFin = horaFin;
	}

	public void validarHoras(Duration cantidadDeHoras) throws Exception {
		
		LocalTime horaInicioEstacionamiento = LocalTime.now(); // esto deberia ser con la clase RELOJ
		LocalTime horaFinEstacionamiento = horaInicioEstacionamiento.plus(cantidadDeHoras);
		
		this.assertHoraInicioNoMayorAHoraFin(horaInicioEstacionamiento,horaFinEstacionamiento ); // sigue por ahora
		this.assertHoraFinNoMenorAHoraInicio(horaInicioEstacionamiento, horaInicioEstacionamiento); // sigue por ahora		
		/*this.assertHorasDentroDeRangoHorario(horaInicio, horaFin); */

	}

	private void assertHoraInicioNoMayorAHoraFin(LocalTime horaInicio, LocalTime horaFin) throws Exception {

		if (horaInicio.isAfter(horaFin)) {
			throw new Exception("La hora de inicio no puede ser mayor que la hora de fin");
		}

	}

	private void assertHoraFinNoMenorAHoraInicio(LocalTime horaInicio, LocalTime horaFin) throws Exception {

		if (horaFin.isBefore(horaInicio)) {

			throw new Exception("La hora de fin no puede ser menor que la hora de inicio");

		}

	}

	/*private void assertHorasDentroDeRangoHorario(LocalTime horaInicio, LocalTime horaFin) throws Exception {

		if (seEncuentraFueraDelRangoHorario(horaInicio, horaFin)) {

			throw new Exception("No se puede iniciar un estacionamiento fuera del horario laboral");
		}

	}

	private boolean seEncuentraFueraDelRangoHorario(LocalTime horaInicio, LocalTime horaFin) {

		return (horaInicio.isBefore(horaLaboralInicio) || horaFin.isBefore(horaLaboralInicio)
				|| horaInicio.isAfter(horaLaboralFin) || horaFin.isAfter(horaLaboralFin));
	}*/

}
