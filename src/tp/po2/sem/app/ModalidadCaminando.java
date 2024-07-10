package tp.po2.sem.app;

public class ModalidadCaminando implements ModoDesplazamiento {

	@Override
	public void caminando(App aplicacion) {}

	@Override
	public void conduciendo(App aplicacion) throws Exception 
	{
		aplicacion.getEstadoEstacionamiento().posibleFinEstacionamiento(this, aplicacion);
		this.update(aplicacion);
	}

	@Override
	public void update(App aplicacion)
	{
		aplicacion.setModoDeDesplazamiento(new ModalidadConduciendo());
	}

	@Override
	public void inicioEstacionamiento(App aplicacion) throws Exception {}

	@Override
	public void finEstacionamiento(App aplicacion) throws Exception 
	{
		aplicacion.notificarUsuario("Posible fin de estacionamiento.");
		aplicacion.getModoEstacionamiento().finalizarEstacionamiento(aplicacion);
	}

}
