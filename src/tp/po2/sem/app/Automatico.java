package tp.po2.sem.app;

public class Automatico implements ModoEstacionamiento {

	@Override
	public void iniciarEstacionamiento(App aplicacion) throws Exception {

		aplicacion.iniciarEstacionamiento();

	}

	@Override
	public void finalizarEstacionamiento(App aplicacion) throws Exception {

		aplicacion.finalizarEstacionamiento();
	}

}
