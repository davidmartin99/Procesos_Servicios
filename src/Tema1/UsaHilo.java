package Tema1;

public class UsaHilo{
	public static void main(String[] args) {
		HiloSimple hs = new HiloSimple();
		hs.start();
		for (int i = 0; i < 5; i++)
			System.out.println("Fuera del Hilo… ");
	}
}
