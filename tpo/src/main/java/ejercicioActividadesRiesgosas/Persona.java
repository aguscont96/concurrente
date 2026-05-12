package ejercicioActividadesRiesgosas;

/**
 *
 * @author acontreras
 */
public class Persona implements Runnable{
    private final String nombre;
    private final Salon salon;
    private Actividad actividadInicial;
    
    public Persona (String n, Salon s, Actividad act){
        this.nombre=n;
        this.salon=s;
        this.actividadInicial=act;
    }
    
    public void run(){
        try{
            salon.esperarTurno();
            actividadInicial.entrar();
            System.out.println(nombre+" entro a "+ actividadInicial.nombre());
            //estaria bueno llevar el orden del turno
            realizarActividad ("inicial", actividadInicial);
            
            actividadInicial.salir();
            System.out.println(nombre+" salio de "+ actividadInicial.nombre());
            //vuelve a sincronizarse con el grupo antes de la siguiente actividad
            salon.esperarTurno();
            Actividad actividadSecundaria = salon.actividadSecundaria(actividadInicial);
            System.out.println(nombre +" empieza "+actividadSecundaria.nombre());
            realizarActividad("secundaria", actividadSecundaria);
            actividadSecundaria.salir();
            System.out.println(nombre+" finalizo el turno");
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }

    private void realizarActividad(String tipo, Actividad actividad) throws InterruptedException {
        System.out.println(nombre +" está realizando su actividad "+tipo+": "+actividad.nombre());
        Thread.sleep(1000);
    }
}
