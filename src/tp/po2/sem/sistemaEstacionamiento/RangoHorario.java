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
		this.assertHorasDentroDeRangoHorario(horaInicio, horaFin); 

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

	private void assertHorasDentroDeRangoHorario(LocalTime horaInicio, LocalTime horaFin) throws Exception {

		if (seEncuentraFueraDelRangoHorario(horaInicio, horaFin)) {

			throw new Exception("No se puede iniciar un estacionamiento fuera del horario laboral");
		}

	}

	public boolean seEncuentraFueraDelRangoHorario(LocalTime horaInicio, LocalTime horaFin) {

		return (horaInicio.isBefore(horaLaboralInicio) || horaFin.isBefore(horaLaboralInicio)
				|| horaInicio.isAfter(horaLaboralFin) || horaFin.isAfter(horaLaboralFin));
	}

	public boolean seEncuentraDentroDelRangoHorario(LocalTime horaInicio, LocalTime horaFin) {
		
        boolean inicioDentroDelHorario = laHoraInicioEstaDentroDelHorarioLaboral(horaInicio);

        boolean finDentroDelHorario = laHoraFinEstaDentroDelHorarioLaboral(horaFin);

        boolean cubreTodoElHorarioLaboral = laHoraCubreTodoElRangoLaboral(horaInicio, horaFin);

        return inicioDentroDelHorario || finDentroDelHorario || cubreTodoElHorarioLaboral;
	}

	private boolean laHoraCubreTodoElRangoLaboral(LocalTime horaInicio, LocalTime horaFin) {
		boolean cubreTodoElHorarioLaboral = horaInicio.isBefore(horaLaboralInicio) && horaFin.isAfter(horaLaboralFin);
		return cubreTodoElHorarioLaboral;
	}

	private boolean laHoraFinEstaDentroDelHorarioLaboral(LocalTime horaFin) {
		boolean finDentroDelHorario = horaFin.isAfter(horaLaboralInicio) && horaFin.isBefore(horaLaboralFin);
		return finDentroDelHorario;
	}

	private boolean laHoraInicioEstaDentroDelHorarioLaboral(LocalTime horaInicio) {
		boolean inicioDentroDelHorario = horaInicio.isAfter(horaLaboralInicio) && horaInicio.isBefore(horaLaboralFin);
		return inicioDentroDelHorario;
	}

}
