package tp.po2.sem.estacionamiento;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

import tp.po2.sem.puntoDeVenta.*;

public class EstacionamientoCompraPuntual extends Estacionamiento 
{
    private CompraPuntual compraAsociada;

    // Constructor que inicializa el estacionamiento con la duración comprada
    public EstacionamientoCompraPuntual(Duration duracion, String patenteVehiculo, CompraPuntual compraAsociada) 
    {
        this.inicioEstacionamiento = LocalDateTime.now();
        this.finEstacionamiento = LocalDateTime.now().plus(duracion);
        this.patenteVehiculo = patenteVehiculo;
        this.compraAsociada = compraAsociada;
        this.duracionEnHoras = duracion;
    }

    
    // Getter para la compra asociada
    public CompraPuntual getCompraAsociada() 
    {
        return compraAsociada;
    }

    // Implementación del método abstracto para verificar si el estacionamiento está vigente
    @Override
    public boolean estaVigente() {
        return LocalDateTime.now().isBefore(this.finEstacionamiento);
    }

    // Implementación del método abstracto para obtener el identificador del estacionamiento
    @Override
    public String getIdentificadorEstacionamiento() {
        return "Puntual-" + this.patenteVehiculo + "-" + this.inicioEstacionamiento.toString();
    }
    
    
	public boolean esEstacionamientoCompraPuntual() {
		return true;
	}

	public boolean esEstacionamientoApp() {
		return false;
	}
    
    
    
    
    
}
