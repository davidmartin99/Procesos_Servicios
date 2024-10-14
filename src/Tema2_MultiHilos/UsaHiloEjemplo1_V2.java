package Tema2_MultiHilos;

public class UsaHiloEjemplo1_V2 {
	public static void main(String[] args) {
		HiloEjemplo1_V2 h1 = new HiloEjemplo1_V2("Hilo 1");
		HiloEjemplo1_V2 h2 = new HiloEjemplo1_V2("Hilo 2");
		HiloEjemplo1_V2 h3 = new HiloEjemplo1_V2("Hilo 3");
			
		h1.start();
		h2.start();
		h3.start();
		
		System.out.println("3 HILOS INICIADOS...");
	}
}//UsaHiloEjemplo1_V2
