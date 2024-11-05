package Tema2_MultiHilos.BloquesSincronizadosEj7;

public class HiloVenta extends Thread {
    private final Producto producto;
    private final Inventario inventario;

    public HiloVenta(String nombre, Producto producto, Inventario inventario) {
        setName(nombre);
        this.producto = producto;
        this.inventario = inventario;
    }

    @Override
    public void run() {
        synchronized (inventario) {
            if (inventario.existeProducto(producto.getId())) {
                Producto interno = inventario.lista.stream()
                        .filter(p -> p.getId() == producto.getId())
                        .findFirst()
                        .orElse(null);

                if (interno != null) {
                    int actual = interno.getCantidad();
                    if (actual >= producto.getCantidad()) {
                        actual -= producto.getCantidad();
                        interno.setCantidad(actual);
                        if (actual == 0) {
                            inventario.eliminarProducto(interno);
                        }
                        System.out.println(getName() + " ha vendido " + producto.getCantidad() + " de " + interno);
                    } else {
                        System.err.println("Error, no hay suficiente cantidad. Cantidad actual: " + actual);
                    }
                }
            } else {
                System.err.println("Error, el producto no existe.");
            }
        }
    }
}
