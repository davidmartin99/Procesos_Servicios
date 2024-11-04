package Tema2_MultiHilos.Practica_Tema2;

public class Ejercicio_1 {

    public static void main(String[] args) {
        /**
         * TareaCalculo_a
         * //Creo el bucle for para que me genere 30 hilos
         *         for(int i = 1; i <= 30; i++){
         *             TareaCalculo_a hilo1 = new TareaCalculo_a();
         *             hilo1.setName("Hilo" + i); //Nombre del hilo
         *             hilo1.start(); //Lanzamos el hilo
         *         }//Fin for
         */

        //Creo el bucle for para que me genere 30 hilos
         for(int i = 1; i <= 30; i++){
             TareaCalculo_c tarea2 = new TareaCalculo_c();
             Thread hilo2 = new Thread(tarea2, "Hilo-" + i); //Nombre del hilo
             hilo2.start(); //Iniciamos el hilo
         }//Fin for

    }//Fin main

}//Fin class
