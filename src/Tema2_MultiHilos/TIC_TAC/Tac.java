package Tema2_MultiHilos.TIC_TAC;

public class Tac extends Thread {
    public void run() {
        System.out.println("TAC");
        try {
            Thread.sleep(500); // Pausa de 500 ms
        } catch (InterruptedException e) {
            System.out.println("TAC thread interrupted.");
        }
    }

}
