package Tema2_MultiHilos.BloquesSincronizadosEj7;

public class HiloCompra extends Thread{
    Producto producto;
    Inventario inventario;
    int cantidad;

    public HiloCompra(String nombre, Producto producto, Inventario inventario) {
        setName(nombre);
        this.producto = producto;
        this.inventario = inventario;
    }

    public void run() {
        Producto interno;
        int actual = 0;

        if(inventario.existeProducto(producto)) {
            interno = inventario.lista.get(producto.getId());
            actual = interno.getCantidad();
            actual = actual + producto.getCantidad();

            interno.setCantidad(actual);
        }else{
            inventario.lista.add(producto);
        }//Fin if-else

    }//Fin run

}//Fin class
