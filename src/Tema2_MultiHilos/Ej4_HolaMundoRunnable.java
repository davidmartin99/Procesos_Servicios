package Tema2_MultiHilos;

public class Ej4_HolaMundoRunnable implements Runnable {
    private final String mensaje;

    // Constructor que recibe una cadena
    public Ej4_HolaMundoRunnable(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public void run() {
        try {
            // Espera un tiempo proporcional al ID del hilo
            long id = Thread.currentThread().getId();
            // Espera tiempo proporcional al ID en milisegundos
            Thread.sleep(id * 100);

            // Muestra el mensaje incluyendo la cadena y el identificador del hilo
            System.out.println("Hola mundo " + mensaje + " desde el hilo " + id);
        } catch (InterruptedException e) {
            // Manejo de la excepción si el hilo es interrumpido
            Thread.currentThread().interrupt();
            System.out.println("El hilo fue interrumpido.");
        }
    }//Fin run

    public static void main(String[] args) {
        // Crear y ejecutar 5 hilos
        for (int i = 1; i <= 5; i++) {
            Ej4_HolaMundoRunnable holaMundoRunnable = new Ej4_HolaMundoRunnable("hilo " + i);
            Thread hilo = new Thread(holaMundoRunnable); // Crear un nuevo hilo
            hilo.start(); // Inicia el hilo
        }
    }//Fin main

}//Fin class
