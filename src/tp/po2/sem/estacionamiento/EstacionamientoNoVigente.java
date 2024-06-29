package tp.po2.sem.estacionamiento;

public class EstacionamientoNoVigente implements EstadoEstacionamiento {

	@Override
	public boolean estaVigente() {
		
		return false;
	}

}
