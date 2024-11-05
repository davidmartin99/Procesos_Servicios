package Tema2_MultiHilos.Bloqueo_Hilos;

public class BloqueoHilos {
    public static void main(String[] args) {
        ObjetoCompartido com = new ObjetoCompartido(); // Crear el objeto compartido
        HiloCadena a = new HiloCadena(com, " A "); // Crear el hilo A
        HiloCadena b = new HiloCadena(com, " B "); // Crear el hilo B
        a.start(); // Iniciar el hilo A
        b.start(); // Iniciar el hilo B
    }
} // BloqueoHilos
