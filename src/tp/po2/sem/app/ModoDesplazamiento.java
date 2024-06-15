package tp.po2.sem.app;

public interface ModoDesplazamiento 
{

	public void caminando( App aplicacion, String patente ) throws Exception;
	
	public void conduciendo( App aplicacion, String patente ) throws Exception;
	
	public void update( App aplicacion );

}
