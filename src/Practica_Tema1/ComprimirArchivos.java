package Practica_Tema1;

import java.io.*;

/**
 * La clase ComprimirArchivos permite comprimir múltiples archivos en un archivo .tar utilizando
 * el comando 'tar' del sistema operativo.
 * Se asegura de que los archivos a comprimir existan y gestiona el proceso de compresión.
 * @author david
 * @version 1.0
 * @date 12/10/2024
 */
public class ComprimirArchivos {

    /**
     * Método principal que se ejecuta al iniciar la aplicación.
     * Se encarga de definir los archivos a comprimir y gestionar el proceso de compresión.
     */
    public static void main(String[] args) {
        // Archivo .tar que vamos a generar
        String nombreTar = "src\\Practica_Tema1\\archivos_comprimidos.tar";

        // Rutas de los archivos a comprimir
        String[] archivos = {
                "src\\Practica_Tema1\\archivo1.txt",
                "src\\Practica_Tema1\\archivo2.txt",
                "src\\Practica_Tema1\\archivo3.txt"
        };

        // Comprobar si los archivos existen, si no, crearlos
        comprobarYCrearArchivos(archivos);

        // Iniciar el proceso que comprime archivos
        try {
            // Crear el ProcessBuilder con el comando para 'tar' con la opción '-T -'
            ProcessBuilder pb = new ProcessBuilder("tar", "-cf", nombreTar, "-T", "-");

            // Redirigir errores a la salida estándar
            pb.redirectErrorStream(true);

            // Iniciar el proceso
            Process proceso = pb.start();

            // Enviar la lista de archivos a través de la entrada estándar
            try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(proceso.getOutputStream()))) {
                for (String archivo : archivos) {
                    writer.write(archivo);
                    writer.newLine(); // Añadir una nueva línea para cada archivo
                }
            }

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
        } //Fin try-catch

    } //Fin main

    /**
     * Método que comprueba si los archivos existen. Si no existen, los crea.
     *
     * @param archivos Array de rutas de los archivos a comprobar.
     */
    public static void comprobarYCrearArchivos(String[] archivos) {
        for (String archivo : archivos) {
            File file = new File(archivo);
            if (!file.exists()) {
                try {
                    if (file.createNewFile()) {
                        System.out.println("Archivo creado: " + archivo);
                    }
                } catch (IOException e) {
                    System.err.println("No se pudo crear el archivo: " + archivo);
                    e.printStackTrace();
                }
            } else {
                System.out.println("El archivo ya existe: " + archivo);
            }
        }
    } //Fin comprobarYCrearArchivos

} //Fin class ComprimirArchivos
