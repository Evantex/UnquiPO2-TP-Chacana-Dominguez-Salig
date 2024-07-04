package tp.po2.sem.Estados;

import java.time.Duration;
import java.time.LocalTime;

import tp.po2.sem.app.CelularDeUsuario;
import tp.po2.sem.estacionamiento.Estacionamiento;
import tp.po2.sem.estacionamiento.EstacionamientoApp;
import tp.po2.sem.sistemaEstacionamiento.SistemaEstacionamiento;

public class NoCobrable implements Estado {

	public void registrarEstacionamientoEn(SistemaEstacionamiento sistemaEstacionamiento,
			EstacionamientoApp unEstacionamiento) {

		unEstacionamiento.setFinEstacionamiento(SistemaEstacionamiento.getHoraLaboralInicio());
		sistemaEstacionamiento.registrarEstacionamiento(unEstacionamiento);

	}

	@Override
	public void cobrarPorEstacionamiento(EstacionamientoApp unEstacionamiento, CelularDeUsuario celular) {
		celular.disminuirSaldo(0);
		unEstacionamiento.setCostoFinal(0);

	}

}
