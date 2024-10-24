package Tema2_MultiHilos.Producto_Consumidor;

public class Cola {
    private int numero;
    private boolean disponible = false; // Inicialmente la cola está vacía

    // Método para consumir un número (solo si está disponible)
    public synchronized int get() {
        while (!disponible) { // Espera hasta que haya un número disponible
            try {
                wait(); // El consumidor espera
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        disponible = false; // Después de consumir, marca la cola como vacía
        notify(); // Notifica al productor que puede producir más
        return numero; // Devuelve el número consumido
    }

    // Método para producir un número (solo si la cola está vacía)
    public synchronized void put(int valor) {
        while (disponible) { // Espera hasta que la cola esté vacía
            try {
                wait(); // El productor espera
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        numero = valor; // Asigna el número a la cola
        disponible = true; // Marca la cola como llena
        notify(); // Notifica al consumidor que puede consumir
    }
}
