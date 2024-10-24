package Tema2_MultiHilos.BloquesSincronizadosEj7;

public class HiloCompra extends Thread {
    private final Producto producto;
    private final Inventario inventario;

    public HiloCompra(String nombre, Producto producto, Inventario inventario) {
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
                    int nuevaCantidad = interno.getCantidad() + producto.getCantidad();
                    interno.setCantidad(nuevaCantidad);
                    System.out.println(getName() + " ha comprado " + producto.getCantidad() + " de " + interno);
                }
            } else {
                inventario.addProducto(producto);
                System.out.println(getName() + " ha añadido " + producto + " al inventario.");
            }
        }
    }
}
