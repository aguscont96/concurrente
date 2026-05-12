package ejercicioActividadesRiesgosas;

import java.util.Random;

/**
 *
 * @author acontreras
 */
public class mainPrueba {
    public static void main(String[] args) {
        Salon salon = new Salon();
        Random random = new Random();
        for (int i = 1; i < 13; i++) {
            Actividad actividadInicial = salon.actividades()[(random.nextInt(3))];
            Thread persona = new Thread (new Persona("P"+i, salon, actividadInicial));
            persona.start();
        }
    }
}
