package tp.po2.sem.Reloj;

import java.time.LocalTime;

public class RelojSem  {
	
	LocalTime horaActual;
	
	public RelojSem () {
		this.horaActual = LocalTime.now();
	}
	
    public LocalTime getHoraActual() {
    	return this.horaActual;
    }
    
    public void setHoraActual(LocalTime horaDada) {
    	this.horaActual = horaDada;
    }
    
    
}