package tp.po2.sem.sistemaEstacionamiento;

import java.util.ArrayList;
import java.util.List;

public class Notificador {
    private List<Observer> observadores;

    public Notificador() {
        this.observadores = new ArrayList<>();
    }

    public void agregarObservador(Observer observador) {
        observadores.add(observador);
    }

    public void eliminarObservador(Observer observador) {
        observadores.remove(observador);
    }

    public void notificarObservadores(EventoEstacionamiento evento) {
        for (Observer observador : observadores) {
            observador.actualizar(evento);
        }
    }
}

