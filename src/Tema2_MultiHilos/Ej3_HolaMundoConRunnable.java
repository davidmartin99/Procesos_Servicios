package Tema2_MultiHilos;

public class Ej3_HolaMundoConRunnable implements Runnable {
    @Override
    public void run() {
        // Muestra el mensaje incluyendo el identificador del hilo
        System.out.println("Hola mundo desde el hilo " + Thread.currentThread().getId());
    }

    public static void main(String[] args) {
        // Crear y ejecutar 5 hilos
        for (int i = 0; i < 5; i++) {
            Ej3_HolaMundoConRunnable holaMundoRunnable = new Ej3_HolaMundoConRunnable();
            Thread hilo = new Thread(holaMundoRunnable); // Crear un nuevo hilo
            hilo.start(); // Inicia el hilo
        }
    }//Fin main

}//Fin class
