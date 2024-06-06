package tp.po2.sem.estacionamiento;

import java.time.LocalDateTime;
import tp.po2.sem.puntoDeVenta.*;

public abstract class Estacionamiento 
{
	
	protected LocalDateTime inicioEstacionamiento;
	protected LocalDateTime finEstacionamiento;
	protected String patenteVehículo;
	protected boolean estaVigente = true;
	// protected Compra compraAsociada;
	
	
	public abstract String identificadorEstacionamiento();
	
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
