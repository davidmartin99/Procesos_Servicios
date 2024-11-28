package Tema2_MultiHilos.Practica_Tema2;

public class ControlHilos implements Runnable {
    private final Buffer buffer;
    private final String nombre;
    private boolean ejecutar = true;

    public ControlHilos(Buffer buffer, String nombre) {
        this.buffer = buffer;
        this.nombre = nombre;
    }

    public int contador = 0;

	//Constructor

    @Override
    public void run() {
        while (ejecutar) {
		contador++;
            try {
                buffer.agregarElemento(contador);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}