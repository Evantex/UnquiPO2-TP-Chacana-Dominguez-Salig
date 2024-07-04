package tp.po2.sem.Estados;

import java.time.Duration;
import java.time.LocalTime;

import tp.po2.sem.app.CelularDeUsuario;
import tp.po2.sem.estacionamiento.Estacionamiento;
import tp.po2.sem.estacionamiento.EstacionamientoApp;
import tp.po2.sem.sistemaEstacionamiento.SistemaEstacionamiento;

public interface Estado {
	

	
	public void registrarEstacionamientoEn(SistemaEstacionamiento sistemaEstacionamiento,
			EstacionamientoApp unEstacionamiento);


	public void cobrarPorEstacionamiento(EstacionamientoApp estacionamiento, CelularDeUsuario celular);
}
