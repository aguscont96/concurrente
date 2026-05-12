package PerrosConHambreConcurrenteContreras;

/**
 *
 * @author acontreras
 */

public class Perro extends Thread {
    private int id;
    private BufferComida buffer;

    public Perro(int id, BufferComida buffer) {
        this.id = id;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            while (true) {
                buffer.tomarPlato(id);

                System.out.println("Perro " + id + " está comiendo");

                Thread.sleep(1000);

                System.out.println("Perro " + id + " dejo de comer");

            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
