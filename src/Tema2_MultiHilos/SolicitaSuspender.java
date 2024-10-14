package Tema2_MultiHilos;

public class SolicitaSuspender {
    private boolean suspender;

    // Cambia el estado del objeto
    public synchronized void set(boolean b) {
        suspender = b; // Cambio de estado sobre el objeto
        notifyAll(); // Notifica a todos los hilos en espera
    }

    public synchronized void esperandoParaReanudar() throws InterruptedException {
        while (suspender) {
            wait(); // Suspende el hilo hasta recibir notify() o notifyAll()
        }
    }
} // SolicitaSuspender


