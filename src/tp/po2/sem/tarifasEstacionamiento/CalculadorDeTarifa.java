package tp.po2.sem.tarifasEstacionamiento;

import java.util.ArrayList;
import java.util.List;

import tp.po2.sem.sistemaEstacionamiento.RangoHorario;

public class CalculadorDeTarifa {

	private List<Tarifa> tiposDeTarifa;

	public CalculadorDeTarifa() { // TARIFA PARA CADA RANGO HORARIO QUE PUEDA EXISTIR

		tiposDeTarifa = new ArrayList<>();

		tiposDeTarifa.add(new TarifaDentroDeHorarioLaboral());

		tiposDeTarifa.add(new TarifaFueraDeHorarioLaboral());

		tiposDeTarifa.add(new TarifaInicioAntesDeHorarioLaboralYFinDentroDeHorario());

		tiposDeTarifa.add(new TarifaInicioDentroDeHorarioLaboralYFinFueraDeHorario());

	}

	public double determinarCobroPara(RangoHorario rangoLaboral, RangoHorario rangoEstacionamiento, int precioPorHora) throws Exception{

		return this.tiposDeTarifa.stream()
				.filter(t -> t.correspondePara(rangoLaboral, rangoEstacionamiento)).findAny()
				.map(t -> t.calcularPara(rangoLaboral, rangoEstacionamiento, precioPorHora))
				.orElseThrow(() -> new Exception("No se puede establecer una tarifa para el estacionamiento"));

	}
}
