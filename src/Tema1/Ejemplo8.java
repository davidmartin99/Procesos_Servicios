package Tema1;

import java.io.File;
import java.io.IOException;

/*
    Metodo redirectInput()
    Este programa utiliza ProcessBuilder para ejecutar un archivo por lotes (batch file)
    en la consola de Windows y redirigir la entrada, salida estándar y salida de error
    a archivos específicos.
 */

public class Ejemplo8 {
    public static void main(String[] args) throws IOException {
        // Creamos un objeto ProcessBuilder para ejecutar "CMD"
        ProcessBuilder pb = new ProcessBuilder("CMD"); // Inicia la consola de Windows

        // Creamos un archivo que contiene el script por lotes a ejecutar
        File fBat = new File("fichero.bat"); // Archivo por lotes que se va a ejecutar

        // Creamos archivos para redirigir la salida estándar y la salida de error
        File fOut = new File("salida.txt"); // Archivo para la salida estándar
        File fErr = new File("error.txt");   // Archivo para la salida de error

        // Redirigimos la entrada del proceso al archivo por lotes
        pb.redirectInput(fBat);
        // Redirigimos la salida estándar del proceso al archivo de salida
        pb.redirectOutput(fOut);
        // Redirigimos la salida de error del proceso al archivo de error
        pb.redirectError(fErr);

        // Iniciamos el proceso
        pb.start(); // Ejecuta el comando configurado, en este caso el CMD con el archivo por lotes
    }
} // Fin de Ejemplo8
