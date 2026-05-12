package ejercicioPizzeria;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author acontreras
 */
public class ColaPedidos {
    private List<Pedido> pedidos = new ArrayList<>();
    
    public synchronized void agregarPedido (Pedido p){
        pedidos.add(p);
        notifyAll();
    }
    
    public synchronized Pedido hacerPedido (String tipo) throws InterruptedException{
        //el pizzero de un tipo particular se bloquea hasta recibir un pedido nuevo
        while (!hayPedido(tipo)) {
            wait();
        }
        
        for (int i = 0; i < pedidos.size(); i++) {
            Pedido p = pedidos.get(i);
            if (p.tipo() == tipo) {
                pedidos.remove(i);
                return p;
            }
        }
        return null;
    }
    
    public boolean hayPedido (String tipo){
        for (int i = 0; i < pedidos.size(); i++) {
            Pedido p = pedidos.get(i);
            if (p.tipo() == tipo) {
                return true;
            }
        }
        return false;
    }
}
