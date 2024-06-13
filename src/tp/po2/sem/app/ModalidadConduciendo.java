package tp.po2.sem.app;

public class ModalidadConduciendo implements ModoDesplazamiento
{
	
	
	@Override
	public void caminando(App aplicacion, String patente) throws Exception 
	{
		try
		{
			aplicacion.verificarSiPoseeEstacionamientoVigente();
			aplicacion.verificarZonaEstacionamiento();
			aplicacion.getModoNotificacion().notificarInicioEstacionamiento(aplicacion);
			aplicacion.getModoEstacionamiento().iniciarEstacionamiento(aplicacion, patente);
			this.update(aplicacion);
		}
		catch (Exception e)
		{
			// En este caso, si una de las dos validaciones lanza error, no se ejecuta ninguna acción.
		}
	}

	@Override
	public void update(App aplicacion)
	/*
			- Vuelvo a validar si tiene estacionamiento vigente debido a que en el proceso puede que no se inicie un estacionamiento si
				el usuario no tiene saldo disponible...
	 */
	{
		if( aplicacion.tieneEstacionamientoVigente() )
		{
			aplicacion.setModoDesplazamiento( new ModalidadCaminando() );
			aplicacion.setUbicacionEstacionamiento(aplicacion.getUbicacionActual());
		}
	}
	
	
	@Override
	public void conduciendo(App aplicacion, String patente){}

}
