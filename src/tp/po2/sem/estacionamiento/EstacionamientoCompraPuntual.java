package tp.po2.sem.estacionamiento;
import tp.po2.sem.puntoDeVenta.*;

import java.time.LocalDateTime;

public class EstacionamientoCompraPuntual extends Estacionamiento 
{
	private CompraPuntual compraAsociada;

	public EstacionamientoCompraPuntual(LocalDateTime inicioEstacionamiento, LocalDateTime finEstacionamiento, 
			String patenteVehículo, CompraPuntual compraAsociada) 
	{
		this.inicioEstacionamiento = inicioEstacionamiento;
		this.finEstacionamiento = finEstacionamiento;
		this.patenteVehículo = patenteVehículo;
		this.compraAsociada = compraAsociada;
	}
	


}
