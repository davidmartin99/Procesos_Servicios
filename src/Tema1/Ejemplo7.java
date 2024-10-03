package Tema1;

import java.io.File;
import java.io.IOException;

/*
    Este programa utiliza ProcessBuilder para ejecutar un comando en la consola de Windows
    y redirigir la salida estándar y la salida de error a archivos específicos.
    Ej7: ejecuta el comando DIR y envía la salida al fichero salida.txt.
     Si hay algún error lo envía al fichero error.txt
 */
public class Ejemplo7 {
    public static void main(String[] args) throws IOException {
        // Creamos un objeto ProcessBuilder para ejecutar "CMD"
        ProcessBuilder pb = new ProcessBuilder("CMD", "/C", "dir"); // "dir" se puede cambiar por cualquier otro comando

        // Creamos archivos para redirigir la salida estándar y la salida de error
        File fOut = new File("./src/Tema1/salida.txt"); // Archivo para la salida estándar
        File fErr = new File("./src/Tema1/error.txt");   // Archivo para la salida de error

        // Redirigimos la salida estándar del proceso al archivo de salida
        pb.redirectOutput(fOut);
        // Redirigimos la salida de error del proceso al archivo de error
        pb.redirectError(fErr);

        // Iniciamos el proceso
        pb.start();
    }
} // Fin de Ejemplo7
