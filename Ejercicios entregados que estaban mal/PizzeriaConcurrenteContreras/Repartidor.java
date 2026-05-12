package PizzeriaConcurrenteContreras;

/**
 *
 * @author acontreras
 */
public class Repartidor implements Runnable{
    private final String nombre;
    private final Pizzeria pizzeria;
    private int viajesRealizados;
    
    public Repartidor(String n, Pizzeria p){
        this.nombre=n;
        this.pizzeria=p;
        this.viajesRealizados=0;
    }
    
    public void run(){
        try{
            while (true){
                pizzeria.entregarPedido(this);
                pizzeria.descansar(this);
            }
        }catch (InterruptedException e) {
            e.printStackTrace();
	}
    }
    
    public String toString(){
        return "Repartidor "+this.nombre;
    }
    
    public void viajar(){
        if(viajesRealizados==10){
            viajesRealizados=1;
        }else{
            viajesRealizados++;
        }
    }
    
    public int viajesRealizados(){
        return viajesRealizados;
    }
}
