package tp.po2.sem.app;

public class Usuario 
{
	private String nroCelular;
	private App aplicacionEstacionamiento;
	private String dominioVehiculo;
	
	
	public boolean estaEnZonaDeEstacionamiento()
	/*
	 	Puede tener true o false, dependiendo de la ubicación del celular.
	 	Seteamos valor true para poder modelar el resto de las funcionalidades.
	 */
	{
		return true;
	}
	
	
	public void recibirNotificacion( String mensaje ){}


	public String getNroCelular()
	{
		return this.nroCelular;
	}
	
	public String getPatente()
	{
		return this.dominioVehiculo;
	}
	

}
