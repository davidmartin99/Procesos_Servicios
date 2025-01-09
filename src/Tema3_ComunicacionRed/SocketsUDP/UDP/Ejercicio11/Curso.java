package Tema3_ComunicacionRed.SocketsUDP.UDP.Ejercicio11;

import java.io.Serializable;

public class Curso implements Serializable {
    private String id;
    private String descripcion;

    // Constructor por defecto
    public Curso() {
    }

    // Constructor con parámetros
    public Curso(String id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    // Métodos getter y setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    // Método toString para representar la clase como texto
    @Override
    public String toString() {
        return "Curso{" +
                "id='" + id + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
