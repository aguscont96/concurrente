package MontañaRusaConcurrenteContreras;

import java.util.Stack;

/**
 *
 * @author acontreras
 */
public class Test {
    public static void main(String[] args) {
        int cantPas=12;
        int cantViajes=4;
        Montaña mont=new Montaña(cantViajes);
        Thread[] pasajeros=new Thread[cantPas];
        for (int i = 0; i < cantPas; i++) {
            pasajeros[i]=new Thread (new Pasajero(mont, i));
            pasajeros[i].start();
        }
        Thread carrito= new Thread(new Carrito(mont,cantViajes));
        carrito.start();
    }
}
