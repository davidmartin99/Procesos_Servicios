package Tema2_MultiHilos.Practica_Tema2.Ejercicio1;

import static Tema2_MultiHilos.Practica_Tema2.CuentaVocales.lock;

/**
 * Clase que representa una tarea de cálculo que extiende la clase Thread.
 * Cada hilo de esta clase genera números aleatorios y los suma.
 *
 * @author David
 * @date 04/11/2024
 */
public class TareaCalculo_a extends Thread {
    // Variable de la suma total
    private int suma = 0;

    /**
     * Método que se ejecuta cuando se inicia el hilo.
     * En un bucle infinito, crea un número aleatorio, lo va sumando al anterior,
     * y muestra el resultado en la consola.
     */
    @Override
    public void run() {
        synchronized (lock) {
            try {
                // Genera un número aleatorio entre 100 y 1,000
                int numero = (int) (Math.random() * 901) + 100;
                suma += numero; // Suma el número al acumulado

                // Muestra el valor acumulado en la consola con el nombre del hilo
                System.out.println(getName() + " - Valor acumulado: " + suma);

                // Espera 10 segundos antes de permitir que el siguiente hilo entre
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                System.out.println(getName() + " ha sido interrumpido.");
                Thread.currentThread().interrupt();
            }
        }
    }
}
