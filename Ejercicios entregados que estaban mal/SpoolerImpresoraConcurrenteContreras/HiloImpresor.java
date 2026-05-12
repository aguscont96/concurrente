package SpoolerImpresoraConcurrenteContreras;

/**
 *
 * @author acontreras
 */
public class HiloImpresor implements Runnable{
    private Spooler spooler;

    public HiloImpresor(Spooler s) {
        this.spooler = s;
    }

    @Override
    public void run() {
        try {
            while (true) {
                spooler.imprimir();
                Thread.sleep(500); // Simula impresión
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
