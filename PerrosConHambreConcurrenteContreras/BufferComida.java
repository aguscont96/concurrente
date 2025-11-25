package PerrosConHambreConcurrenteContreras;
import java.util.concurrent.Semaphore;

/**
 *
 * @author acontreras
 */

public class BufferComida {
    private int nPlatos;
    private int nComida;
    private Semaphore perrosComer;
    private Semaphore perrosEsperando;
    private Semaphore encargadoEsperando;

    public BufferComida(int nPlatos) {
        this.nPlatos = nPlatos;
        this.nComida = nPlatos;
        this.perrosComer = new Semaphore(1);
        this.perrosEsperando = new Semaphore(1);
        this.encargadoEsperando = new Semaphore(1);
    }

    public void tomarPlato(int perroId) throws InterruptedException {
        perrosComer.acquire();

        while (nComida == 0) {
            System.out.println("Perro " + perroId + " ladra, no hay comida");
            perrosEsperando.acquire();
            encargadoEsperando.release(); 
            //Libera el permiso del encargado para avisarle y tambien avisa que hay perro esperando
        }

        nComida--;
        System.out.println("Perro " + perroId + " ha tomado un plato");

        perrosComer.release();
    }


    public void llenarPlatos() throws InterruptedException {
        encargadoEsperando.acquire();

        nComida = nPlatos;
        System.out.println("Encargado ha llenado los platos");

        perrosEsperando.release();
    }
}