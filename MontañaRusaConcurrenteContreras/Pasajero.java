package MontañaRusaConcurrenteContreras;

/**
 *
 * @author acontreras
 */
public class Pasajero implements Runnable{
    Montaña mont;
    public int num;
	
    public Pasajero (Montaña m, int n){
	mont=m;
	num=n;
    }
	
    public void run(){
	while (true){
            mont.subir(num);
	    mont.bajarse(num);
	}
    }
}
