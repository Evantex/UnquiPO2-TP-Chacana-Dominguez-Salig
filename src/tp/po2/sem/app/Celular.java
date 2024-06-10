package tp.po2.sem.app;

import java.awt.Point;

public class Celular
{
	private String nroCelular;
	private Point ubicacion;
	
	
	public void recibirMensaje( String msg ) {};
	
	public boolean estaDentroDeZonaEstacionamiento()
	{
		return true;
	}
	

	public String getNroCelular()
	{
		return this.nroCelular;
	}

	public Point getUbicacion()
	{
		return this.ubicacion;
	}
	
	
}
