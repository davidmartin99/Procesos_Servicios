package Tema2_MultiHilos;

// Clase que extiende Thread para crear un hilo que cuenta hasta 'n'.
class HiloJoin extends Thread {
    private int n; // Variable para almacenar el número hasta el cual contar

    // Constructor de la clase HiloJoin
    public HiloJoin(String nom, int n) {
        super(nom); // Llama al constructor de la clase Thread para establecer el nombre del hilo
        this.n = n; // Asigna el valor de n a la variable de instancia
    }

    // Método que se ejecuta cuando se inicia el hilo
    public void run() {
        // Bucle que cuenta desde 1 hasta n
        for (int i = 1; i <= n; i++) {
            System.out.println(getName() + ": " + i); // Imprime el nombre del hilo y el número actual
            try {
                sleep(1000); // Pausa el hilo durante 1000 milisegundos (1 segundo)
            } catch (InterruptedException ignore) {
                // Captura la excepción si el hilo es interrumpido durante el sueño
            }//Fin try-catch
        }//Fin for
        System.out.println("Fin Bucle " + getName()); // Imprime un mensaje al final del bucle
    }
}//Fin class

// Clase principal que contiene el método main
public class EjemploJoin {
    public static void main(String[] args) {
        // Crea tres hilos con diferentes nombres y valores de n
        HiloJoin h1 = new HiloJoin("Hilo1", 2);
        HiloJoin h2 = new HiloJoin("Hilo2", 5);
        HiloJoin h3 = new HiloJoin("Hilo3", 7);

        // Inicia los hilos
        h1.start();
        h2.start();
        h3.start();

        // Espera a que los hilos terminen antes de continuar
        try {
            h1.join(); // Espera a que h1 termine
            h2.join(); // Espera a que h2 termine
            h3.join(); // Espera a que h3 termine
        } catch (InterruptedException e) {
            // Captura la excepción si el hilo principal es interrumpido
            e.printStackTrace();
        }

        // Mensaje que indica que todos los hilos han terminado
        System.out.println("FINAL DE PROGRAMA");
    }//Fin main

}//Fin class


