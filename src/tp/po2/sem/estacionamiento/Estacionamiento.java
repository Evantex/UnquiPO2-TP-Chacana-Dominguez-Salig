package tp.po2.sem.estacionamiento;

import java.time.LocalDateTime;
import tp.po2.sem.puntoDeVenta.*;

public class Estacionamiento 
{
	
	protected LocalDateTime inicioEstacionamiento;
	protected LocalDateTime finEstacionamiento;
	protected String patenteVehículo;
	protected boolean estaVigente = true;
	
	
	public boolean estaVigente()
	{
		return estaVigente;
	}
	
	public void finalizarEstacionamiento()
	{
		this.estaVigente = false;
		this.finEstacionamiento = LocalDateTime.now();
	}
	
//	private String datox;

//	public Estacionamiento(String datox) {
//		super();
//		this.datox = datox;
//	}



}
