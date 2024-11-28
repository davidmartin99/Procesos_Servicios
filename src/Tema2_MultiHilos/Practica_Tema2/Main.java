package Tema2_MultiHilos.Practica_Tema2;

import Tema2_MultiHilos.Practica_Tema2.Ejercicio1.TareaCalculo_c;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Buffer buffer = new Buffer();

        for(int i = 1; i <= 2; i++){
            String nombre = "Hola";
            ControlHilos control = new ControlHilos(buffer, nombre);
            Thread hilo2 = new Thread(control, "Hilo_" + i); //Nombre del hilo
            hilo2.start(); //Iniciamos el hilo
        }//Fin for

        System.out.println("Todos los hilos han terminado su ejecución.");
        System.out.println("Contenido del buffer: ");

    }
}
