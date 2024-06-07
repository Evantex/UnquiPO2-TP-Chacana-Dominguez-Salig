
package tp.po2.sem.sistemaEstacionamiento;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

import tp.po2.sem.estacionamiento.Estacionamiento;

public class SistemaEstacionamiento {
	private Set<Estacionamiento> estacionamientos = HashSet<Estacionamiento>();
	
	private Set<Integer> mutableSet = new HashSet<>();



	public void registrarEstacionamiento(Estacionamiento unEstacionamiento) {
		estacionamientos.add(unEstacionamiento);
		
	}

	public Integer getCantidadEstacionamientos() {
	
		return estacionamientos.size();
	}

	public boolean poseeEstacionamientoVigente(String patente) {
		return true;
		
	}

	public void finalizarEstacionamiento( Estacionamiento estacionamiento )
	{
		this.estacionamientos.get
	}
	
	
	
}
