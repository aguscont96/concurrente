package SpoolerImpresoraConcurrenteContreras;
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
    private final int MAX_BUFFER_PRIMARIO=5;
    private Queue<String> bufferPrimario= new LinkedList<>();
    private Queue<String> bufferSecundario= new LinkedList<>();
    private Lock lock = new ReentrantLock();
    private Condition primarioConInfo= lock.newCondition();
    private Condition secundarioConInfo= lock.newCondition();
    private int bufferSiguiente=0;
    
    public void escribir(String datos){
        lock.lock();
        //Siempre toma al buffer primario como prioridad, por lo que se basa en su condición para esperar.
        //Se basa siempre en el buffer siguiente para elegir en cuál escribir.
        try{
           while (bufferSiguiente == 0 && bufferPrimario.size() >= MAX_BUFFER_PRIMARIO) {
                secundarioConInfo.signal();
                primarioConInfo.await();
            }
           if(bufferSiguiente==0){
               bufferPrimario.offer(datos);
               primarioConInfo.signal();
           }else{
               bufferSecundario.offer(datos);
               secundarioConInfo.signal();
           }
        }catch(InterruptedException e) {
            Thread.currentThread().interrupt();
        }finally{
            lock.unlock();
        }
    }
    
    public void imprimir(){
        lock.lock();
        //Mientras los buffers están vacios, espera. Luego, verifica cuál está vacío, por lo que elige 
        //el buffer que imprimiremos. Finalmente, de acuerdo al buffer siguiente, avisa con signal.
        try{
            while(bufferPrimario.isEmpty() & bufferSecundario.isEmpty()){
                primarioConInfo.await();
                secundarioConInfo.await();
            }
            Queue<String> bufferElegido;
            String bu;
            if(bufferPrimario.isEmpty()){
                bufferElegido=bufferSecundario;
                bu="Secundario";
                bufferSiguiente=0;
            }else{
                bufferElegido=bufferPrimario;
                bu="Primario";
                bufferSiguiente=1;
            }
            String data=bufferElegido.poll();
            System.out.println("Imprimiendo: "+data+" en Buffer "+bu);
            if (!bufferElegido.isEmpty()) {
                if (bufferSiguiente == 0) {
                    secundarioConInfo.signal();
                } else {
                    primarioConInfo.signal();
                }
            }
        }catch(InterruptedException e) {
            Thread.currentThread().interrupt();
        }finally{
            lock.unlock();
        }
    }
}
