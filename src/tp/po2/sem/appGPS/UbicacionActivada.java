package tp.po2.sem.appGPS;

import tp.po2.sem.app.App;

public class UbicacionActivada implements EstadoGPS 
{
	@Override
	public void activar(App app) {}

	@Override
	public void desactivar(App app) 
	{	
		app.setEstadoGps(new UbicacionDesactivada());	
	}

	@Override
	public boolean seEncuentraActivada() 
	{
		return true;
	}
}
