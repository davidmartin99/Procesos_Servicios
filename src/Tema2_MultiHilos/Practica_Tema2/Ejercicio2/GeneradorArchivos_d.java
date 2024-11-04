package Tema2_MultiHilos.Practica_Tema2.Ejercicio2;

import java.util.Set;

/**
 * Clase GeneradorArchivos_d que genera múltiples archivos CSV utilizando hilos.
 * Esta versión ha sido modificada para crear un número específico de archivos.
 * @author David
 * @date 04/11/2024
 */
public class GeneradorArchivos_d {
    private static final int CANTIDAD_ARCHIVOS = 50; // Cambiado a 50, número de archivos a generar

    /**
     * Método que genera múltiples archivos CSV en hilos separados.
     *
     * @param identificadores Conjunto de identificadores alfanuméricos a utilizar en los archivos.
     */
    public void generarArchivos(Set<String> identificadores) {
        // Itera para crear la cantidad especificada de archivos
        for (int i = 0; i < CANTIDAD_ARCHIVOS; i++) {
            // Crea y empieza un nuevo hilo que ejecuta GeneradorCSVRunnable
            new Thread(new GeneradorCSVRunnable(identificadores, "archivo_" + (i + 1) + ".csv")).start();
        }
    }//Fin generarArchivos

    /**
     * Método main que sirve como punto de entrada de la aplicación.
     * Registra el tiempo de ejecución total para generar los archivos.
     *
     * @param args Argumentos de línea de comandos (no utilizados en este caso).
     */
    public static void main(String[] args) {
        // Registra el tiempo inicial
        long startTime = System.currentTimeMillis();

        GeneradorIdentAlfa generadorIds = new GeneradorIdentAlfa(); // Crea una instancia del generador de identificadores
        Set<String> ids = generadorIds.generarIdentificadores(); // Genera los identificadores

        // Crea una instancia del generador de archivos
        GeneradorArchivos_d generadorArchivos = new GeneradorArchivos_d();
        // Llama al método para generar los archivos CSV
        generadorArchivos.generarArchivos(ids);

        // Registra el tiempo final
        long endTime = System.currentTimeMillis();
        // Mostramos el tiempo de ejecución
        System.out.println("Tiempo de ejecución: " + (endTime - startTime) + " ms");
    }//Fin main

}//Fin class