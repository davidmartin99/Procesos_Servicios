package Tema2_MultiHilos;

public class PrimerHiloRunnable implements Runnable {
	public void run() {
		System.out.println("Hola desde el Hilo! " +
	       Thread.currentThread().getId());
	}
}
