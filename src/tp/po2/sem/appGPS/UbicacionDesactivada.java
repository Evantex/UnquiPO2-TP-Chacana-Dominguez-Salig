package tp.po2.sem.appGPS;

import tp.po2.sem.app.App;

public class UbicacionDesactivada implements EstadoGPS {

	@Override
	public void activar(App app) {
		
		app.setEstadoGps(new UbicacionActivada());
		
	}

	@Override
	public void desactivar(App app) {
		
	}

	@Override
	public boolean seEncuentraActivada() {
		return false;
		
	}
}
