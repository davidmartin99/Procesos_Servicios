package Tema2_MultiHilos.Practica_Tema2.Ejercicio5;

/**
 * Clase principal de HiloPrioridades que lanza los tres hilos con diferentes prioridades.
 */
public class Ejercicio_6 {

    /**
     * Método principal que crea y ejecuta los hilos.
     *
     * @param args no se utiliza.
     */
    public static void main(String[] args) {
        // Crea tres hilos con diferentes prioridades
        HiloPrioridades hilo1 = new HiloPrioridades("Hilo 1", 1);
        HiloPrioridades hilo2 = new HiloPrioridades("Hilo 2", 3);
        HiloPrioridades hilo3 = new HiloPrioridades("Hilo 3", 5);

        // Inicia los hilos
        hilo1.start();
        hilo2.start();
        hilo3.start();

        // En Java, la prioridad de un hilo sugiere al sistema operativo (SO)
        // qué hilos deben tener más o menos tiempo de CPU,
        // pero no garantiza un orden específico de ejecución.
    }//Fin main
} // Fin clase Ejercicio_6
