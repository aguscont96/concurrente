package PerrosConHambreConcurrenteContreras;

/**
 *
 * @author acontreras
 */
public class mainPrueba {
    public static void main(String[] args) {
        int nPlatos = 5;

        BufferComida buffer = new BufferComida(nPlatos);

        EncargadoComida encargado = new EncargadoComida(buffer);
        encargado.start();

        int numPerros = 10;
        for (int i = 1; i <= numPerros; i++) {
            Perro perro = new Perro(i, buffer);
            perro.start();
        }
    }
}
