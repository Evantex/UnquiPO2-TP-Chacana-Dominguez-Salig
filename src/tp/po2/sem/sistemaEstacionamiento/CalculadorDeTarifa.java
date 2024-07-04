package tp.po2.sem.sistemaEstacionamiento;


public class CalculadorDeTarifa {

	private final Tarifa tarifaCompleta = new TarifaCompleta();
	private final Tarifa tarifaParcial = new TarifaParcial();

	public double determinarCobroPara(RangoHorario rangoLaboral, RangoHorario rangoEstacionamiento, int precioPorHora) {
		
		if (rangoLaboral.estaDentroDelRango(rangoEstacionamiento.getHoraInicioRango())
				&& rangoLaboral.estaDentroDelRango(rangoEstacionamiento.getHoraFinRango())) {
			return tarifaCompleta.calcular(rangoLaboral, rangoEstacionamiento, precioPorHora);
		} else {
			return tarifaParcial.calcular(rangoLaboral, rangoEstacionamiento, precioPorHora);
		}
		
	}
}
