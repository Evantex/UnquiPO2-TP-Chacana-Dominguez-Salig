package tp.po2.sem.sistemaEstacionamiento;

import tp.po2.sem.estacionamiento.Estacionamiento;

public class EventoEstacionamiento {
    public enum Tipo {
        INICIO,
        FIN
    }

    private Tipo tipo;
    private Estacionamiento estacionamiento;

    public EventoEstacionamiento(Tipo tipo, Estacionamiento unEstacionamiento) {
        this.tipo = tipo;
        this.setEstacionamiento(unEstacionamiento);
    }

    public Tipo getTipo() {
        return tipo;
    }

	public Estacionamiento getEstacionamiento() {
		return estacionamiento;
	}

	public void setEstacionamiento(Estacionamiento estacionamiento) {
		this.estacionamiento = estacionamiento;
	}

    
}

