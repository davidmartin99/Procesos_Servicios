package Practica_Tema1;

import java.io.*;
/*
    scribe la clase ComprimirArchivos que inicie un subproceso para comprimir varios archivos
    en un archivo .tar y comparta información con el subproceso.
 */

public class ComprimirArchivos {

    public static void main(String[] args) {
        // Archivo .tar que vamos a generar
        String nombreTar = "src\\Practica_Tema1\\archivos_comprimidos.tar";

        // Iniciar el proceso que comprime archivos
        try {
            // Crear el comando tar e incluir los archivos como argumentos directos
            ProcessBuilder pb = new ProcessBuilder();

            // Comando 'tar' con archivos a comprimir
            pb.command("tar", "-cvf", nombreTar,
                    "src\\Practica_Tema1\\archivo1.txt",
                    "src\\Practica_Tema1\\archivo2.txt",
                    "src\\Practica_Tema1\\archivo3.txt");

            // Redirigir errores a la salida estándar
            pb.redirectErrorStream(true);

            // Iniciar el proceso
            Process proceso = pb.start();

            // Leer la salida del subproceso
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(proceso.getInputStream()))) {
                String linea;
                while ((linea = reader.readLine()) != null) {
                    System.out.println(linea); // Mostrar la salida en la consola
                }
            }

            // Esperar a que el proceso termine y obtener el código de salida
            int exitCode = proceso.waitFor();
            if (exitCode == 0) {
                System.out.println("Archivos comprimidos exitosamente en " + nombreTar);
            } else {
                System.out.println("Error al comprimir los archivos. Código de salida: " + exitCode);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }//Fin try-catch

    }//Fin main

}//Fin class
