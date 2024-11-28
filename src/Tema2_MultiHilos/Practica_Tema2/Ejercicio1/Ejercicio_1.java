package Tema2_MultiHilos.Practica_Tema2.Ejercicio1;

/**
 * Clase que contiene el método principal para iniciar múltiples hilos de cálculo.
 * @author David
 * @date 04/11/2024
 */

public class Ejercicio_1 {

    /**
     * Método principal que crea y lanza 30 hilos.
     *
     * Este método utiliza un bucle for para instanciar y comenzar 30 hilos de la clase
     * TareaCalculo_c. Cada hilo se nombra de forma secuencial como "Hilo-1", "Hilo-2", etc.
     *
     * @param args argumentos de línea de comandos (no se utilizan en este programa)
     */

    public static void main(String[] args) {
        /**
         * TareaCalculo_a
         * // Creo el bucle for para que me genere 30 hilos
         *         for (int i = 1; i <= 30; i++) {
         *             TareaCalculo_a tarea = new TareaCalculo_a();
         *             tarea.setName("Hilo-" + i); // Asigna un nombre único a cada hilo
         *             tarea.start(); // Inicia el hilo
         *         }
         */


        // Creo el bucle for para que me genere 30 hilos
         for(int i = 1; i <= 30; i++){
             TareaCalculo_c tarea2 = new TareaCalculo_c();
            Thread hilo2 = new Thread(tarea2, "Hilo_" + i); //Nombre del hilo
             hilo2.start(); //Iniciamos el hilo
         }//Fin for

    }//Fin main

}//Fin class
