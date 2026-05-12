package PizzeriaConcurrenteContreras;
import java.util.Random;
/**
 *
 * @author acontreras
 */
public class GeneradorPedidos implements Runnable{
    private final Random random = new Random();
    private final Pizzeria pizzeria;
    private final String[] tipos = {"Napolitana", "Vegana"};    
    public GeneradorPedidos(Pizzeria p) {
        this.pizzeria = p;
    }
    
    @Override
    public void run(){
        try {
            while (true){
                int randomPos=random.nextInt(tipos.length);
                String tipo= tipos[randomPos];
                String cliente = "Cliente "+random.nextInt(100);
                pizzeria.recibirPedido (new Pedido (tipo,cliente));
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
	}
    }
    
    
}
