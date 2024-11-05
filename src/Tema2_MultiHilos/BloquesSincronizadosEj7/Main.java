package Tema2_MultiHilos.BloquesSincronizadosEj7;

import java.util.Random;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Producto p1 = new Producto(1, 3);
        Producto p2 = new Producto(2, 6);

        Inventario almacen = new Inventario();
        almacen.addProducto(p1);
        almacen.addProducto(p2);

        Random random = new Random();

        // Hilos
        HiloCompra h1 = new HiloCompra("hilo1", new Producto(1, random.nextInt(1, 5)), almacen);
        HiloCompra h2 = new HiloCompra("hilo2", new Producto(2, random.nextInt(1, 10)), almacen);

        HiloVenta h3 = new HiloVenta("hilo3", new Producto(1, random.nextInt(2, 4)), almacen);
        HiloVenta h4 = new HiloVenta("hilo4", new Producto(2, random.nextInt(1, 3)), almacen);

        h1.start();
        h2.start();
        h3.start();
        h4.start();

        h1.join();
        h2.join();
        h3.join();
        h4.join(); // Esperar a que todos los hilos terminen

        System.out.println(almacen.toString());
    }
}
