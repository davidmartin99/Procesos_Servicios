package Tema2_MultiHilos;

public class HiloPrioridad2 extends Thread {

    // Constructor que recibe el nombre del hilo
    HiloPrioridad2(String nom) {
        this.setName(nom); // Asigna el nombre al hilo
    }

    // Método run que se ejecuta cuando se inicia el hilo
    public void run() {
        System.out.println("Ejecutando [" + getName() + "]"); // Muestra el nombre del hilo en ejecución
        for (int i = 1; i <= 5; i++) {
            System.out.println("\t(" + getName() + ": " + i + ")"); // Muestra el nombre del hilo y el valor de i
        }
    }

    public static void main(String[] args) {
        // Creación de hilos con diferentes nombres
        HiloPrioridad2 h1 = new HiloPrioridad2("Uno");
        HiloPrioridad2 h2 = new HiloPrioridad2("Dos");
        HiloPrioridad2 h3 = new HiloPrioridad2("Tres");
        HiloPrioridad2 h4 = new HiloPrioridad2("Cuatro");
        HiloPrioridad2 h5 = new HiloPrioridad2("Cinco");

        // Asignación de prioridades a cada hilo
        h1.setPriority(Thread.MIN_PRIORITY);   // Prioridad mínima (1)
        h2.setPriority(3);                     // Prioridad baja (3)
        h3.setPriority(Thread.NORM_PRIORITY);  // Prioridad normal (5)
        h4.setPriority(7);                     // Prioridad alta (7)
        h5.setPriority(Thread.MAX_PRIORITY);   // Prioridad máxima (10)

        // Ejecución de los hilos
        h1.start();
        h2.start();
        h3.start();
        h4.start();
        h5.start();
    }
} // Fin de la clase EjemploHiloPrioridad2
