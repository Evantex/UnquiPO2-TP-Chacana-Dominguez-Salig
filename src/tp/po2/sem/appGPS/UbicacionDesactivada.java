package tp.po2.sem.appGPS;

import tp.po2.sem.app.App;

public class UbicacionDesactivada implements EstadoGPS {

	@Override
	public void activarUbicacion(App app) {
		
		app.setUbicacionGps(new UbicacionActivada());
		
	}

	@Override
	public void desactivarUbicacion(App app) {
		
	}

	@Override
	public boolean seEncuentraActivada() {
		return false;
		
	}
}
