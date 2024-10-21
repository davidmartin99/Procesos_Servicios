package Tema2_MultiHilos.BloquesSincronizadosEj7;

import java.util.ArrayList;

/**
 * Crea una clase Inventario que contenga una lista de productos y
 * métodos para comprar productos.
 */

public class Inventario {
    // Lista de Productos
    ArrayList<Producto> lista = new ArrayList<>();

    // Metodo para comprar productos
    boolean addProducto(Producto producto) {
        return lista.add(producto);
    }

    // Metodo para eliminar productos
    boolean eliminarProducto(Producto producto) {
        return lista.remove(producto);
    }

    // Metodo para ver si existe producto
    boolean existeProducto(Producto producto) {
        return lista.contains(producto);
    }

    @Override
    public String toString() {
        return lista.toString();
    }
}//Fin class
