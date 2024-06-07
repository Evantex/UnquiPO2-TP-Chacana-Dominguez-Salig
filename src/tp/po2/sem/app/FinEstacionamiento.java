package tp.po2.sem.app;

public class FinEstacionamiento implements AsistenciaUsuario
{

	@Override
	public void notificar(App aplicacion, String dominio) 
	{
		if( aplicacion.getDeteccionDesplazamiento() )
		{
			aplicacion.notificarUsuario("Posible fin de estacionamiento");
			aplicacion.getModoEstacionamiento().finalizarEstacionamiento(aplicacion);
		}
		
	}

	@Override
	public void actualizarEstado(App aplicacion) 
	{
		aplicacion.setAsistenteUsuario( new InicioEstacionamiento() );
	}

}
