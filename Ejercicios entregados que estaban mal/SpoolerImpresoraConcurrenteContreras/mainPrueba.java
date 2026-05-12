package SpoolerImpresoraConcurrenteContreras;

/**
 *
 * @author acontreras
 */
public class mainPrueba {
    public static void main(String[] args) {
        Spooler sp=new Spooler();
        Thread hiloEscritor= new Thread (new HiloEscritor(sp));
        Thread hiloImpresor= new Thread (new HiloImpresor(sp));
        hiloEscritor.start();
        hiloImpresor.start();
    }
}
