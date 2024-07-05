package tp.po2.sem.appGPS;

import tp.po2.sem.app.App;

public class UbicacionActivada implements EstadoGPS {

	@Override
	public void activarUbicacion(App app) {}

	@Override
	public void desactivarUbicacion(App app) {
		
		app.setUbicacionGps(new UbicacionDesactivada());
		
	}

	@Override
	public boolean seEncuentraActivada() {
		return true;
		
	}
	
	
}
