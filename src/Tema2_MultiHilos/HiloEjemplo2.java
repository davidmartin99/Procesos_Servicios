package Tema2_MultiHilos;
/*
    Metodos de algunos Hilos
 */

public class HiloEjemplo2 extends Thread {
    public void run() {
        System.out.println("Dentro del Hilo\n\tPrioridad: "
                + Thread.currentThread().getPriority()
                + "\n\tID: " + Thread.currentThread().getId()
                + "\n\tHilos activos: " + Thread.activeCount());
    }

    public static void main(String[] args) {
        Thread.currentThread().setName("Principal");
        System.out.println(Thread.currentThread().getName());
        System.out.println(Thread.currentThread().toString());

        for (int i = 0; i < 3; i++) { // Changed from 13 to 3 to create 3 threads
            HiloEjemplo2 h = new HiloEjemplo2(); // Crear hilo
            h.setName("HILO" + (i + 1)); // Damos nombre al hilo
            h.setPriority(i + 1); // Damos prioridad
            h.start(); // Iniciar hilo
            System.out.println("Información del " + h.getName() + ": " + h.toString());
        }//Fin for

        System.out.println("3 HILOS CREADOS...");
        System.out.println("Hilos activos: " + Thread.activeCount());
    }//Fin main

}//Fin class
