package Tema2_MultiHilos.Practica_Tema2;

/**
 * Clase que demuestra los principales estados de un hilo usando los métodos wait(), notify(), sleep() y join().
 * El hilo pasa por diferentes estados: NEW, RUNNABLE, TIMED_WAITING, WAITING, y TERMINATED.
 */
public class EstadosHilo {

    /**
     * Método principal que crea y ejecuta un hilo, demostrando sus diferentes estados.
     * Los estados son:
     * - Nuevo: El hilo se crea pero aún no se ha iniciado.
     * - Ejecutable: El hilo está listo para ejecutarse, esperando tiempo de CPU.
     * - En ejecución: El hilo está siendo ejecutado por el procesador.
     * - Bloqueado: El hilo está esperando a liberar un recurso o esperando un tiempo específico.
     * - Muerto: El hilo ha finalizado su ejecución después de completar el método run().
     *
     * @param args Argumentos de línea de comandos.
     */

    public static void main(String[] args) {

        // Crear un objeto para sincronización
        Object lock = new Object();

        // Crear el hilo
        Thread hilo = new Thread(() -> {
            try {
                // Estado Ejecutable (RUNNABLE): El hilo está listo para ejecutarse.
                System.out.println("Hilo en ejecución (Ejecutable) - El hilo está listo para ejecutarse");

                // Estado En ejecución (RUNNING): El hilo está siendo ejecutado por el procesador.
                System.out.println("Hilo entra en ejecución (En ejecución)");
                // Simula trabajo al dormir durante 2 segundos.
                Thread.sleep(2000); // El hilo está en ejecución durante 2 segundos

                // Estado Bloqueado (BLOCKED): El hilo espera una notificación para continuar.
                synchronized (lock) {
                    System.out.println("Hilo entra en bloqueado (Bloqueado), esperando notificación");
                    lock.wait(); // El hilo entra en el estado bloqueado hasta que otro hilo lo notifique
                }

                // Después de ser notificado, el hilo terminará.
                System.out.println("Hilo sale del estado Bloqueado y finaliza");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Estado Nuevo (NEW): El hilo ha sido creado pero aún no se ha iniciado.
        System.out.println("Estado antes de iniciar: " + hilo.getState());

        // Iniciar el hilo
        hilo.start();

        try {
            // Esperar que el hilo entre en el estado Ejecutable
            Thread.sleep(1000); // Pausa el hilo principal para dar tiempo al hilo creado
            System.out.println("Estado después de dormir 1 segundo: " + hilo.getState());

            // Notificar al hilo para que salga del estado Bloqueado
            synchronized (lock) {
                lock.notify(); // Notifica al hilo para que salga del estado Bloqueado
            }

            // Esperar a que el hilo termine y pase al estado Muerto (TERMINATED)
            hilo.join(); // Esto asegura que el hilo principal espere hasta que el hilo termine
            System.out.println("Estado final (Muerto): " + hilo.getState()); // El hilo ahora debería estar en TERMINATED

        } catch (InterruptedException e) {
            e.printStackTrace();
        }//Fin try-catch
    }//Fin main
}//Fin class
