package ejercicioPizzeria;

/**
 *
 * @author acontreras
 */
public class Repartidor implements Runnable{
    private final String nombre;
    private final Pizzeria pizzeria;
    private int viajesRealizados=0;
    
    public Repartidor(String n, Pizzeria p){
        this.nombre=n;
        this.pizzeria=p;
    }
    
    public void run(){
        try{
            while (true){
                Pedido p = pizzeria.retirarPedido();
                entregarPedido(p);
                viajesRealizados++;
                if (viajesRealizados % 10 == 0) { //Siempre descansa en multiplos de 10
                    descansar();
                }
            }
        }catch (InterruptedException e) {
            e.printStackTrace();
	}
    }
    
    public void entregarPedido (Pedido p) throws InterruptedException{
        System.out.println("El repartidor " + nombre + " está entregando el pedido de " + p.cliente());
        Thread.sleep(1000*p.cantidad());
        System.out.println("El repartidor " + nombre + " entrego el pedido de "+ p.cliente());
    }
    
    private void descansar() throws InterruptedException {
        System.out.println("El repartidor " + nombre +
                " está descansando luego de 10 viajes");
        Thread.sleep(3000);
    }
}
