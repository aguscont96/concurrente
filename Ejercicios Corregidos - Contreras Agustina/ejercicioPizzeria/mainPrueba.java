 package ejercicioPizzeria;
/**
 *
 * @author acontreras
 */
public class mainPrueba {
    public static void main(String[] args) {
        //Iniciamos ambos monitores y luego los hilos que interactuan con ellos
        //Monitores
        System.out.println("Abrimos mostrador");
        Pizzeria p=new Pizzeria(6);
        ColaPedidos cola = new ColaPedidos();
        //Hilos
        System.out.println("Iniciamos generador de pedidos");
        Reloj reloj = new Reloj (8,20);
        Thread hiloReloj = new Thread (reloj);
        Thread generador= new Thread(new GeneradorPedidos(cola,reloj));
        System.out.println("Ambos maestros pizzeros comienzan a trabajar");
        Thread maestroPizzeroNapo = new Thread (new MaestroPizzero("Napolitana", cola,p));
        Thread maestroPizzeroVeg = new Thread (new MaestroPizzero("Vegana",cola,p));
        Thread repartidor = new Thread (new Repartidor ("Lucas",p));
        Thread repartidora = new Thread (new Repartidor ("Ana",p));
        //Inicio hilos
        hiloReloj.start();
        generador.start();
        maestroPizzeroNapo.start();
        maestroPizzeroVeg.start();
        repartidor.start();
        repartidora.start();
    }
}
