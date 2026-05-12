package ejercicioPizzeria;

/**
 *
 * @author acontreras
 */
public class Pedido {
    private final String tipo;
    private final String cliente;
    private final int cantidad;
    
    public Pedido (String t, String c){
        tipo=t;
        cliente=c;
        if (tipo=="Napolitana"){
            cantidad=1;
        } else {
            cantidad=2;
        }
    }
    
    public String toString(){
        return tipo + " - " + cliente;
    }
    
    public String tipo(){
        return tipo;
    }
    
    public String cliente(){
        return cliente;
    }
    
    public int cantidad(){
        return cantidad;
    }
}
