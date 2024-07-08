package tp.po2.sem.sistemaEstacionamiento;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class RangoHorario 
{

	private LocalDateTime horaInicioRango;
	private LocalDateTime horaFinRango;

	public RangoHorario(LocalDateTime horaInicio, LocalDateTime horaFin) {
		this.horaInicioRango = horaInicio;
		this.horaFinRango = horaFin;
	}
	
	
	
	public LocalDateTime getHoraInicioRango() {
		return horaInicioRango;
	}



	public void setHoraInicioRango(LocalDateTime horaInicioRango) {
		this.horaInicioRango = horaInicioRango;
	}



	public LocalDateTime getHoraFinRango() {
		return horaFinRango;
	}



	public void setHoraFinRango(LocalDateTime horaFinRango) {
		this.horaFinRango = horaFinRango;
	}



	public void validarHoras(LocalDateTime horaInicioEstacionamiento, LocalDateTime horaFinEstacionamiento) throws Exception {
		
		this.assertHoraInicioNoMayorAHoraFin(horaInicioEstacionamiento, horaFinEstacionamiento );  	
		
	}

	private void assertHoraInicioNoMayorAHoraFin(LocalDateTime horaInicio, LocalDateTime horaFin) throws Exception {

		if (horaInicio.isAfter(horaFin)) {
			throw new Exception("La hora de inicio no puede ser mayor que la hora de fin");
		}

	}

	 public boolean estaDentroDelRango(LocalDateTime hora) {
		
		return hora.isAfter(horaInicioRango) && hora.isBefore(horaFinRango);
		
	}

}
