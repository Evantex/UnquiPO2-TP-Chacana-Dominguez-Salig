package tp.po2.sem.appGPS;

import tp.po2.sem.app.App;

public interface EstadoGPS {

	void activar(App app);

	void desactivar(App app);

	boolean seEncuentraActivada();

}
