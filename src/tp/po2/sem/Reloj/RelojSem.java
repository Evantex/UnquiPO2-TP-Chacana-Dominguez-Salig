package tp.po2.sem.Reloj;

import java.time.LocalTime;

public class RelojSem implements Reloj {
    @Override
    public LocalTime horaActual() {
        return LocalTime.now();
    }
}