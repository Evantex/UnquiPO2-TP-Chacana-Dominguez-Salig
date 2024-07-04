package tp.po2.sem.Estados;

import java.time.Duration;

import tp.po2.sem.app.CelularDeUsuario;
import tp.po2.sem.estacionamiento.EstacionamientoApp;
import tp.po2.sem.sistemaEstacionamiento.SistemaEstacionamiento;

public class Cobrable implements Estado {

	@Override
	public void registrarEstacionamientoEn(SistemaEstacionamiento sistemaEstacionamiento,
			EstacionamientoApp unEstacionamiento) {
		sistemaEstacionamiento.registrarEstacionamiento(unEstacionamiento);

	}

	@Override
	public void cobrarPorEstacionamiento(EstacionamientoApp estacionamiento, CelularDeUsuario celular) {

		celular.disminuirSaldo(estacionamiento.getCostoEstacionamiento());
		estacionamiento.setCostoFinal(estacionamiento.getCostoEstacionamiento());

	}

}
