package ejercicioActividadesRiesgosas;

import java.util.concurrent.Semaphore;

/**
 *
 * @author acontreras
 */
public class Salon {
    private final Actividad[] actividades;
    private int personasListas = 0;
    private final int capacidad_turno = 12;
    private final Semaphore mutex = new Semaphore(1);
    private final Semaphore molineteEntrada = new Semaphore(0);
    private final Semaphore molineteSalida = new Semaphore(1);
    
    public Salon (){
        actividades= new Actividad[3];
        actividades[0]= new Actividad ("A1 - Acro Telas");
        actividades[1]= new Actividad ("A2 - Lyra");
        actividades[2]= new Actividad ("A3 - Yoga en Aro");
    }
    
    public void esperarTurno() throws InterruptedException {
        //mutex sólo controla el contador de personas listas para no ser modificado por varios hilos
        mutex.acquire();
        personasListas++;
        if (personasListas == capacidad_turno) {
            molineteSalida.acquire();
            System.out.println("Arranca el turno");
            molineteEntrada.release();
        }
        mutex.release();
        //La persona 12, libera el permiso que lo toma cualquiera de los que espera, lo libera y pasa el siguiente
        molineteEntrada.acquire();
        molineteEntrada.release();

        mutex.acquire();
        personasListas--;
        if (personasListas == 0) {
            molineteEntrada.acquire();
            molineteSalida.release();
        }
        mutex.release();
        //como ya hizo release, se van pasando como se hizo con el molinete de entrada. esto habilita una salida para la segunda fase, reseteando todo, ya que el metodo se reutiliza
        molineteSalida.acquire();
        molineteSalida.release();
    }
    
    public Actividad actividadSecundaria (Actividad inicial){
        while (true) {
            for (Actividad a : actividades) {
                if (a != inicial && a.intentarEntrar()) {
                    return a; // preferimos que no repita la actividad inicial
                }
            }
            for (Actividad a : actividades) {
                if (a.intentarEntrar()) {
                    return a; // si no hay otra disponible, puede repetir
                }
            }
        }
    }
    
    public Actividad[] actividades(){
        return actividades;
    }
}
