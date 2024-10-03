package Tema1;

import java.io.IOException;

public class Ejemplo9 {
    public static void main(String args[]) throws IOException {
        // Creamos un objeto ProcessBuilder para ejecutar "CMD" con el argumento "/C"
        ProcessBuilder pb = new ProcessBuilder("CMD", "/C", // Inicia la consola de Windows y ejecuta un comando
                "//la salida a consola");

        // Redirigimos la salida estándar del proceso al mismo lugar que la salida estándar de la consola
        pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);

        // Iniciamos el proceso
        Process p = pb.start(); // Ejecuta el comando configurado en CMD
    }
} // Fin de Ejemplo9