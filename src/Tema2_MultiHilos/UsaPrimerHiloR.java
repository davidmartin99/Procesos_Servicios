package Tema2_MultiHilos;

public class UsaPrimerHiloR {
	public static void main(String[] args) {
		//Primer hilo
		PrimerHiloRunnable hilo1 = new PrimerHiloRunnable();
		new Thread(hilo1).start();

		//Segundo hilo
		PrimerHiloRunnable hilo2 = new PrimerHiloRunnable();
		Thread hilo = new Thread(hilo2);
		hilo.start(); 
		
		//Tercer Hilo
		new Thread(new PrimerHiloRunnable()).start();
	}
}//UsaPrimerHiloR
