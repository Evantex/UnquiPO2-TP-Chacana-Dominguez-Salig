package tp.po2.sem.app;

public class ModalidadConduciendo implements ModoDesplazamiento {

	@Override
	public void caminando(App aplicacion) throws Exception {

		if (!aplicacion.tieneEstacionamientoVigente() && aplicacion.estaDentroDeZonaEstacionamiento()) {

			aplicacion.notificarUsuario("Alerta inicio estacionamiento");
			aplicacion.getModoEstacionamiento().iniciarEstacionamiento(aplicacion);
			this.update(aplicacion);
		}
	}

	@Override
	public void conduciendo(App aplicacion) {
	}

	@Override
	public void update(App aplicacion) {

		aplicacion.setModoDeDesplazamiento(new ModalidadCaminando());

	}

}
