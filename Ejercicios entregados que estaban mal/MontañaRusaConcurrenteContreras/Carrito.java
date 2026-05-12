package MontañaRusaConcurrenteContreras;

/**
 *
 * @author acontreras
 */
public class Carrito implements Runnable{
	Montaña mont;
	int cantidadViajes; /*Esta es la cantidad de viajes que hace el carrito puntualmente*/
	public Carrito (Montaña m, int cantViajes){
		mont=m;
		cantidadViajes=cantViajes;
	}
	
	public void run(){
		for (int i=0; i<cantidadViajes; i++){
			try{
				mont.subirPasajeros();
				Thread.sleep(2000);
				mont.viajar();
				Thread.sleep(5000);
				mont.bajarPasajeros();
				Thread.sleep(1000);
			} catch (InterruptedException e) {
					e.printStackTrace();
			}
		}
		System.out.println("El carro ya realizó todos los viajes planeados.");
	}
}
