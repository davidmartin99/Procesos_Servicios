package Tema2_MultiHilos.BloquesSincronizadosEj7;

/**
 * Crea una clase Producto que tenga un atributo cantidad, un atributo id, y
 * métodos para obtener y actualizar la cantidad.
 */
public class Producto {
    // Atributos
    private int id;
    private int cantidad;

    // Constructores
    public Producto(int id) {
        this.id = id;
    }

    public Producto(int id, int cantidad) {
        this.id = id;
        this.cantidad = cantidad;

    }

    public Producto() {}

    // Get y Set
    public int getId() {
        return id;
    }

    public void setId(int id) {}

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {}



}//Fin class
