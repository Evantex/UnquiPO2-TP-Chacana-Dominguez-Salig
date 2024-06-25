package tp.po2.sem.sistemaEstacionamiento;

import tp.po2.sem.estacionamiento.Estacionamiento;

public class EstadoSistemaAbierto implements EstadoSistema {

	@Override
	public void registrarEstacionamiento(SistemaEstacionamiento sem, Estacionamiento estacionamiento) throws Exception {
		
		sem.addEstacionamiento(estacionamiento);
		sem.notificarSistemaAlertasInicioEstacionamiento(estacionamiento);
	}

	@Override
	public boolean esEstadoAbierto() {
		
		return true;
	}

}
