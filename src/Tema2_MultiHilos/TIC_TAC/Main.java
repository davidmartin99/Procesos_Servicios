package Tema2_MultiHilos.TIC_TAC;

public class Main {
    public static void main(String[] args) {

        for(int i = 0; i < 20; i++){
            Tic t1 = new Tic();
            Tac t2 = new Tac();

            t1.start();
            try {
                Thread.sleep(500); // Pausa de 500 ms
            } catch (InterruptedException e) {
            }

            t2.start();
            try {
                Thread.sleep(500); // Pausa de 500 ms
            } catch (InterruptedException e) {
            }
        }//Fin for

    }//Fin main

}//Fin class
