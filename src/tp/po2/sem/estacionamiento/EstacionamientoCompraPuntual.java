package tp.po2.sem.estacionamiento;

import java.time.Duration;
import java.time.LocalDateTime;

import tp.po2.sem.puntoDeVenta.*;

public class EstacionamientoCompraPuntual extends Estacionamiento {
    private CompraPuntual compraAsociada;
    private Duration duracion;

    // Constructor que inicializa el estacionamiento con la duración comprada
    public EstacionamientoCompraPuntual(Duration duracion, String patenteVehiculo,
            String nombreZonaEstacionamiento, CompraPuntual compraAsociada) {
        super(LocalDateTime.now().plus(duracion), patenteVehiculo, nombreZonaEstacionamiento);
        this.compraAsociada = compraAsociada;
        this.duracion = duracion;
    }

    // Getter para la compra asociada
    public CompraPuntual getCompraAsociada() {
        return compraAsociada;
    }

    // Setter para la compra asociada
    public void setCompraAsociada(CompraPuntual compraAsociada) {
        this.compraAsociada = compraAsociada;
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
}
