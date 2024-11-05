package Tema2_MultiHilos.BloquesSincronizadosEj7;

import java.util.ArrayList;

public class Inventario {
    // Lista de Productos
    final ArrayList<Producto> lista = new ArrayList<>();

    // Metodo para agregar productos
    public synchronized boolean addProducto(Producto producto) {
        for (Producto p : lista) {
            if (p.getId() == producto.getId()) {
                return false; // No se agrega si ya existe
            }
        }
        return lista.add(producto);
    }

    // Metodo para eliminar productos
    public synchronized boolean eliminarProducto(Producto producto) {
        return lista.removeIf(p -> p.getId() == producto.getId());
    }

    // Metodo para ver si existe producto
    public synchronized boolean existeProducto(int id) {
        return lista.stream().anyMatch(p -> p.getId() == id);
    }

    @Override
    public synchronized String toString() {
        return lista.toString();
    }
}
