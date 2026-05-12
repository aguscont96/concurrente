package MontañaRusaConcurrenteContreras;
import java.util.concurrent.Semaphore;
/**
 *
 * @author acontreras
 */
public class Montaña {
    static final int cantidadAsientos=4;
	private int cantidadViajes;
	private int asientosNoDisponibles=0;
	private int viajesFinalizados=0;
	
	private Semaphore exclusion= new Semaphore(1);
	private Semaphore salirCarro= new Semaphore(0);
	private Semaphore empezarViaje= new Semaphore(0);
	private Semaphore asientosDisponibles= new Semaphore (0,true);
	private Semaphore carroDespejado= new Semaphore(1);
	
	public Montaña (int cantViajes){
		cantidadViajes=cantViajes;
	}
	
	public void subirPasajeros (){
		try{
			carroDespejado.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Estamos preparando el carro... No desesperes");
		asientosDisponibles.release(cantidadAsientos);
	}
	
	public void viajar(){
		try{
			empezarViaje.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("El carro está en viaje. Vamos por el "+(viajesFinalizados+1));
		viajesFinalizados++;
	}
	
	public void bajarPasajeros(){
		System.out.println("Los pasajeros están descendiendo del carrito");
		salirCarro.release(cantidadAsientos);
	}
	
	public void subir (int numPas) {
		try {
			asientosDisponibles.acquire();
			exclusion.acquire();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		System.out.println("\t"+numPas+" consiguió asiento y está subiendo");
		asientosNoDisponibles++;
		/* Si ya no quedan asientos libres, la ultima persona en sentarse
		   notifica al carro que puede ponerse en marcha */
		if (asientosNoDisponibles==cantidadAsientos) {
			empezarViaje.release();	}
		exclusion.release();
	}
	
	public void bajarse (int numPas){
		try{
			salirCarro.acquire();
			exclusion.acquire();
		}catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		System.out.println("\t"+numPas+" ya se está bajando");
		asientosNoDisponibles--;
		if(asientosNoDisponibles==0){
			carroDespejado.release();
		}
		exclusion.release();
	}
}
