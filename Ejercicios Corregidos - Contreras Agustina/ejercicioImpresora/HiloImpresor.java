package ejercicioImpresora;

/**
 *
 * @author acontreras
 */
public class HiloImpresor implements Runnable{
    private final Spooler spooler;
    
    public HiloImpresor(Spooler s){
        this.spooler=s;
    }

    @Override
    public void run() {
        String nombre = Thread.currentThread().getName();
        try{
            System.out.println(nombre+" quedó listo para imprimir.");
            while (true) {
                TrabajoImpresion t= spooler.imprimir();
                System.out.println(nombre+" terminó de imprimir "+t.toString());
                Thread.sleep(1000);
            }
        } catch (InterruptedException e){
            System.out.println(nombre+" fue interrumpido.");
            Thread.currentThread().interrupt();
        }
    }
    
    
}
