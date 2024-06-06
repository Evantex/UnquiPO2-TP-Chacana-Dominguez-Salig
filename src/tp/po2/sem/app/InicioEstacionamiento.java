package tp.po2.sem.app;

public class InicioEstacionamiento implements AsistenciaUsuario
{

	@Override
	public void notificar(App aplicacion) 
	{
		if( aplicacion.getDeteccionDesplazamiento() )
		{
			aplicacion.notificarUsuario("Posible inicio de estacionamiento");
			// aplicacion.
		}
		
	}

	@Override
	public void actualizarEstado() 
	{
		// TODO Auto-generated method stub

	}
	
	

}
