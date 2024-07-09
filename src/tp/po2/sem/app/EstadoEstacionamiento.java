package tp.po2.sem.app;
import tp.po2.sem.app.*;

public interface EstadoEstacionamiento 
{
	
	public void posibleInicioEstacionamiento( ModoDesplazamiento modo, App aplicacion ) throws Exception;
	public void posibleFinEstacionamiento( ModoDesplazamiento modo, App aplicacion ) throws Exception;

}
