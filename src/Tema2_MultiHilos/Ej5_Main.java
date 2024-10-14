package Tema2_MultiHilos;

import java.util.Scanner;

// Clase para manejar la suspensión y reanudación del hilo
class SolicitaSuspender5 {
    private boolean suspender;

    public synchronized void set(boolean b) {
        suspender = b; // Cambio de estado sobre el objeto
        notifyAll();
    }

    public synchronized void esperandoParaReanudar() throws InterruptedException {
        while (suspender) {
            wait(); // Suspender el hilo hasta recibir notify() o notifyAll()
        }
    }
}

// Clase que extiende Thread para implementar el hilo
class MyHilo5 extends Thread {
    private SolicitaSuspender5 suspender = new SolicitaSuspender5();
    private int contador = 0; // Contador inicializado a 0
    private volatile boolean running = true; // Variable para controlar el hilo

    // Método que suspende el hilo
    public void suspende() {
        suspender.set(true);
    }

    // Método que reanuda el hilo
    public void reanuda() {
        suspender.set(false);
    }

    // Método que devuelve el valor del contador
    public int getContador() {
        return contador;
    }

    // Método para detener el hilo
    public void parar() {
        running = false; // Cambiar el estado de running
        suspender.set(false); // Asegúrate de reanudar el hilo si estaba suspendido
    }

    @Override
    public void run() {
        try {
            while (running) {
                contador++; // Incrementar el contador
                System.out.println("Contador: " + contador);
                Thread.sleep(500); // Espera para visualizar mejor los números
                suspender.esperandoParaReanudar(); // Esperar para reanudar
            }
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt(); // Manejo de interrupciones
        }
        System.out.println("El hilo ha terminado. Valor final del contador: " + contador);
    }
}

public class Ej5_Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MyHilo5 hilo = new MyHilo5(); // Cambia el nombre de MyHilo a MyHilo5

        System.out.println("Introduce 'S' para suspender, 'R' para reanudar, o 'X' para salir:");

        // Iniciar el hilo
        hilo.start();

        String input;
        while (true) {
            input = scanner.nextLine(); // Leer entrada del usuario
            if (input.equalsIgnoreCase("S")) {
                hilo.suspende(); // Suspender el hilo
            } else if (input.equalsIgnoreCase("R")) {
                hilo.reanuda(); // Reanudar el hilo
            } else if (input.equalsIgnoreCase("X")) {
                hilo.parar(); // Cambiar el estado de running
                break; // Salir del bucle
            }
        }

        try {
            hilo.join(); // Esperar a que el hilo termine
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Valor final del contador: " + hilo.getContador());
        scanner.close(); // Cerrar el escáner
    }
}
