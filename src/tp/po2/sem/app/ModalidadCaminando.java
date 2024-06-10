package tp.po2.sem.app;

public class ModalidadCaminando implements ModoDesplazamiento
{
	

	@Override
	public void caminando(App aplicacion, String patente) {}

	@Override
	public void conduciendo(App aplicacion, String patente) 
	{
		aplicacion.getModoNotificacion().notificarFinEstacionamiento(aplicacion);
		// getModoEstacionamiento... manual/automático
	}

	private boolean validarZonaEstacionamiento(App aplicacion)
	{
		return aplicacion.tieneEstacionamientoVigente() && this.validarMismoPuntoGeografico(aplicacion);
	}
	
	
	private boolean validarMismoPuntoGeografico(App aplicacion)
	{
		return aplicacion.getUbicacionActual() == aplicacion.getUbicacionEstacionamiento();
	}

	
	@Override
	public void update(App aplicacion)
	{
		aplicacion.setModoDesplazamiento(new ModalidadConduciendo());
	}
}
