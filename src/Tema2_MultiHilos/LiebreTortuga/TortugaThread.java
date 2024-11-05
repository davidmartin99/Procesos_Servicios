package Tema2_MultiHilos.LiebreTortuga;

public class TortugaThread implements Runnable {

    // Método que se ejecuta cuando el hilo es iniciado
    public void run() {
        int i = 0; // Contador
        System.out.println("Arranco la tortuga");

        // Bucle que se ejecuta 5 veces
        while (i < 5) {
            try {
                Thread.sleep(1000); // El hilo duerme durante 5 segundos
                System.out.println("Soy la tortuga");
            } catch (InterruptedException ex) {
                // Captura la excepción si el hilo es interrumpido
            }
            i++; // Incrementa el contador
        }

        System.out.println("Termina la tortuga");
    }

    // Método principal para probar el hilo
    public static void main(String[] args) {
        // Crea un objeto TortugaThread
        TortugaThread tortuga = new TortugaThread();

        // Crea un hilo a partir del objeto tortuga
        Thread hiloTortuga = new Thread(tortuga);

        // Inicia el hilo
        hiloTortuga.start();
    }
}
