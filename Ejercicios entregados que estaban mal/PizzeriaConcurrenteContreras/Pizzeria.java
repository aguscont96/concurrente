package PizzeriaConcurrenteContreras;
import java.util.LinkedList;
import java.util.Queue;
/**
 *
 * @author acontreras
 */
public class Pizzeria {
    public static final int MAX_PEDIDOS=5;
    private Queue<Pedido> mostrador = new LinkedList<>();
    private int repartidoresDescansados=0;
    
    public synchronized void recibirPedido (Pedido pedido) throws InterruptedException{
        while (mostrador.size() >= MAX_PEDIDOS){
            System.out.println("El mostrador está lleno");
            wait();
        }
        mostrador.offer(pedido);
        System.out.println("Pedido recibido: "+ pedido.toString());
        notifyAll();
    }
    
    public synchronized void entregarPedido (Repartidor r) throws InterruptedException{
        while (mostrador.isEmpty()){
            wait();
        }
        Pedido pedido=mostrador.poll();
        System.out.println(r.toString()+ " se llevó "+pedido.toString());
        r.viajar();
        notifyAll();
    }
    
    public synchronized void descansar(Repartidor r) throws InterruptedException{
        if (r.viajesRealizados()==10){
            System.out.println(r.toString() + " está descansando");
            Thread.sleep(1000);
        }
    }
}
