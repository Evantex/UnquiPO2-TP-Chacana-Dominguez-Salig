package tp.po2.sem.app;

public class ModalidadCaminando implements ModoDesplazamiento {

	@Override
	public void caminando(App aplicacion) {}

	@Override
	public void conduciendo(App aplicacion) throws Exception 
	{
		if (aplicacion.tieneEstacionamientoVigente() && aplicacion.validarMismoPuntoGeografico())
		{
			aplicacion.notificarUsuario("Posible fin de estacionamiento.");
			aplicacion.getModoEstacionamiento().finalizarEstacionamiento(aplicacion);
			this.update(aplicacion);
		}
	}

	@Override
	public void update(App aplicacion)
	{
		aplicacion.setModoDeDesplazamiento(new ModalidadConduciendo());
	}

}
