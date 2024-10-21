package Tema2_MultiHilos.BloquesSincronizadosEj7;

public class HiloVenta extends Thread{
    Producto producto;
    Inventario inventario;
    int cantidad;

    public HiloVenta(String nombre, Producto producto, Inventario inventario) {
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

            //Si hay cantidad
            if(actual>= producto.getCantidad()){
                actual = actual - producto.getCantidad();
                interno.setCantidad(actual);
                if(actual==0){
                    inventario.eliminarProducto(interno); //Metodo de Inventario
                }
            }else{
                System.err.println("Error, no hay suficiente cantidad" +
                        "cantidad actual: "+ interno.getCantidad());
            }

        }else{
            System.err.println("Error el producto no existe");
        }//Fin if-else

    }//Fin run

}//Fin class
