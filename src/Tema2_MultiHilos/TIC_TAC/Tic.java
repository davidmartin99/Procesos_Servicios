package Tema2_MultiHilos.TIC_TAC;

public class Tic extends Thread {
    public void run() {
        System.out.println("TIC");
        try {
            Thread.sleep(500); // Pausa de 500 ms
        } catch (InterruptedException e) {
            System.out.println("TIC thread interrupted.");
        }
    }
}
