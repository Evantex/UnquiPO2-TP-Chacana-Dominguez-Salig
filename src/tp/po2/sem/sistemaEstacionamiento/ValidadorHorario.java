package tp.po2.sem.sistemaEstacionamiento;

import java.time.LocalTime;

public class ValidadorHorario {
	
	private LocalTime horaLaboralInicio;
	private LocalTime horaLaboralFin;
	
	public ValidadorHorario(LocalTime horaInicio, LocalTime horaFin ) {
		this.horaLaboralInicio = horaInicio;
		this.horaLaboralFin = horaFin;
	}

	public void validarHora(LocalTime hora) throws Exception {
		
		if (hora.isBefore(horaLaboralInicio) || hora.isAfter(horaLaboralFin)) {
            throw new Exception("No se puede iniciar un estacionamiento fuera del horario laboral");
        }
		
	}
	
	
	
}
