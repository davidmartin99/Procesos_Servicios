package Tema2_MultiHilos.Producto_Consumidor;

public class ProducConsum {
    public static void main(String[] args) {
        Cola cola = new Cola(); // Recurso compartido (cola)
        Productor p = new Productor(cola, 1); // Crea un productor
        Consumidor c = new Consumidor(cola, 1); // Crea un consumidor
        p.start(); // Inicia el hilo productor
        c.start(); // Inicia el hilo consumidor
    }
}
