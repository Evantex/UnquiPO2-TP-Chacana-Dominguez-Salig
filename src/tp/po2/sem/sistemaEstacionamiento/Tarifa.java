package tp.po2.sem.sistemaEstacionamiento;

public interface Tarifa {
    double calcular(RangoHorario rangoLaboral, RangoHorario rangoEstacionamiento, int precioPorHora);
}
