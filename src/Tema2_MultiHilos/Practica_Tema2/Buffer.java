package Tema2_MultiHilos.Practica_Tema2;

import java.util.ArrayList;

public class Buffer {
    private final ArrayList<Integer> buffer = new ArrayList<>();

    public synchronized boolean agregarElemento(int valor) throws InterruptedException {
        //implementación del método

        return buffer.add(valor);
    }

    public synchronized int retirarElemento() throws InterruptedException {
        while (buffer.isEmpty()) {
            wait();  // Espera hasta que el buffer no esté vacío
        }
        return buffer.remove(0);
    }

	//Getters y Setters
	
}