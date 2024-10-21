package Tema2_MultiHilos;

/*
    Sincronización de Hilos
    pag.66 - pag.73
 */

// Clase Contador que mantiene un valor entero y proporciona métodos para incrementar, decrementar y obtener el valor.
class Contador {
    private int c; // Variable privada para almacenar el valor del contador.

    // Constructor que inicializa el contador con un valor inicial.
    Contador(int c) {
        this.c = c; // Asigna el valor inicial al contador.
    }

    // Método que incrementa el contador en 1.
    public void incrementa() {
        c = c + 1; // Aumenta el valor del contador.
    }

    // Método que decrementa el contador en 1.
    public void decrementa() {
        c = c - 1; // Disminuye el valor del contador.
    }

    // Método para obtener el valor actual del contador.
    public int getValor() {
        return c; // Devuelve el valor del contador.
    }
}

// Clase HiloA que extiende Thread para incrementar el contador.
class HiloA extends Thread {
    private Contador contador; // Variable privada para almacenar la referencia al contador.

    // Constructor que inicializa el hilo con un nombre y un objeto Contador.
    public HiloA(String n, Contador c) {
        setName(n); // Establece el nombre del hilo.
        this.contador = c; // Asigna el objeto Contador al hilo.
    }

    // Método que se ejecuta al iniciar el hilo.
    public void run() {
        synchronized (contador) { // Bloque sincronizado para asegurar acceso exclusivo al contador.
            for (int j = 0; j < 300; j++) {
                contador.incrementa(); // Incrementa el contador.
                // System.out.println(getName() + " contador vale " + contador.getValor()); // Imprime el valor actual.
            }
            System.out.println(getName() + " contador vale " + contador.getValor()); // Imprime el valor final.
        } // FIN HILOA
    }
}

// Clase HiloB que extiende Thread para decrementar el contador.
class HiloB extends Thread {
    private Contador contador; // Variable privada para almacenar la referencia al contador.

    // Constructor que inicializa el hilo con un nombre y un objeto Contador.
    public HiloB(String n, Contador c) {
        setName(n); // Establece el nombre del hilo.
        this.contador = c; // Asigna el objeto Contador al hilo.
    }

    // Método que se ejecuta al iniciar el hilo.
    public void run() {
        synchronized (contador) { // Bloque sincronizado para asegurar acceso exclusivo al contador.
            for (int j = 0; j < 300; j++) {
                contador.decrementa(); // Decrementa el contador.
                // System.out.println(getName() + " contador vale " + contador.getValor()); // Imprime el valor actual.
            }
            System.out.println(getName() + " contador vale " + contador.getValor()); // Imprime el valor final.
        } // FIN HILOB
    }
}

// Clase principal que contiene el método main.
public class CompartirInf1 {
    public static void main(String[] args) {
        Contador cont = new Contador(100); // Crea un objeto Contador inicializado en 100.
        HiloA a = new HiloA("HiloA", cont); // Crea el hilo A.
        HiloB b = new HiloB("HiloB", cont); // Crea el hilo B.
        a.start(); // Inicia la ejecución del hilo A.
        b.start(); // Inicia la ejecución del hilo B.
    }
}
