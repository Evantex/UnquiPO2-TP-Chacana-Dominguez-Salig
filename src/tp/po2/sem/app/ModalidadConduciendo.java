package tp.po2.sem.app;

public class ModalidadConduciendo implements ModoDesplazamiento 
{

	@Override
	public void caminando(App aplicacion) throws Exception 
	{
		aplicacion.getEstadoEstacionamiento().posibleInicioEstacionamiento(this, aplicacion);
        this.update(aplicacion);
	}
	
	public void inicioEstacionamiento( App aplicacion ) throws Exception
	{
		aplicacion.notificarUsuario("Posible inicio de estacionamiento");
		aplicacion.getModoEstacionamiento().iniciarEstacionamiento(aplicacion);
	}

	@Override
	public void update(App aplicacion) 
	{
		aplicacion.setModoDeDesplazamiento(new ModalidadCaminando());
	}
	
	@Override
	public void conduciendo(App aplicacion) {}

	@Override
	public void finEstacionamiento(App aplicacion) {}

}
