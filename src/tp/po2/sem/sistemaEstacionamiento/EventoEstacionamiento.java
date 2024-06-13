package tp.po2.sem.sistemaEstacionamiento;

public class EventoEstacionamiento {
    public enum Tipo {
        INICIO,
        FIN
    }

    private Tipo tipo;
    private String patente;
    private String nroTelefono;

    public EventoEstacionamiento(Tipo tipo, String patente, String nroTelefono) {
        this.tipo = tipo;
        this.patente = patente;
        this.nroTelefono = nroTelefono;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public String getPatente() {
        return patente;
    }

   public String getNroTelefono() {
	   return nroTelefono;
   }
}

