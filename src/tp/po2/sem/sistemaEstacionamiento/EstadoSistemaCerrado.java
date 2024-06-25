package tp.po2.sem.sistemaEstacionamiento;

import tp.po2.sem.estacionamiento.Estacionamiento;

public class EstadoSistemaCerrado implements EstadoSistema {

	@Override
	public void registrarEstacionamiento(SistemaEstacionamiento sem, Estacionamiento estacionamiento) throws Exception {
		
		throw new Exception("El sistema de estacionamiento está cerrado.");

	}

	@Override
	public boolean esEstadoAbierto() {
		return false;
	}

}
