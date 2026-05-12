package ejercicioImpresora;

/**
 *
 * @author acontreras
 */
public class mainPrueba {
    public static void main(String[] args) {
        Spooler spooler = new Spooler();
        Thread impresor1 = new Thread(new HiloImpresor(spooler), "Impresor-1");
        Thread impresor2 = new Thread(new HiloImpresor(spooler), "Impresor-2");
        Thread escritor1= new Thread (new HiloEscritor(spooler,"Usuario-1"));
        Thread escritor2= new Thread (new HiloEscritor(spooler,"Usuario-2"));
        Thread escritor3= new Thread (new HiloEscritor(spooler,"Usuario-3"));
        impresor1.start();
        impresor2.start();
        escritor1.start();
        escritor2.start();
        escritor3.start();
    }
}
