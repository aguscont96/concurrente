package ejercicioActividadesRiesgosas;

import java.util.concurrent.Semaphore;

/**
 *
 * @author acontreras
 */
public class Actividad {
    private final String nombre;
    private final Semaphore cupo;
    
    public Actividad (String n){
        this.nombre=n;
        this.cupo= new Semaphore(4); //como son doce usuarios y tres actividades, nuestro cupo es de cuatro
    }
    
    public void entrar() throws InterruptedException{
        cupo.acquire();
    }
    
    public boolean intentarEntrar(){
        //intenta tomar cupo sin bloquearse
        return cupo.tryAcquire();
    }
    
    public void salir() throws InterruptedException{
        cupo.release();
    }
    
    public String nombre(){
        return nombre;
    }
}
