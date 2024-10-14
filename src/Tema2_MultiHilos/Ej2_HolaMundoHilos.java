package Tema2_MultiHilos;

public class Ej2_HolaMundoHilos extends Thread {
    @Override
    public void run() {
        // Muestra el mensaje incluyendo el identificador del hilo
        System.out.println("Hola mundo desde el hilo " + getId());
    }


    public static void main(String[] args) {
        // Crear y ejecutar 5 hilos
        for (int i = 0; i < 5; i++) {
            Ej2_HolaMundoHilos hilo = new Ej2_HolaMundoHilos();
            hilo.start(); // Inicia el hilo
        }
    }

}

