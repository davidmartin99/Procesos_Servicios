package Tema2_MultiHilos.LiebreTortuga;

public class LiebreThread implements Runnable {

    // Método que se ejecuta cuando el hilo es iniciado
    @Override
    public void run() {
        int i = 0; // Contador
        System.out.println("Arranco la liebre");

        // Bucle que se ejecuta 5 veces
        while (i < 5) {
            try {
                Thread.sleep(2000); // El hilo duerme durante 2 segundos
                System.out.println("Soy la liebre");
            } catch (InterruptedException ex) {
                // Captura la excepción si el hilo es interrumpido
            }
            i++; // Incrementa el contador
        }

        System.out.println("Termina la liebre");
    }

    // Método principal para probar el hilo
    public static void main(String[] args) {
        // Crea un objeto LiebreThread
        LiebreThread liebre = new LiebreThread();

        // Crea un hilo a partir del objeto liebre
        Thread hiloLiebre = new Thread(liebre);

        // Inicia el hilo
        hiloLiebre.start();
    }
}
