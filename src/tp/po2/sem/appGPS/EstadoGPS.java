package tp.po2.sem.appGPS;

import tp.po2.sem.app.App;

public interface EstadoGPS {

	void activarUbicacion(App app);

	void desactivarUbicacion(App app);

	boolean seEncuentraActivada();

}
