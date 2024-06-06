package tp.po2.sem.app;

public class InicioEstacionamiento implements AsistenciaUsuario
{

	@Override
	public void notificar(App aplicacion, String dominio) 
	{
		if( aplicacion.getDeteccionDesplazamiento() )
		{
			aplicacion.notificarUsuario("Posible inicio de estacionamiento");
			aplicacion.getModoEstacionamiento().iniciarEstacionamiento(aplicacion, dominio);
		}
		
	}

	@Override
	public void actualizarEstado(App aplicacion) 
	{
		aplicacion.setAsistenteUsuario( new FinEstacionamiento() );
	}
	
	

}
