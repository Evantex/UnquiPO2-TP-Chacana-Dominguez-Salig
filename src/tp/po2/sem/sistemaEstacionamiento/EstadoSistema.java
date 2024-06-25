package tp.po2.sem.sistemaEstacionamiento;

import tp.po2.sem.estacionamiento.Estacionamiento;

public interface EstadoSistema {
	
	void registrarEstacionamiento(SistemaEstacionamiento sem, Estacionamiento estacionamiento) throws Exception;

	boolean esEstadoAbierto();
	
}
