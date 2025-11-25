package SpoolerImpresoraConcurrenteContreras;

/**
 *
 * @author acontreras
 */
public class HiloEscritor implements Runnable{
    private Spooler spooler;
    
    public HiloEscritor (Spooler s){
        this.spooler=s;
    }
    
    public void run(){
        try {
            while(true){
            for (int i = 0; i < 10; i++) {
                spooler.escribir("Data-" + i);
                Thread.sleep(200); // Simula escritura cada vez.
            }}
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
