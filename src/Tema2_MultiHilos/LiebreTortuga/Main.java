package Tema2_MultiHilos.LiebreTortuga;

public class Main {
    public static void main(String args[]) {
        // Creación del hilo para la tortuga
        Thread tortuga = new Thread(new TortugaThread());

        // Creación del hilo para la liebre
        Thread liebre = new Thread(new LiebreThread());

        // Inicia el hilo de la tortuga
        tortuga.start();

        // Si se quisiera asignar más prioridad a la tortuga
        tortuga.setPriority(Thread.MAX_PRIORITY);

        // Inicia el hilo de la liebre
        liebre.start();

        // Si se quisiera asignar menos prioridad a la liebre
        liebre.setPriority(Thread.MIN_PRIORITY);
    }
}
