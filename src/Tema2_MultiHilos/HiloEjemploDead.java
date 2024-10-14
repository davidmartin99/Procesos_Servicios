package Tema2_MultiHilos;

/*
    Método para matar el hilo
 */
public class HiloEjemploDead extends Thread {
    // Creamos stopHilo
    private boolean stopHilo = false;

    public void pararHilo() {
        stopHilo = true; // Corrige la asignación
    }

    // Método run
    public void run() {
        while (!stopHilo) {
            System.out.println("En el Hilo");
        }
    }

    public static void main(String[] args) {
        HiloEjemploDead h = new HiloEjemploDead();
        h.start(); // Inicia el hilo

        for (int i = 0; i < 100000; i++); // No hago nada

        h.pararHilo(); // Detiene el hilo
    } // main
} // fin clase
