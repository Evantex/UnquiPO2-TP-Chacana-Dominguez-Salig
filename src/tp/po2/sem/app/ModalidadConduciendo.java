package tp.po2.sem.app;

public class ModalidadConduciendo implements ModoDesplazamiento
{

	@Override
	public void caminando(App aplicacion, String patente) 
	{
			if(  this.validarSiPuedeEstacionar(aplicacion) )
			{
			aplicacion.getModoNotificacion().notificarInicioEstacionamiento(aplicacion);
			aplicacion.getModoEstacionamiento().iniciarEstacionamiento(aplicacion, patente);
			aplicacion.setModoDesplazamiento( new ModalidadCaminando() );
			}
	}


	private boolean validarSiPuedeEstacionar(App aplicacion)
	{
		return aplicacion.estaDentroDeZonaEstacionamiento() && !aplicacion.tieneEstacionamientoVigente();
	}
	
	
	@Override
	public void conduciendo(App aplicacion, String patente){}

	

}
