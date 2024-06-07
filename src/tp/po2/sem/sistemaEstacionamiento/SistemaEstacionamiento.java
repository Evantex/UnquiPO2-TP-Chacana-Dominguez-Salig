package tp.po2.sem.sistemaEstacionamiento;

import java.util.Set;
import java.time.Duration;
import java.util.HashSet;

import tp.po2.sem.estacionamiento.Estacionamiento;
import tp.po2.sem.estacionamiento.EstacionamientoCompraPuntual;
import tp.po2.sem.puntoDeVenta.CompraPuntual;

public class SistemaEstacionamiento {
    private Set<Estacionamiento> estacionamientos;

    public SistemaEstacionamiento(Set<Estacionamiento> estacionamientos) {
        this.estacionamientos = estacionamientos;
    }

    public void registrarEstacionamiento(Estacionamiento unEstacionamiento) {
        estacionamientos.add(unEstacionamiento);
    }

    public void registrarEstacionamientoCompraPuntual(String patente, Duration horasCompradas, CompraPuntual compraAsociada) {
        EstacionamientoCompraPuntual estacionamiento = new EstacionamientoCompraPuntual(horasCompradas, patente, compraAsociada);
        estacionamientos.add(estacionamiento);
    }

    public Integer getCantidadEstacionamientos() {
        return estacionamientos.size();
    }

    public boolean poseeEstacionamientoVigente(String patente) {
        return true;
    }

    public void registrarInfraccion(String patente) {
    }
}
