package PerrosConHambreConcurrenteContreras;

/**
 *
 * @author acontreras
 */

public class EncargadoComida extends Thread {
    private BufferComida buffer;

    public EncargadoComida(BufferComida buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(2000);
                System.out.println("Encargado ha preparado raciones de comida");

                buffer.llenarPlatos();

                Thread.sleep(3000);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
