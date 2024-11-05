package Tema2_MultiHilos.Producto_Consumidor;

import static java.lang.Thread.sleep;

public class Consumidor extends Thread{
    private Cola cola;
    private int n;

    public Consumidor(Cola c, int n) {
        cola = c;
        this.n = n;
    }

    public void run() {
        int valor;
        for (int i = 0; i < 5; i++) {
            valor = cola.get(); // Recoge un número de la cola
            System.out.println(i + " => Consumidor: " + n + ", consume: " + valor);
            try {
                sleep(200); // Simula un tiempo de espera entre consumos
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
