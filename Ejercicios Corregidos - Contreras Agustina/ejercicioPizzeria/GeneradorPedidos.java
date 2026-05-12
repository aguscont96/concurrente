package ejercicioPizzeria;
import java.util.Random;
/**
 *
 * @author acontreras
 */
public class GeneradorPedidos implements Runnable{
    private final Random random = new Random();
    private final ColaPedidos pedidos;
    private final String[] tipos = {"Napolitana", "Vegana"}; 
    private final Reloj reloj;
    
    public GeneradorPedidos(ColaPedidos c, Reloj r) {
        this.pedidos = c;
        this.reloj=r;
    }
    
    @Override
    public void run(){
        try {
            while (true){
                //si la pizzeria está cerrada, este hilo queda bloqueado en espera. los pedidos en curso finalizan
                if (reloj.estaAbierta()){
                int randomPos=random.nextInt(tipos.length);
                String tipo= tipos[randomPos];
                String cliente = "Cliente "+random.nextInt(100);
                pedidos.agregarPedido (new Pedido (tipo,cliente));
                Thread.sleep(500);
                }else{
                    reloj.esperarApertura();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
	}
    }
    
    
}
