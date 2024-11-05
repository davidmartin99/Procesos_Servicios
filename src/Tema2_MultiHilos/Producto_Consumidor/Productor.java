package Tema2_MultiHilos.Producto_Consumidor;

import static java.lang.Thread.sleep;

public class Productor extends Thread{
    private Cola cola;
    private int n;

    public Productor(Cola c, int n) {
        cola = c;
        this.n = n;
    }

    public void run() {
        for (int i = 0; i < 5; i++) {
            cola.put(i); // Coloca un número en la cola
            System.out.println(i + " => Productor: " + n + ", produce: " + i);
            try {
                sleep(100); // Simula un tiempo de espera entre producciones
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
