package ejercicioImpresora;

/**
 *
 * @author acontreras
 */
public class TrabajoImpresion {
    private final int orden;
    private final String dato;

    public TrabajoImpresion(int o, String d) {
        this.dato = d;
        this.orden = o;
    }
    
    public int orden(){
        return orden;
    }
    
    public String dato(){
        return dato;
    }
    
    public String toString(){
        return orden+" - "+dato;
    }
}
