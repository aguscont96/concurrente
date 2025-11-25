package PizzeriaConcurrenteContreras;

/**
 *
 * @author acontreras
 */
public class Pedido {
    private final String tipo;
    private final String cliente;
    
    public Pedido (String t, String c){
        tipo=t;
        cliente=c;
    }
    
    public String toString(){
        return tipo + " - " + cliente;
    }
}
