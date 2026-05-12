package ejercicioPizzeria;

/**
 *
 * @author acontreras
 */
public class Reloj implements Runnable {
    private final int horaApertura;
    private final int horaCierre;

    private int horaActual;
    private boolean abierta;

    public Reloj(int horaApertura, int horaCierre) {
        this.horaApertura = horaApertura;
        this.horaCierre = horaCierre;
        this.horaActual = 0;
        this.abierta = false;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(1000);
                avanzarHora();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private synchronized void avanzarHora() {
        //avanzamos la hora y actualizamos el estado de apertura/cierre
        horaActual++;
        //cuando llega a 24, reiniciamos
        if (horaActual == 24) {
            horaActual=0;
        }
        
        if (!abierta && horaActual == horaApertura) {
            abierta = true;
            System.out.println("[Reloj] La pizzeria abre en la hora " + horaActual);
            notifyAll();
        }

        if (abierta && horaActual== horaCierre) {
            abierta = false;
            System.out.println("[Reloj] La pizzeria cierra en la hora " + horaActual);
            notifyAll();
        }
    }

    public synchronized boolean estaAbierta() {
        return abierta;
    }

    public synchronized int getHoraActual() {
        return horaActual;
    }

    public synchronized String getEstado() {
        if (abierta) {
            return "ABIERTA";
        } else {
            return "CERRADA";
        }
    }


    public synchronized void esperarApertura() throws InterruptedException {
        while (!abierta) {
            wait();
        }
    }
}
