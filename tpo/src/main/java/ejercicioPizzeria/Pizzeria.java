package ejercicioPizzeria;
import java.util.LinkedList;
import java.util.Queue;
/**
 *
 * @author acontreras
 */
public class Pizzeria {
    private int MAX;
    private Queue<Pedido> mostrador = new LinkedList<>();
    
    public Pizzeria (int max_pedidos){
        this.MAX=max_pedidos;
    }
    
    public synchronized void dejarPedido (Pedido pedido) throws InterruptedException{
        //no podemos dejar pedidos si el mostrador está lleno
        while (mostrador.size() == MAX){
            System.out.println("El mostrador está lleno");
            wait();
        }
        mostrador.add(pedido);
        System.out.println("Pedido listo para entregar: "+ pedido.toString());
        notifyAll();
    }
    
    public synchronized Pedido retirarPedido () throws InterruptedException{
        //me bloqueo hasta que llegue un pedido
        while (mostrador.isEmpty()){
            wait();
        }
        Pedido pedido=mostrador.remove();
        notifyAll();
        return pedido;
    }
}
