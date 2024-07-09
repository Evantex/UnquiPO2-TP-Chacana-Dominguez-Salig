package tp.po2.sem.estacionamiento;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import tp.po2.sem.puntoDeVenta.*;

public class EstacionamientoCompraPuntual extends Estacionamiento 
{
    private CompraPuntual compraAsociada;
 

    // Constructor que inicializa el estacionamiento con la duración comprada
    public EstacionamientoCompraPuntual(String patenteVehiculo, CompraPuntual compraAsociada) 
    {
    	super();
    	this.compraAsociada = compraAsociada;
        this.inicioEstacionamiento = compraAsociada.getHoraInicio();
        this.finEstacionamiento = compraAsociada.getHoraFin();
        this.patenteVehiculo = patenteVehiculo;
        this.duracionEnHoras = compraAsociada.getHorasCompradas();
    }
    
    @Override
    public String getIdentificadorEstacionamiento() 
    {
        return this.patenteVehiculo;
    }
    
    public CompraPuntual getCompraAsociada() 
    {
        return compraAsociada;
    }
    
	public boolean esEstacionamientoCompraPuntual()
	{
		return true;
	}

	public boolean esEstacionamientoApp() {
		return false;
	}


    
    
    
    
    
}
