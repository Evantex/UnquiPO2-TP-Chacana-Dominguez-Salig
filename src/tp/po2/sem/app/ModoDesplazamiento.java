package tp.po2.sem.app;

public interface ModoDesplazamiento 
{

	public void caminando(App aplicacion) throws Exception;
	
	public void conduciendo(App aplicacion) throws Exception;
	
	public void update(App aplicacion);

}
