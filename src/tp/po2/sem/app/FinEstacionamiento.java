package tp.po2.sem.app;

public class FinEstacionamiento implements AsistenciaUsuario
{

	@Override
	public void notificar(App aplicacion, String dominio) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actualizarEstado(App aplicacion) 
	{
		aplicacion.setAsistenteUsuario( new InicioEstacionamiento() );
	}

}
