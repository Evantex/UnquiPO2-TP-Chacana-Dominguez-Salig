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
		aplicacion.setModoDesplazamiento( new ModalidadConduciendo() );
	}


	private boolean validarZonaEstacionamiento(App aplicacion)
	{
		return aplicacion.estaDentroDeZonaEstacionamiento() && !aplicacion.tieneEstacionamientoVigente();
	}
	

}
