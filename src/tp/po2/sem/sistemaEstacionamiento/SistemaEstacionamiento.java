
package tp.po2.sem.sistemaEstacionamiento;

import java.util.ArrayList;
import java.util.List;

import tp.po2.sem.estacionamiento.Estacionamiento;

public class SistemaEstacionamiento {
	private List<Estacionamiento> estacionamientos;

	public SistemaEstacionamiento(List<Estacionamiento> estacionamientos) {
		super();
		this.estacionamientos = estacionamientos;
	}

	public SistemaEstacionamiento() {
		estacionamientos = new ArrayList<Estacionamiento>();

	}

	public void registrarEstacionamiento(Estacionamiento unEstacionamiento) {
		estacionamientos.add(unEstacionamiento);
		
	}

	public Integer getCantidadEstacionamientos() {
	
		return estacionamientos.size();
	}

	public boolean poseeEstacionamientoVigente(String patente) {
		return true;
		
	}

	
	
	
}
