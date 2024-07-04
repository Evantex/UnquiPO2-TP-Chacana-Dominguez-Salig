package tp.po2.sem.tarifasEstacionamiento;

import tp.po2.sem.sistemaEstacionamiento.RangoHorario;

public interface Tarifa {
	
    double calcularPara(RangoHorario rangoLaboral, RangoHorario rangoEstacionamiento, int precioPorHora);

	boolean correspondePara(RangoHorario rangoLaboral, RangoHorario rangoEstacionamiento);
}
