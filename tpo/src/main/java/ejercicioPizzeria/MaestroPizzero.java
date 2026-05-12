package ejercicioPizzeria;

/**
 *
 * @author acontreras
 */
public class MaestroPizzero implements Runnable {
    private String tipo;
    private ColaPedidos pedidos;
    private Pizzeria mostrador;
    
    public MaestroPizzero (String t, ColaPedidos p, Pizzeria m){
        this.tipo=t;
        this.pedidos=p;
        this.mostrador=m;
    }
    
    public void run(){
        try{
            while (true) {
                Pedido p = pedidos.hacerPedido(tipo);
                prepararPedido(p);
                mostrador.dejarPedido(p);
            }
        } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        }
    }
    
    public void prepararPedido (Pedido p) throws InterruptedException{
        System.out.println("Se está preparando el pedido "+ p.tipo() + " para el cliente "+ p.cliente());
        Thread.sleep(1000*p.cantidad());
        System.out.println("Pedido de "+p.cliente()+" fue finalizado en cocina");
    }
}
