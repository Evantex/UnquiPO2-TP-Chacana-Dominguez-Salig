package tp.po2.sem.sistemaEstacionamiento;

import java.time.Duration;
import java.time.LocalTime;

public class RangoHorario {

	private LocalTime horaInicioRango;
	private LocalTime horaFinRango;

	public RangoHorario(LocalTime horaInicio, LocalTime horaFin) {
		this.horaInicioRango = horaInicio;
		this.horaFinRango = horaFin;
	}
	
	
	
	public LocalTime getHoraInicioRango() {
		return horaInicioRango;
	}



	public void setHoraInicioRango(LocalTime horaInicioRango) {
		this.horaInicioRango = horaInicioRango;
	}



	public LocalTime getHoraFinRango() {
		return horaFinRango;
	}



	public void setHoraFinRango(LocalTime horaFinRango) {
		this.horaFinRango = horaFinRango;
	}



	public void validarHoras(LocalTime horaInicioEstacionamiento, LocalTime horaFinEstacionamiento) throws Exception {
		
		this.assertHoraInicioNoMayorAHoraFin(horaInicioEstacionamiento, horaFinEstacionamiento ); 
		this.assertHoraFinNoMenorAHoraInicio(horaInicioEstacionamiento, horaFinEstacionamiento); 	
		
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
	
	public boolean seEncuentraFueraDelRangoHorario(LocalTime horaInicio, LocalTime horaFin) {

		return (horaInicio.isBefore(horaInicioRango) || horaFin.isBefore(horaInicioRango)
				|| horaInicio.isAfter(horaFinRango) || horaFin.isAfter(horaFinRango));
	}


	 public boolean estaDentroDelRango(LocalTime hora) {
		
		return hora.isAfter(horaInicioRango) && hora.isBefore(horaFinRango);
		
	}

}
