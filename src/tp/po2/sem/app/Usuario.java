package tp.po2.sem.app;

public class Usuario 
{
	private int nroCelular;
	private App aplicacionEstacionamiento;
	private boolean estacionamientoVigente = false;
	
	
	public boolean estaEnZonaDeEstacionamiento()
	/*
	 	Puede tener true o false, dependiendo de la ubicación del celular.
	 	Seteamos valor true para poder modelar el resto de las funcionalidades.
	 */
	{
		return true;
	}
	
	
	public void recibirNotificacion( String mensaje ){}


	public boolean poseeEstacionamientoVigente()
	{
		return estacionamientoVigente;
	}


	public void setEstacionamientoVigente(boolean estacionamientoVigente)
	{
		this.estacionamientoVigente = estacionamientoVigente;
	}
	

}
