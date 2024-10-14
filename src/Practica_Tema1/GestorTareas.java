package Practica_Tema1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * La clase GestorTareas permite a los usuarios seleccionar archivos de su sistema
 * y comprimirlos en archivos .tar utilizando procesos en paralelo.
 * @author david
 * @version 1.0
 * @date 12/10/2024
 */
public class GestorTareas {

    /**
     * Método principal que se ejecuta al iniciar la aplicación.
     * Permite al usuario ingresar rutas de archivos a comprimir y gestiona el proceso de compresión.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<File> archivos = new ArrayList<>();

        // Solicitar al usuario que ingrese la ruta de los archivos a comprimir
        System.out.println("Ingrese las rutas de los archivos a comprimir (escriba 'fin' para terminar):");

        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("fin")) {
                break;
            }
            File archivo = new File(input);
            if (archivo.exists() && archivo.isFile()) {
                archivos.add(archivo);
                System.out.println("Archivo agregado: " + input);
            } else {
                System.out.println("El archivo no existe o la ruta es incorrecta. Intente de nuevo.");
            }
        }

        // Iniciar procesos para comprimir cada archivo
        List<Process> procesos = new ArrayList<>();
        List<String> resultados = new ArrayList<>();

        for (File archivo : archivos) {
            String nombreTar = archivo.getAbsolutePath() + ".tar"; // Nombre del archivo tar

            // Crear el proceso de compresión
            ProcessBuilder pb = new ProcessBuilder("tar", "-cvf", nombreTar, archivo.getAbsolutePath());
            pb.redirectErrorStream(true); // Redirigir errores a la salida estándar

            try {
                Process proceso = pb.start();
                procesos.add(proceso);
                resultados.add(nombreTar); // Almacenar el nombre del archivo tar resultante
            } catch (IOException e) {
                System.err.println("Error al iniciar el proceso para " + archivo.getName() + ": " + e.getMessage());
            }
        }

        // Esperar a que todos los procesos terminen y mostrar resultados
        for (int i = 0; i < procesos.size(); i++) {
            Process proceso = procesos.get(i);
            String nombreTar = resultados.get(i);

            try {
                // Esperar a que el proceso termine
                int exitCode = proceso.waitFor();

                // Mostrar el estado final
                if (exitCode == 0) {
                    System.out.println("Compresión exitosa: " + nombreTar);
                } else {
                    System.out.println("Error al comprimir " + archivos.get(i).getName() + ". Código de salida: " + exitCode);
                }
            } catch (InterruptedException e) {
                System.err.println("El proceso fue interrumpido: " + e.getMessage());
            }
        }

        scanner.close();
    } // Fin main

} // Fin class GestorTareas
