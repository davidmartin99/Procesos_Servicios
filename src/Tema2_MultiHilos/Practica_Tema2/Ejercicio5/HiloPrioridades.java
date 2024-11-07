package Tema2_MultiHilos.Practica_Tema2.Ejercicio5;

/**
 * Clase que tiene hilos con diferentes prioridadees.
 * Ejecuta las tareas según qué prioridad tenga.
 */
public class HiloPrioridades extends Thread {

    /**
     * Constructor con el nombre y la prioridad de cada hilo.
     *
     * @param nombre Nombre del hilo.
     * @param prioridad Prioridad del hilo.
     */
    public HiloPrioridades(String nombre, int prioridad) {
        setName(nombre);
        setPriority(prioridad);
    }//Fin HiloPrioridades

    /**
     * Método que se ejecuta al iniciar el hilo.
     * Comprueba la prioridad del hilo y llama a la tarea correspondiente
     * en un bucle infinito, con una demora de 1 segundo entre cada uno.
     */
    @Override
    public void run() {
        System.out.println("Iniciando el hilo: " + getName());

        while (true) {
            switch (getPriority()) {
                case 1:
                    tarea1();
                    break;
                case 3:
                    tarea3();
                    break;
                case 5:
                    tarea5();
                    break;
                default:
                    System.out.println(getName() + " tiene una prioridad no manejada.");
                    break;
            }//Fin switch

            try {
                // 1 segundo.
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }//Fin try-catch
        } // Fin while
    } // Fin run

    /**
     * Método que representa una tarea lenta, asociada a la prioridad 1.
     * Muestra el nombre del hilo, su prioridad y el mensaje "tarea lenta".
     */
    private void tarea1() {
        System.out.println(getName() + " [Prioridad " + getPriority() + "] - tarea lenta");
    }

    /**
     * Método que representa una tarea normal, asociada a la prioridad 3.
     * Muestra el nombre del hilo, su prioridad y el mensaje "tarea normal".
     */
    private void tarea3() {
        System.out.println(getName() + " [Prioridad " + getPriority() + "] - tarea normal");
    }

    /**
     * Método que representa una tarea rápida, asociada a la prioridad 5.
     * Muestra el nombre del hilo, su prioridad y el mensaje "tarea rápida".
     */
    private void tarea5() {
        System.out.println(getName() + " [Prioridad " + getPriority() + "] - tarea rápida");
    }

} // Fin clase HiloPrioridades
