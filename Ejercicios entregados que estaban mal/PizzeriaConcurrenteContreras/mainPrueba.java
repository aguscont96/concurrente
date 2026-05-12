/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PizzeriaConcurrenteContreras;
/**
 *
 * @author acontreras
 */
public class mainPrueba {
    public static void main(String[] args) {
        Pizzeria p=new Pizzeria();
        Thread generador= new Thread(new GeneradorPedidos(p));
        generador.start();
        
        for (int i = 1; i < 4; i++) {
            Thread repartidor= new Thread (new Repartidor ("Rep "+i, p));
            repartidor.start();
        }
    }
}
