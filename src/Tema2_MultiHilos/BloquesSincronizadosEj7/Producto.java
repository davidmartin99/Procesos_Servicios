package Tema2_MultiHilos.BloquesSincronizadosEj7;

public class Producto {
    // Atributos
    private int id;
    private int cantidad;

    // Constructores
    public Producto(int id, int cantidad) {
        this.id = id;
        this.cantidad = cantidad;
    }

    public int getId() {
        return id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Producto{id=" + id + ", cantidad=" + cantidad + "}";
    }
}
