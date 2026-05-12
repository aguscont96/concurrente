package ejercicioImpresora;

/**
 *
 * @author acontreras
 */
public class HiloEscritor implements Runnable{
    private final Spooler spooler;
    private final String nombre;
    
    public HiloEscritor(Spooler s, String n){
        this.spooler=s;
        this.nombre=n;
    }

    @Override
    public void run() {
        for (int i = 1; i < 6; i++) {
            String datos = "Trabajo "+i+" de "+nombre;
            spooler.escribir(datos);
        
            try{
                Thread.sleep(1000);
            } catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
    }
    
    
}