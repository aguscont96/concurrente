package ejercicioImpresora;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author acontreras
 */
public class Spooler {
    private final Queue<TrabajoImpresion> bufferPrimario = new LinkedList<>();
    private final Queue<TrabajoImpresion> bufferSecundario = new LinkedList<>();
    private final int MAX_PRIMARIO=5; //no cambia este valor
    private int contadorOrden = 0;
    private final Lock lockOrden = new ReentrantLock(); //el lock de orden es importante porque nos permite bloquear la posibilidad de que dos hilos quieran subir o bajar el orden
    private final Lock lockColas = new ReentrantLock(); //este lock nos permite proteger ambas colas y la condición tanto para impresión como para escritura
    private final Condition hayDatos = lockColas.newCondition();

    public void escribir (String datos){
        TrabajoImpresion trabajo;
        //tomo el orden global
        lockOrden.lock();
        try{
            trabajo = new TrabajoImpresion (contadorOrden++, datos);
        }finally{
            lockOrden.unlock();
        }

        lockColas.lock();
        try{
            if (bufferPrimario.size() < MAX_PRIMARIO) {
                bufferPrimario.offer(trabajo);
                System.out.println("El buffer primario recibió "+trabajo.toString());
            } else {
                System.out.println("El buffer primario se lleno con "+bufferPrimario.size());
                bufferSecundario.offer(trabajo);
                System.out.println("El buffer secundario recibió "+trabajo.toString());
            }
            hayDatos.signalAll();
        }finally{
            lockColas.unlock();
        }
    }
    
    public TrabajoImpresion imprimir() throws InterruptedException {
        lockColas.lock();
        try{
            while (bufferPrimario.isEmpty() && bufferSecundario.isEmpty()){
                System.out.println(Thread.currentThread().getName()+" espera porque no hay trabajos para imprimir.");
                hayDatos.await(); //recordemos que escribir le manda la señal cuando genera un trabajo nuevo
            }

            TrabajoImpresion primario = bufferPrimario.peek();
            TrabajoImpresion secundario = bufferSecundario.peek();

            if (primario != null && (secundario == null || primario.orden() < secundario.orden())) {
                System.out.println(Thread.currentThread().getName()+" toma del buffer primario: "+primario.toString());
                return bufferPrimario.poll();
            } else {
                System.out.println(Thread.currentThread().getName()+" toma del buffer secundario: "+secundario.toString());
                return bufferSecundario.poll();
            }
        }finally{
            lockColas.unlock();
        }
    }
}
