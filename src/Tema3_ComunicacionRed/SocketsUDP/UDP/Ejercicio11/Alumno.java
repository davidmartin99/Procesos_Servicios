package Tema3_ComunicacionRed.SocketsUDP.UDP.Ejercicio11;

import java.io.Serializable;

public class Alumno implements Serializable {
    private String idAlumno;
    private String nombre;
    private Curso curso; // Relación con la clase Curso
    private int nota;

    // Constructor por defecto
    public Alumno() {
    }

    // Constructor con parámetros
    public Alumno(String idAlumno, String nombre, Curso curso, int nota) {
        this.idAlumno = idAlumno;
        this.nombre = nombre;
        this.curso = curso;
        this.nota = nota;
    }

    // Métodos getter y setter
    public String getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(String idAlumno) {
        this.idAlumno = idAlumno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    // Método toString para representar la clase como texto
    @Override
    public String toString() {
        return "Alumno{" +
                "idAlumno='" + idAlumno + '\'' +
                ", nombre='" + nombre + '\'' +
                ", curso=" + curso +
                ", nota=" + nota +
                '}';
    }
}
